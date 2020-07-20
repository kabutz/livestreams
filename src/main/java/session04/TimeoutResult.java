package session04;

public class TimeoutResult extends PingResult {
  public TimeoutResult(int seq) {
    super(seq);
  }

  public PingResult success(int seq, double time) {
    return new SuccessResult(seq, time);
  }

  public PingResult timeout(int seq) {
    endSeq(seq);
    return this;
  }

  public String toString() {
    return super.toString() + " Down";
  }
}
