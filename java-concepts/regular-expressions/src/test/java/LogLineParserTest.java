import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LogLineParser")
class LogLineParserTest {

    @Test
    @DisplayName("should parse a well-formed log line")
    void shouldParseWellFormedLine() {
        Optional<LogLineParser.LogEntry> entry =
            LogLineParser.parse("2026-08-02 ERROR Payment failed");

        assertTrue(entry.isPresent());
        assertEquals("2026-08-02", entry.get().date());
        assertEquals("ERROR", entry.get().level());
        assertEquals("Payment failed", entry.get().message());
    }

    @Test
    @DisplayName("should return empty for a line missing the level")
    void shouldReturnEmptyForMalformedLine() {
        Optional<LogLineParser.LogEntry> entry = LogLineParser.parse("2026-08-02 Payment failed");

        assertTrue(entry.isEmpty());
    }

    @Test
    @DisplayName("should return empty when the line has leading text before the date")
    void shouldReturnEmptyWhenLineHasLeadingText() {
        Optional<LogLineParser.LogEntry> entry =
            LogLineParser.parse("prefix 2026-08-02 ERROR Payment failed");

        assertTrue(entry.isEmpty());
    }

    @Test
    @DisplayName("should find every ERROR message in a multi-line log")
    void shouldFindAllErrorMessages() {
        String log = """
            2026-08-02 INFO Server started
            2026-08-02 ERROR Payment failed
            2026-08-02 WARN Slow response
            2026-08-02 ERROR Database timeout
            """;

        List<String> errors = LogLineParser.findAllErrorMessages(log);

        assertEquals(List.of("Payment failed", "Database timeout"), errors);
    }

    @Test
    @DisplayName("should return an empty list when there are no ERROR lines")
    void shouldReturnEmptyListWhenNoErrors() {
        String log = "2026-08-02 INFO Server started\n";

        assertTrue(LogLineParser.findAllErrorMessages(log).isEmpty());
    }

    @Test
    @DisplayName("should extract the first quoted substring, not everything up to the last quote")
    void shouldExtractFirstQuotedSubstring() {
        String text = "user said \"hello\" and then \"goodbye\"";

        assertEquals("hello", LogLineParser.extractFirstQuoted(text));
    }

    @Test
    @DisplayName("should return an empty string when there are no quotes")
    void shouldReturnEmptyStringWhenNoQuotes() {
        assertEquals("", LogLineParser.extractFirstQuoted("no quotes here"));
    }

    @Test
    @DisplayName("should mask every digit in the text (bonus)")
    void shouldMaskDigits() {
        assertEquals("card ending in ####", LogLineParser.maskDigits("card ending in 1234"));
    }
}
