package ir.hajk1.n26.challenge.model;

/**
 * Created by k1 on 9/3/18.
 * email:<k1.tehrani@gmail.com>
 */
public class EnquiryResult {
    private long count;
    private Double sum;
    private Double min;
    private Double max;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public double getAvg() {
        return sum / count;
    }

    public void addCount(long transactionCount) {
        count += transactionCount;
    }

    public void addSum(Double totalAmountPerSecond) {
        sum += totalAmountPerSecond;
    }

    public void checkMax(Double maxAmountPerSecond) {
        if (this.max < maxAmountPerSecond)
            this.max = maxAmountPerSecond;
    }

    public void chekMin(Double minAmountPerSecond) {
        if (minAmountPerSecond < this.min)
            this.min = minAmountPerSecond;
    }
}
