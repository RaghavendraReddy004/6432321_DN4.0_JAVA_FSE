import java.util.Arrays;

class Book implements Comparable<Book> {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String toString() {
        return "Book ID: " + bookId + " | Title: " + title + " | Author: " + author;
    }

    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }
}

public class LibraryManagementSystem {
    
    public static void linearSearch(Book[] books, String title) {
        System.out.println("Linear Search Result for \"" + title + "\":");
        boolean found = false;
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                System.out.println(book);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    public static void binarySearch(Book[] books, String title) {
        Arrays.sort(books);
        int low = 0, high = books.length - 1;
        System.out.println("Binary Search Result for \"" + title + "\":");
        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = books[mid].title.compareToIgnoreCase(title);

            if (comparison == 0) {
                System.out.println(books[mid]);
                return;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("Book not found.");
    }

    public static void main(String[] args) {
        Book[] library = {
            new Book(201, "Java: The Complete Reference", "Herbert Schildt"),
            new Book(202, "Effective Java", "Joshua Bloch"),
            new Book(203, "Cognizant Java Training Manual", "Cognizant Academy"),
            new Book(204, "Head First Java", "Kathy Sierra"),
            new Book(205, "Clean Code", "Robert C. Martin"),
            new Book(206, "Introduction to Algorithms", "Thomas H. Cormen"),
            new Book(207, "Cracking the Coding Interview", "Gayle Laakmann McDowell"),
            new Book(208, "Java Concurrency in Practice", "Brian Goetz")
        };

        System.out.println("=== Library Management System ===\n");

        linearSearch(library, "Cognizant Java Training Manual");
        System.out.println();
        binarySearch(library, "Java: The Complete Reference");
    }
}