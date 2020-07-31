package transaction;

public class ClientAccount extends BaseAccount {

   private Double balance;

    public ClientAccount(String accountNumber, Currency currency, Double balance) {
        super(accountNumber, currency);
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }
    // Hozza adja az uj osszeget nem pedig a parameter erteket kapja
    public void setBalance(Double balance) {
        this.balance += balance;
    }
}
