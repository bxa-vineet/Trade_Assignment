import java.util.*;
import java.util.stream.Collectors;

public class ReportService {

    public static void generate(Portfolio portfolio) {

        var snapshot = portfolio.snapshot();

        System.out.println("\n===== PORTFOLIO SUMMARY =====");

        snapshot.forEach((acc, map) -> {
            System.out.println("Account: " + acc);
            map.forEach((sym, qty) ->
                    System.out.println("   " + sym + " → " + qty));
        });

        System.out.println("\n===== TOP TRADED SYMBOLS =====");

        snapshot.values().stream()
                .flatMap(m -> m.keySet().stream())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}