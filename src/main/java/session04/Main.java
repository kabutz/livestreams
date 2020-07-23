package session04;

import java.io.*;
import java.net.*;

public class Main {
  public static void main(String... args) throws IOException, URISyntaxException {
    PingAnalyzer analyzer = new PingAnalyzer("session04/pings-2020-07-22.txt");

    System.out.println("All results:");
    analyzer.getPingResults().forEach(System.out::println);

    System.out.println();
    System.out.println("Merged results:");
    analyzer.getMergedPingResults(3).forEach(System.out::println);

    System.out.println();
    System.out.println("Total timeouts: " + analyzer.getTotalNumberOfTimeouts());
  }
}
