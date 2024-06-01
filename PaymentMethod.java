
public abstract class PaymentMethod {
    public abstract void pay();
}


class GooglePay extends PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Payment done using Google Pay. Order will arrive after 7 days.");
    }
}


class PhonePe extends PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Payment done using PhonePe. Order will arrive after 7 days.");
    }
}
