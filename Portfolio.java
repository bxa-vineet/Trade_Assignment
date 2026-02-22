import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Portfolio {

    private final ConcurrentHashMap<Long, ConcurrentHashMap<String, Integer>> holdings = new ConcurrentHashMap<>();

    public void processTrade(Trade trade) {

        holdings.putIfAbsent(trade.accountId(), new ConcurrentHashMap<>());
        ConcurrentHashMap<String, Integer> accountPortfolio = holdings.get(trade.accountId());

        accountPortfolio.compute(trade.symbol(), (sym, qty) -> {
            int current = qty == null ? 0 : qty;
            int newQty = trade.side() == Side.BUY
                    ? current + trade.quantity()
                    : current - trade.quantity();

            if (newQty < 0)
                throw new IllegalStateException("Negative holdings not allowed: " + trade);

            return newQty;
        });
    }

    public Map<Long, ConcurrentHashMap<String, Integer>> snapshot() {
        return holdings;
    }
}