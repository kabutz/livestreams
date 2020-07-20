package session04;

import java.util.*;

public class SuccessResult extends PingResult {
  private final Collection<Double> times = new ArrayList<>();
  public SuccessResult(int start, double time) {
    super(start);
    times.add(time);
  }

  public PingResult success(int seq, double time) {
    endSeq(seq);
    times.add(time);
    return this;
  }


  public PingResult timeout(int seq) {
    return new TimeoutResult(seq);
  }
}
