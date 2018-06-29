package ir.hajk1.n26.challenge.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by k1 on 6/29/18.
 * email:<k1.tehrani@gmail.com>
 */
public class Transaction {
    @NotNull
    @Min(0)
    private Double amount;
    @NotNull
    @Min(0)
    private Long timestamp;

    public Transaction(Double amount, Long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Transaction() {
    }

    public Double getAmount() {
        return amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return amount.equals(that.amount) && timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + timestamp.hashCode();
        return result;
    }
}
