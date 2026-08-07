@Component
public class EmailNotifier implements Notifier {
    @Override
    public String notify(String message) {
        return "email: " + message;
    }
}
