package session04;

import java.util.*;

public class SuccessResult extends PingResult {
  private final Collection<Double> times = new ArrayList<>();

  public SuccessResult(int start, double time) {
    super(start);
    times.add(time);
  }

  public SuccessResult(SequenceNumber startSeq) {
    super(startSeq);
  }

  public PingResult success(int seq, double time) {
    endSeq(seq);
    times.add(time);
    return this;
  }


  public PingResult timeout(int seq) {
    return new TimeoutResult(seq);
  }

  public DoubleSummaryStatistics getStats() {
    return times.stream().mapToDouble(Double::doubleValue)
        .summaryStatistics();
  }

  public String toString() {
    var stats = getStats();
    return String.format("%s Up min=%.0f max=%.0f avg=%.0f",
        super.toString(), stats.getMin(), stats.getMax(), stats.getAverage());
  }

  public SuccessResult merge(SuccessResult that) {
    SuccessResult merged = new SuccessResult(this.startSeq());
    merged.endSeq(that.endSeq());
    merged.times.addAll(this.times);
    merged.times.addAll(that.times);
    return merged;
  }
}
