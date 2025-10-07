import java.util.*;

interface Notifier {
    void sendMessage();
}

class EmailNotifier implements Notifier {
    private final String message;

    public EmailNotifier(String message) {
        this.message = message;
    }

    public void sendMessage() {
        System.out.println("Sending Email: " + message);
    }
}

class SMSNotifier implements Notifier {
    private final String message;

    public SMSNotifier(String message) {
        this.message = message;
    }

    public void sendMessage() {
        System.out.println("Sending SMS: " + message);
    }
}

class PushNotifier implements Notifier {
    private final String message;

    public PushNotifier(String message) {
        this.message = message;
    }

    public void sendMessage() {
        System.out.println("Sending Push Notification: " + message);
    }
}


class NotificationManager {
    private final List<Notifier> notifiers;

    public NotificationManager(List<Notifier> notifiers) {
        this.notifiers = notifiers;
    }

    public void notifyAllUsers() {
        for (Notifier notifier : notifiers) {
            notifier.sendMessage();
        }
    }
}

class NotifierFactory{

    public static Notifier getNotifier( String type, String message){
        
        switch (type.toLowerCase()) {
        case "email":
            return new EmailNotifier(message);
        
        case "sms":
            return new SMSNotifier(message);
        
        case "push":
            return  new PushNotifier(message);
        
        default:
            throw new IllegalArgumentException("Invalid notifier type: " + type);
        }
    }
}

class NotificationService{
    private Notifier notifier;
    public void setNotifier(Notifier notifier){
        this.notifier = notifier;
    }

    public void sendNotification(){
        if (notifier == null) {
            System.out.println("No notification strategy selected!");
        } else {
            notifier.sendMessage();
            System.out.println("✅ Notification sent successfully!");

        }
    }
}

public class NotificationSystem {
    public static void main(String args[]) {
        String message = "Hello Bhanu!";

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter notification type: ");
            String choice = sc.nextLine();
        

            try {
                Notifier notifier = NotifierFactory.getNotifier(choice, message);
                NotificationService service = new NotificationService();
                service.setNotifier(notifier);
                service.sendNotification(); // Executes strategy
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
      
    }
}
