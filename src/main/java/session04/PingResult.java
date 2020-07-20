package session04;

import java.time.*;

public abstract class PingResult {
  private final SequenceNumber start;
  private SequenceNumber end;

  protected PingResult(int start) {
    this(new SequenceNumber(start));
  }

  protected PingResult(SequenceNumber start) {
    this.start = this.end = start;
  }

  public abstract PingResult success(int seq, double time);
  public abstract PingResult timeout(int seq);


  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PingResult that = (PingResult) o;
    return start.equals(that.start);
  }

  public int hashCode() {
    return start.hashCode();
  }

  public String toString() {
    return "[" + start + ", " + end + "] " +
        Duration.ofSeconds(duration());
  }

  public int duration() {
    return end.seconds() - start.seconds() + 1;
  }

  protected void endSeq(int seq) {
    this.end = new SequenceNumber(seq);
  }
}
