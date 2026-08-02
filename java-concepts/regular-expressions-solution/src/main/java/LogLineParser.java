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

    public static Optional<LogEntry> parse(String line) {
        Matcher matcher = LOG_LINE.matcher(line);
        if (!matcher.matches()) {
            return Optional.empty();
        }
        return Optional.of(new LogEntry(matcher.group("date"), matcher.group("level"), matcher.group("message")));
    }

    public static List<String> findAllErrorMessages(String log) {
        List<String> messages = new ArrayList<>();
        Matcher matcher = LOG_LINE.matcher(log);
        while (matcher.find()) {
            if ("ERROR".equals(matcher.group("level"))) {
                messages.add(matcher.group("message"));
            }
        }
        return messages;
    }

    public static String extractFirstQuoted(String text) {
        Matcher matcher = QUOTED.matcher(text);
        return matcher.find() ? matcher.group(1) : "";
    }

    public static String maskDigits(String text) {
        return text.replaceAll("\\d", "#");
    }
}
