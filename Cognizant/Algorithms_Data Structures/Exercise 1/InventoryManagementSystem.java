import java.util.HashMap;
import java.util.Scanner;

class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "ProductID: " + productId + ", Name: " + productName + ", Quantity: " + quantity + ", Price: ₹" + price;
    }
}

class Inventory {
    private HashMap<String, Product> inventoryMap = new HashMap<>();

    public void addProduct(Product product) {
        inventoryMap.put(product.getProductId(), product);
        System.out.println("Product added successfully.\n");
    }

    public void updateProduct(String productId, String name, int quantity, double price) {
        if (inventoryMap.containsKey(productId)) {
            Product p = inventoryMap.get(productId);
            p.setProductName(name);
            p.setQuantity(quantity);
            p.setPrice(price);
            System.out.println("Product updated.\n");
        } else {
            System.out.println("Product not found.\n");
        }
    }

    public void deleteProduct(String productId) {
        if (inventoryMap.remove(productId) != null) {
            System.out.println("Product deleted.\n");
        } else {
            System.out.println("Product not found.\n");
        }
    }

    public void displayAll() {
        if (inventoryMap.isEmpty()) {
            System.out.println("Inventory is empty.\n");
        } else {
            for (Product p : inventoryMap.values()) {
                System.out.println(p);
            }
            System.out.println();
        }
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("------ Inventory Management Menu ------");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View All Products");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String id1 = scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name1 = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty1 = scanner.nextInt();
                    System.out.print("Enter Price: ");
                    double price1 = scanner.nextDouble();
                    inventory.addProduct(new Product(id1, name1, qty1, price1));
                    break;
                case 2:
                    System.out.print("Enter Product ID to Update: ");
                    String id2 = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    String name2 = scanner.nextLine();
                    System.out.print("Enter New Quantity: ");
                    int qty2 = scanner.nextInt();
                    System.out.print("Enter New Price: ");
                    double price2 = scanner.nextDouble();
                    inventory.updateProduct(id2, name2, qty2, price2);
                    break;
                case 3:
                    System.out.print("Enter Product ID to Delete: ");
                    String id3 = scanner.nextLine();
                    inventory.deleteProduct(id3);
                    break;
                case 4:
                    inventory.displayAll();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.\n");
            }
        } while (choice != 5);

        scanner.close();
    }
}