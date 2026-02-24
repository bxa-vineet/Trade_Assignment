import java.util.concurrent.*;

public class TradingEngine {

    public static void main(String[] args) throws Exception {

        Portfolio portfolio = new Portfolio();

        ExecutorService pool = Executors.newFixedThreadPool(8);

        TradeLoader.load("trades.csv")
                .forEach(trade -> pool.submit(new TradeProcessor(trade, portfolio)));
        
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        ReportService.generate(portfolio);
    }
}
