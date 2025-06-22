import java.util.Arrays;
import java.util.Comparator;

class Product {
    int productId;
    String productName;
    String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Category: " + category;
    }
}

public class EcommerceSearch {

    public static Product linearSearch(Product[] products, String targetName) {
        for (Product product : products) {
            if (product.productName.equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String targetName) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = products[mid].productName.compareToIgnoreCase(targetName);

            if (compare == 0) {
                return products[mid];
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        // Products for linear search (unsorted)
        Product[] linearArray = {
            new Product(201, "Shoes", "Footwear"),
            new Product(202, "Laptop", "Electronics"),
            new Product(203, "T-shirt", "Clothing"),
            new Product(204, "Watch", "Accessories"),
            new Product(205, "Headphones", "Electronics")
        };

        // Products for binary search (separate array with different IDs)
        Product[] binaryArray = {
            new Product(301, "Shoes", "Footwear"),
            new Product(302, "Laptop", "Electronics"),
            new Product(303, "T-shirt", "Clothing"),
            new Product(304, "Watch", "Accessories"),
            new Product(305, "Headphones", "Electronics")
        };

        // Sort the binary array by productName (needed for binary search)
        Arrays.sort(binaryArray, Comparator.comparing(p -> p.productName.toLowerCase()));

        System.out.println("Available Products for Linear Search:");
        for (Product p : linearArray) {
            System.out.println(p);
        }

        System.out.println("\nPerforming Linear Search for 'Laptop'...");
        Product foundLinear = linearSearch(linearArray, "Laptop");
        if (foundLinear != null) {
            System.out.println("Found via Linear Search: " + foundLinear);
        } else {
            System.out.println("Product not found via Linear Search.");
        }

        System.out.println("\n-----------------------------\n");

        System.out.println("Available Products for Binary Search:");
        for (Product p : binaryArray) {
            System.out.println(p);
        }

        System.out.println("\nPerforming Binary Search for 'Laptop'...");
        Product foundBinary = binarySearch(binaryArray, "Laptop");
        if (foundBinary != null) {
            System.out.println("Found via Binary Search: " + foundBinary);
        } else {
            System.out.println("Product not found via Binary Search.");
        }
    }
}