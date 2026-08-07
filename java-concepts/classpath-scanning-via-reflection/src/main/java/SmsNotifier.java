public class SmsNotifier implements Notifier {
    @Override
    public String notify(String message) {
        return "sms: " + message;
    }
}
