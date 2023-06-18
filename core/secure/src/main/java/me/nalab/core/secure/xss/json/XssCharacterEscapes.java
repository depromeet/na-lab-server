package me.nalab.core.secure.xss.json;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

public class XssCharacterEscapes extends CharacterEscapes {

	private final int[] asciiEscapes;

	private final SerializeCharSequenceTranslator charSequenceTranslator;

	XssCharacterEscapes() {
		asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
		asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
		asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;

		charSequenceTranslator = new SerializeCharSequenceTranslator(
			new LookupTranslator(EntityArrays.BASIC_ESCAPE),
			new LookupTranslator(EntityArrays.ISO8859_1_ESCAPE),
			new LookupTranslator(EntityArrays.HTML40_EXTENDED_ESCAPE),
			new LookupTranslator(getLookUpMap())
		);
	}

	private Map<CharSequence, CharSequence> getLookUpMap() {
		HashMap<CharSequence, CharSequence> map = new HashMap<>();
		map.put("'", "&#39;");
		return map;
	}

	@Override
	public int[] getEscapeCodesForAscii() {
		return asciiEscapes;
	}

	@Override
	public SerializableString getEscapeSequence(int ch) {
		return new SerializedString(charSequenceTranslator.translate(Character.toString((char)ch)));
	}

	public static class SerializeCharSequenceTranslator extends CharSequenceTranslator implements Serializable {

		private final List<CharSequenceTranslator> translators = new ArrayList<>();

		public SerializeCharSequenceTranslator(final CharSequenceTranslator... translators) {
			if(translators != null) {
				Stream.of(translators).filter(Objects::nonNull).forEach(this.translators::add);
			}
		}

		@Override
		public int translate(final CharSequence input, final int index, final Writer writer) throws IOException {
			for(final CharSequenceTranslator translator : translators) {
				final int consumed = translator.translate(input, index, writer);
				if(consumed != 0) {
					return consumed;
				}
			}
			return 0;
		}

	}

}
