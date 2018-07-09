package ir.hajk1.n26.challenge.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: <a href="mailto:k1.tehrani@gmail.com">Kayvan Tehrani</a>
 *
 * Description: <the description of the class for java doc by those that might use it, please use html if possible>
 */
public class TransactionAmountListPerSecond {

  private List<Double> amountList = new ArrayList<>();
    private Double totalAmountPerSecond;
    private Double minAmountPerSecond;
    private Double maxAmountPerSecond;

  public List<Double> getAmountList() {
    return amountList;
  }

  public void setAmountList(List<Double> amountList) {
    this.amountList = amountList;
  }

    synchronized public void addNewTransaction(Double newAmount) {
        totalAmountPerSecond += newAmount;
        if (newAmount > maxAmountPerSecond)
            maxAmountPerSecond = newAmount;
        if (newAmount < minAmountPerSecond)
            minAmountPerSecond = newAmount;
    }
}
