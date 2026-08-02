import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLineParser {

    private static final Pattern LOG_LINE =
        Pattern.compile("(?<date>\\d{4}-\\d{2}-\\d{2}) (?<level>[A-Z]+) (?<message>.+)");

    private static final Pattern QUOTED = Pattern.compile("\"(.*?)\"");

    public record LogEntry(String date, String level, String message) {
    }

    /**
     * Parses a single log line into a LogEntry, or returns empty if the line
     * doesn't match the expected "yyyy-MM-dd LEVEL message" shape.
     */
    public static Optional<LogEntry> parse(String line) {
        // TODO-00: Use LOG_LINE.matcher(line) and matches() — the WHOLE line
        // must fit the pattern, not just part of it. Read the "date",
        // "level", and "message" named groups to build a LogEntry.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Scans a multi-line block of log text and returns the message of every
     * line at ERROR level, in order.
     */
    public static List<String> findAllErrorMessages(String log) {
        // TODO-01: Use LOG_LINE.matcher(log) and find() in a loop — unlike
        // matches(), find() locates matches anywhere in the input and can be
        // called repeatedly to walk through all of them.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Returns the first double-quoted substring in `text`, without the
     * quotes, or "" if there are none. If `text` has multiple quoted
     * substrings, only the first one is returned — not everything from the
     * first quote to the last.
     */
    public static String extractFirstQuoted(String text) {
        // TODO-02: QUOTED already uses a reluctant quantifier. Use it with
        // find() and return group(1), or "" if there's no match.

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Replaces every digit in `text` with '#'.
     */
    public static String maskDigits(String text) {
        // TODO-03 (optional): Use String.replaceAll() with a digit class.

        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
