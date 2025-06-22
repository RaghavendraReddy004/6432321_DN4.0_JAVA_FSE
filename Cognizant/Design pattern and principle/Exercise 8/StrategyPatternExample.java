import java.util.Scanner;

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolder;

    public CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card [" + cardNumber + "] - Card Holder: " + cardHolder);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal account: " + email);
    }
}

class UpiPayment implements PaymentStrategy {
    private String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using UPI ID: " + upiId);
    }
}

class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        if (strategy == null) {
            System.out.println("No payment method selected.");
        } else {
            strategy.pay(amount);
        }
    }
}

public class StrategyPatternExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaymentContext context = new PaymentContext();

        System.out.println("Enter amount to pay:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Select Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. UPI");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter Card Number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter Card Holder Name: ");
                String cardHolder = scanner.nextLine();
                context.setPaymentStrategy(new CreditCardPayment(cardNumber, cardHolder));
                break;
            case 2:
                System.out.print("Enter PayPal Email: ");
                String email = scanner.nextLine();
                context.setPaymentStrategy(new PayPalPayment(email));
                break;
            case 3:
                System.out.print("Enter UPI ID: ");
                String upiId = scanner.nextLine();
                context.setPaymentStrategy(new UpiPayment(upiId));
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        context.executePayment(amount);
        scanner.close();
    }
}