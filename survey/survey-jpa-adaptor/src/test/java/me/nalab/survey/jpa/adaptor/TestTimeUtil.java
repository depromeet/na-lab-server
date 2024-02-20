package me.nalab.survey.jpa.adaptor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TestTimeUtil {

    public static Instant now() {
        var current = Instant.now();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSX")
            .withZone(ZoneId.of("UTC"));
        return Instant.parse(formatter.format(current));
    }

}
