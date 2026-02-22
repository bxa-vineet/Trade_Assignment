import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class TradeLoader {

    public static Stream<Trade> load(String file) throws Exception {
        return Files.lines(Path.of(file))
                .skip(1)
                .map(line -> {
                    String[] p = line.split(",");
                    return new Trade(
                            Long.parseLong(p[0]),
                            Long.parseLong(p[1]),
                            p[2],
                            Integer.parseInt(p[3]),
                            Double.parseDouble(p[4]),
                            Side.valueOf(p[5]),
                            LocalDateTime.parse(p[6])
                    );
                });
    }
}