package session04;

import java.util.concurrent.atomic.*;

public final class SequenceNumber {
  private final static AtomicInteger nextBatch = new AtomicInteger(-1);
  private final int batch;
  private final int value;

  public SequenceNumber(int value) {
    this.value = value;
    this.batch = value == 0 ? nextBatch.incrementAndGet() : nextBatch.get();
  }

  public int batch() {
    return batch;
  }

  public int value() {
    return value;
  }

  public int seconds() {
    return batch << 16 | value;
  }

  public boolean equals(Object o) {
    return o instanceof SequenceNumber that
        && this.batch == that.batch && this.value == that.value;
  }

  public int hashCode() {
    return seconds();
  }

  public String toString() {
    return value + "." + batch;
  }
}
