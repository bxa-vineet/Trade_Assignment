public class TradeProcessor implements Runnable {

    private final Trade trade;
    private final Portfolio portfolio;

    public TradeProcessor(Trade trade, Portfolio portfolio) {
        this.trade = trade;
        this.portfolio = portfolio;
    }

    @Override
    public void run() {
        portfolio.processTrade(trade);
        // persistTrade(trade);   // DB call (optional here)
    }
}