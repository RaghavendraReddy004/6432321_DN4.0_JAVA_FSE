import java.util.*;

interface Stock {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

interface Observer {
    void update(String stockSymbol, double stockPrice);
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockSymbol;
    private double stockPrice;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(stockSymbol, stockPrice);
        }
    }

    public void setStockData(String stockSymbol, double stockPrice) {
        this.stockSymbol = stockSymbol;
        this.stockPrice = stockPrice;
        notifyObservers();
    }
}

class MobileApp implements Observer {
    private String name;

    public MobileApp(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double stockPrice) {
        System.out.println(name + " [MobileApp] - Stock: " + stockSymbol + ", Price: " + stockPrice);
    }
}

class WebApp implements Observer {
    private String name;

    public WebApp(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double stockPrice) {
        System.out.println(name + " [WebApp] - Stock: " + stockSymbol + ", Price: " + stockPrice);
    }
}

public class ObserverPatternExample {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp1 = new MobileApp("Client A");
        Observer webApp1 = new WebApp("Client B");

        stockMarket.registerObserver(mobileApp1);
        stockMarket.registerObserver(webApp1);

        System.out.println("=== First Stock Update ===");
        stockMarket.setStockData("AAPL", 189.95);

        System.out.println("\n=== Second Stock Update ===");
        stockMarket.setStockData("GOOGL", 2820.50);

        System.out.println("\n=== Removing WebApp Observer ===");
        stockMarket.removeObserver(webApp1);

        System.out.println("\n=== Third Stock Update ===");
        stockMarket.setStockData("MSFT", 310.10);
    }
}