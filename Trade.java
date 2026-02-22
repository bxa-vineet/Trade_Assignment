import java.time.LocalDateTime;

enum Side { BUY, SELL }

public record Trade(
        long tradeId,
        long accountId,
        String symbol,
        int quantity,
        double price,
        Side side,
        LocalDateTime timestamp
) {}