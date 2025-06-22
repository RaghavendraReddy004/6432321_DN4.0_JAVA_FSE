class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id.toUpperCase();
    }

    public String getName() {
        return name;
    }
}

interface CustomerRepository {
    Customer findCustomerById(String id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    public Customer findCustomerById(String id) {
        if (id.equalsIgnoreCase("C001")) {
            return new Customer("C001", "Ravi Kumar");
        } else if (id.equalsIgnoreCase("C002")) {
            return new Customer("C002", "Sneha Reddy");
        } else {
            return new Customer(id, "Unknown Customer");
        }
    }
}

class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void displayCustomerDetails(String id) {
        Customer customer = customerRepository.findCustomerById(id);
        System.out.println("-------------------------------------");
        System.out.println("Customer Details:");
        System.out.println("ID   : [" + customer.getId() + "]");
        System.out.println("Name : " + customer.getName());
        System.out.println("-------------------------------------\n");
    }
}

public class DependencyInjectionExample {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        service.displayCustomerDetails("C001");
        service.displayCustomerDetails("C002");
        service.displayCustomerDetails("C999");
    }
}