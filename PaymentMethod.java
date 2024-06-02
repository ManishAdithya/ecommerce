public abstract class PaymentMethod {
    public abstract void pay();
}

class GooglePay extends PaymentMethod {
    private String upiId;

    public GooglePay(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay() {
        System.out.println("Payment done using Google Pay (UPI ID: " + upiId + "). Order will arrive after 7 days.");
    }
}

class PhonePe extends PaymentMethod {
    private String upiId;

    public PhonePe(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay() {
        System.out.println("Payment done using PhonePe (UPI ID: " + upiId + "). Order will arrive after 7 days.");
    }
}
class paytm extends PaymentMethod {
    private String upiId;

    public paytm(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay() {
        System.out.println("Payment done using paytm (UPI ID: " + upiId + "). Order will arrive after 7 days.");
    }
}

class CreditCard extends PaymentMethod {
    private String accountHolderName;
    private String cardNumber;
    private int cvv;

    public CreditCard(String accountHolderName, String cardNumber, int cvv) {
        this.accountHolderName = accountHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public void pay() {
        System.out.println("Payment done using Credit Card (Account Holder: " + accountHolderName +
                ", Card Number: " + cardNumber + ", CVV: " + cvv + "). Order will arrive after 7 days.");
    }
}

class DebitCard extends PaymentMethod {
    private String accountHolderName;
    private String cardNumber;
    private int cvv;

    public DebitCard(String accountHolderName, String cardNumber, int cvv) {
        this.accountHolderName = accountHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public void pay() {
        System.out.println("Payment done using Debit Card (Account Holder: " + accountHolderName +
                ", Card Number: " + cardNumber + ", CVV: " + cvv + "). Order will arrive after 7 days.");
    }
}