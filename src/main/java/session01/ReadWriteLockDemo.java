package session01;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

/*
Good day!

Nice article on the ReentrantRradWriteLock and StampedLock.

I have a quick question and it would be great if you could throw some lights.

Given:
In both ReentrantReadWriteLock and StampedLock, the concurrency design is to support multiple (several in large numbers) readers or one writer (mutual exclusivity between readers and writer) ie., the expectation is that multiple readers almost always keep reading. Chances for zero reader locks or preemptive scheduling for the writer thread may not occur for a long time.

Question:
When there is a writer thread, won’t it get to a stage where it never gets a chance to write or writer thread chances to acquire the lock is heavily delayed? Could this occur frequently in these two type of locks? If so, do you suggest a pattern to avoid this issue?

Appreciate your inputs on this.
 */
public class ReadWriteLockDemo {
  public static void main(String... args) throws InterruptedException {
    testStarvation("ReentrantRWLock", new ReentrantReadWriteLock());
    testStarvation("ReentrantRWLock fair", new ReentrantReadWriteLock(true));
    testStarvation("StampedLock as RWLock", new StampedLock().asReadWriteLock());
    testStarvation("StampedLock", new StampedLock());
  }

  private static void testStarvation(String description, ReadWriteLock rwlock) throws InterruptedException {
    System.out.println(description);
    AtomicBoolean written = new AtomicBoolean(false);
    Runnable readTask = () -> {
      for (int i = 0; i < 10 && !written.get(); i++) {
        rwlock.readLock().lock();
        try {
          sleepQuietly(1000);
        } finally {
          rwlock.readLock().unlock();
        }
      }
    };
    Runnable writeTask = () -> {
      long time = System.nanoTime();
      try {
        rwlock.writeLock().lock();
        try {
          System.out.println("Got write lock");
        } finally {
          rwlock.writeLock().unlock();
        }
        written.set(true);
      } finally {
        time = System.nanoTime() - time;
        System.out.printf("time = %dms%n", (time / 1_000_000));
      }
    };

    Thread readThread1 = new Thread(readTask);
    readThread1.start();
    sleepQuietly(500);
    Thread readThread2 = new Thread(readTask);
    readThread2.start();

    Thread writeThread = new Thread(writeTask);
    writeThread.start();

    readThread1.join();
    readThread2.join();
    writeThread.join();
  }

  private static void testStarvation(String description, StampedLock sl) throws InterruptedException {
    System.out.println(description);
    AtomicBoolean written = new AtomicBoolean(false);
    Runnable readTask = () -> {
      for (int i = 0; i < 10 && !written.get(); i++) {
        long stamp = sl.tryOptimisticRead();
        try {
          retryHoldingLock:
          for (; ; stamp = sl.readLock()) {
            // possibly racy reads
            sleepQuietly(1000);
            if (!sl.validate(stamp)) {
              System.out.println("Failed validate");
              continue retryHoldingLock;
            }
            return;
          }
        } finally {
          if (StampedLock.isReadLockStamp(stamp))
            sl.unlockRead(stamp);
        }
      }
    };
    Runnable writeTask = () -> {
      long time = System.nanoTime();
      try {
        long stamp = sl.writeLock();
        try {
          System.out.println("Got write lock");
        } finally {
          sl.unlockWrite(stamp);
        }
        written.set(true);
      } finally {
        time = System.nanoTime() - time;
        System.out.printf("time = %dms%n", (time / 1_000_000));
      }
    };

    Thread readThread1 = new Thread(readTask);
    readThread1.start();
    sleepQuietly(500);
    Thread readThread2 = new Thread(readTask);
    readThread2.start();

    Thread writeThread = new Thread(writeTask);
    writeThread.start();

    readThread1.join();
    readThread2.join();
    writeThread.join();
  }

  private static void sleepQuietly(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new CancellationException("interrupted");
    }
  }
}
