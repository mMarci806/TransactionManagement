package transaction;

public class BaseAccount {

    private String accountNumber;
    private Currency currency;

    public BaseAccount() {
    }

    public BaseAccount(String accountNumber, Currency currency){
        this.accountNumber = accountNumber;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
