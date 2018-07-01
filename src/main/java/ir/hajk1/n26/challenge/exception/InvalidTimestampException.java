package ir.hajk1.n26.challenge.exception;

/**
 * Created by k1 on 6/30/18.
 * email:<k1.tehrani@gmail.com>
 */
public class InvalidTimestampException extends IllegalArgumentException {

  public InvalidTimestampException(String message) {
    super(message);
  }

}