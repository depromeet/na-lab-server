package me.nalab.core.secure.xss.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JsonXssFilter.class)
class JsonXssFilterTest {

	private static final String INVALID = "<SCRIPT>alert(\"테스트!!!\");</SCRIPT>";

	@Autowired
	private JsonXssFilter jsonXssFilter;

	/*
		Json 객체에 Xss Filtering 되어야할 텍스트가 들어간다면?
		-> Filtering 되어야함.
	 */
	@Test
	@DisplayName("Json Xss Filtering 테스트")
	void JSON_XSS_FILTERING_TEST() {
		// given
		String expected = "&lt;SCRIPT&gt;alert(&quot;테스트!!!&quot;);&lt;/SCRIPT&gt;";
		Request request = new Request(INVALID, INVALID);

		// when
		Request result = jsonXssFilter.doFilter(request, Request.class);

		// then
		assertEquals(expected, result.getHello());
		assertEquals(expected, result.getWorld());
	}

	/*
		객체가 중첩되어 있다면 어떻게 될까? -> Filtering 되어야함.
		json의 key가 filtering 대상이라면? -> Filtering 되어야함.
	 */
	@Test
	@DisplayName("Json Xss Filtering 테스트 - 중첩 객체")
	void JSON_XSS_FILTERING_WRAPPER_TEST() throws Throwable {
		// given
		String expected = "&lt;SCRIPT&gt;alert(&quot;테스트!!!&quot;);&lt;/SCRIPT&gt;";
		RequestWrapper request = new RequestWrapper(INVALID);

		// when
		RequestWrapper result = jsonXssFilter.doFilter(request, RequestWrapper.class);

		// then
		assertEquals(expected, result.getRequest().getHello());
		assertEquals(expected, result.getRequest().getWorld());
		result.getRequestList().forEach(
			r -> {
				assertEquals(expected, r.getWorld());
				assertEquals(expected, r.getHello());
			}
		);
		for(Map.Entry<String, Request> entry : result.getRequestMap().entrySet()) {
			assertEquals(expected, entry.getKey());
			assertEquals(expected, entry.getValue().getHello());
			assertEquals(expected, entry.getValue().getHello());
		}
	}

	/*
		Filtering 하지 않아도 되는 대상이 들어온 경우
	 */
	@Test
	@DisplayName("Json Xss Filtering - non filtering")
	void JSON_XSS_FILTERING_DOES_NOT_NEED_FILTERING_TEST() {
		// given
		String expected = "hello world";
		Request request = new Request(expected, expected);

		// when
		Request result = jsonXssFilter.doFilter(request, Request.class);

		// then
		assertEquals(expected, result.getHello());
		assertEquals(expected, result.getWorld());
	}

}
