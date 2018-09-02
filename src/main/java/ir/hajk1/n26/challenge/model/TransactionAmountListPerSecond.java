package ir.hajk1.n26.challenge.model;

/**
 * Author: <a href="mailto:k1.tehrani@gmail.com">Kayvan Tehrani</a>
 *
 * Description: <the description of the class for java doc by those that might use it, please use html if possible>
 */
public class TransactionAmountListPerSecond {

    private long transactionCount;
    private Double totalAmountPerSecond;
    private Double minAmountPerSecond = Double.MIN_VALUE;
    private Double maxAmountPerSecond;

    public long getTransactionCount() {
        return transactionCount;
    }

    public Double getTotalAmountPerSecond() {
        return totalAmountPerSecond;
    }

    public Double getMinAmountPerSecond() {
        return minAmountPerSecond;
    }

    public Double getMaxAmountPerSecond() {
        return maxAmountPerSecond;
    }

    synchronized public void addNewTransaction(Double newAmount) {
        transactionCount++;
        totalAmountPerSecond += newAmount;
        if (newAmount > maxAmountPerSecond)
            maxAmountPerSecond = newAmount;
        if (newAmount < minAmountPerSecond)
            minAmountPerSecond = newAmount;
    }

    public void reset() {
        transactionCount = 0;
        totalAmountPerSecond = 0d;
        minAmountPerSecond = Double.MIN_VALUE;
        maxAmountPerSecond = 0d;
    }
}
