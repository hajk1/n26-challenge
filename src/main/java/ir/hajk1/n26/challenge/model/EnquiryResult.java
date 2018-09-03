package ir.hajk1.n26.challenge.model;

/**
 * Created by k1 on 9/3/18.
 * email:<k1.tehrani@gmail.com>
 */
public class EnquiryResult {
    private long count;
    private double sum;
    private double min;
    private double max;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAvg() {
        return count == 0 ? 0 : sum / count;
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
        if (this.min == 0)
            this.min = minAmountPerSecond;
        if (minAmountPerSecond < this.min)
            this.min = minAmountPerSecond;
    }
}
