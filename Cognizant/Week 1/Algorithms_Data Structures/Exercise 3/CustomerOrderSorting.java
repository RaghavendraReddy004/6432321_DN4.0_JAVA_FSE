class Order {
    int orderId;
    String customerName;
    double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return String.format("Order ID: %d, Customer: %s, Total Price: $%.2f",
                orderId, customerName, totalPrice);
    }
}

public class CustomerOrderSorting {

    // Bubble Sort implementation (inefficient for large data)
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;  // Array is already sorted
            }
        }
    }

    // Quick Sort implementation (efficient divide-and-conquer)
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    // Utility to print orders array
    public static void printOrders(String title, Order[] orders) {
        System.out.println(title);
        for (Order o : orders) {
            System.out.println(o);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Order[] ordersForBubble = {
            new Order(101, "Alice", 250.50),
            new Order(102, "Bob", 450.00),
            new Order(103, "Charlie", 150.75),
            new Order(104, "David", 300.00),
            new Order(105, "Eva", 120.00)
        };

        // Make a copy for quick sort so original is not affected
        Order[] ordersForQuick = new Order[ordersForBubble.length];
        System.arraycopy(ordersForBubble, 0, ordersForQuick, 0, ordersForBubble.length);

        printOrders("Original Orders:", ordersForBubble);

        bubbleSort(ordersForBubble);
        printOrders("Orders After Bubble Sort (by total price):", ordersForBubble);

        quickSort(ordersForQuick, 0, ordersForQuick.length - 1);
        printOrders("Orders After Quick Sort (by total price):", ordersForQuick);

        System.out.println("Analysis:");
        System.out.println("- Bubble Sort has average and worst-case time complexity of O(n²). It's simple but inefficient for large datasets.");
        System.out.println("- Quick Sort has average time complexity O(n log n) and is much faster in practice for large datasets.");
        System.out.println("- Hence, Quick Sort is generally preferred over Bubble Sort in production systems.");
    }
}