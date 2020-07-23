package session04;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class PingAnalyzer {
  private final Collection<PingResult> pingResults;

  public PingAnalyzer(String pingFile) throws URISyntaxException, IOException {
    var tempResults = new LinkedHashSet<PingResult>();

    URL resource = PingAnalyzer.class.getClassLoader().getResource(pingFile);
    List<String> stringResults = Files.readAllLines(Path.of(resource.toURI()));
    PingResult current = new StartState();
    for (String result : stringResults) {
      if (!result.contains("DUP!")
          && (result.contains("timeout") || result.contains("time="))) {
        if (result.contains("timeout")) {
          int seq = Integer.parseInt(result.replaceAll(".*icmp_seq ", ""));
          current = current.timeout(seq);
        } else if (result.contains("time=")) {
          int seq = Integer.parseInt(result.replaceAll(".*icmp_seq=", "")
              .replaceAll(" .*", ""));
          double time = Double.parseDouble(
              result.replaceAll(".*time=", "").replaceAll(" .*", ""));
          current = current.success(seq, time);
        }
        tempResults.add(current);
      }
    }
    pingResults = Collections.unmodifiableCollection(tempResults);
  }

  public Collection<PingResult> getPingResults() {
    return pingResults;
  }

  public Collection<PingResult> getMergedPingResults(int timeoutChainThreshold) {
    Collection<PingResult> mergedResults = new ArrayList<>();
    SuccessResult lastSuccess = null;
    for (Iterator<PingResult> it = pingResults.iterator(); it.hasNext(); ) {
      PingResult pingResult = it.next();
      mergedResults.add(pingResult);
      if (pingResult instanceof SuccessResult sr) lastSuccess = sr;
      else if (lastSuccess != null) {
        int duration = pingResult.duration();
        if (duration <= timeoutChainThreshold) {
          if (it.hasNext()) {
            PingResult next = it.next();
            if (next instanceof SuccessResult sr) {
              SuccessResult merged = lastSuccess.merge(sr);
              mergedResults.add(merged);
              mergedResults.remove(lastSuccess);
              mergedResults.remove(pingResult);
              lastSuccess = merged;
            }
          }
        }
      }
    }
    return Collections.unmodifiableCollection(mergedResults);
  }

  public int getTotalNumberOfTimeouts() {
    return pingResults.stream()
        .filter(result -> result instanceof TimeoutResult)
        .mapToInt(PingResult::duration)
        .sum();
  }
}
