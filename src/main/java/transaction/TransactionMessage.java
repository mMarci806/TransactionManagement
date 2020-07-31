package transaction;

import java.util.Optional;

public class TransactionMessage extends BaseAccount {

    private Double amount = 0.0;
    private Optional<Double> exchangeRate;

    public TransactionMessage() {
    }

    public TransactionMessage(String accountNumber, Currency currency, Double amount, Optional<Double> exchangeRate) {
        super(accountNumber, currency);
        this.amount = amount;
        this.exchangeRate = exchangeRate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Optional<Double> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Optional<Double> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "TransactionMessage{" +
                "amount=" + amount +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
