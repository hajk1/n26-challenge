package ir.hajk1.n26.challenge.model;

/**
 * Author: <a href="mailto:k1.tehrani@gmail.com">Kayvan Tehrani</a>
 * <p>
 * Description: <the description of the class for java doc by those that might use it, please use html if possible>
 */
public class TransactionAmountListPerSecond {

    private long transactionCount;
    private Double totalAmountPerSecond = 0d;
    private Double minAmountPerSecond = 0d;
    private Double maxAmountPerSecond = 0d;

    public long getTransactionCount() {
        return transactionCount;
    }

    public Double getTotalAmountPerSecond() {
        return totalAmountPerSecond;
    }

    public Double getMinAmountPerSecond() {
        return minAmountPerSecond < 0 ? 0 : minAmountPerSecond;
    }

    public Double getMaxAmountPerSecond() {
        return maxAmountPerSecond;
    }

    synchronized public void addNewTransaction(Double newAmount) {
        transactionCount++;
        totalAmountPerSecond += newAmount;
        if (newAmount > maxAmountPerSecond)
            maxAmountPerSecond = newAmount;
        if (minAmountPerSecond == null || minAmountPerSecond == 0)
            minAmountPerSecond = newAmount;
        else if (newAmount < minAmountPerSecond)
            minAmountPerSecond = newAmount;
    }

    public void reset() {
        transactionCount = 0;
        totalAmountPerSecond = 0d;
        minAmountPerSecond = 0d;
        maxAmountPerSecond = 0d;
    }
}
