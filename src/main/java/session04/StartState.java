package session04;

public class StartState extends PingResult {
  public StartState() {
    super(-1);
  }

  public PingResult success(int seq, double time) {
    return new SuccessResult(seq, time);
  }

  public PingResult timeout(int seq) {
    return new TimeoutResult(seq);
  }
}