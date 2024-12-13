import java.util.*;

public class ServiceManagementApp {

    private static VehicleServiceQueue serviceQueue = new VehicleServiceQueue("Q001");
    private static NotificationService notificationService = new NotificationService();
    private static Manufacturer manufacturer = new Manufacturer("Auto Parts Co.", "contact@autopartsco.com");
    private static Supplier supplier = new Supplier("Global Supplies Inc.", "contact@globalsupplies.com");
    private static List<Customer> customers = new ArrayList<>();
    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<Mechanic> mechanics = new ArrayList<>();
    private static List<Part> partsInventory = new ArrayList<>();
    private static List<ServiceAppointment> appointments = new ArrayList<>();
    private static List<ServiceReport> reports = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Service Management System ---");
            System.out.println("1. Add Vehicle to Service Queue");
            System.out.println("2. Notify Customer");
            System.out.println("3. Supply Parts");
            System.out.println("4. Deliver Supplies");
            System.out.println("5. Manage Customers");
            System.out.println("6. Manage Mechanics");
            System.out.println("7. Manage Parts Inventory");
            System.out.println("8. Schedule Service Appointment");
            System.out.println("9. Generate Service Report");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addVehicleToServiceQueue(scanner);
                    break;
                case 2:
                    notifyCustomer(scanner);
                    break;
                case 3:
                    supplyParts(scanner);
                    break;
                case 4:
                    deliverSupplies(scanner);
                    break;
                case 5:
                    manageCustomers(scanner);
                    break;
                case 6:
                    manageMechanics(scanner);
                    break;
                case 7:
                    managePartsInventory(scanner);
                    break;
                case 8:
                    scheduleServiceAppointment(scanner);
                    break;
                case 9:
                    generateServiceReport(scanner);
                    break;
                case 10:
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 10);

        scanner.close();
    }

    private static void addVehicleToServiceQueue(Scanner scanner) {
        System.out.print("Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine();
        System.out.print("Enter Vehicle Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        Vehicle vehicle = new Vehicle(vehicleId, model, customer);
        serviceQueue.addVehicle(vehicle);
        vehicles.add(vehicle);

        System.out.println("Vehicle added to the service queue.");
    }

    private static void notifyCustomer(Scanner scanner) {
        System.out.print("Enter Customer ID to notify: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Notification Message: ");
        String message = scanner.nextLine();

        try {
            notificationService.notifyCustomer(customerId, message);
            System.out.println("Notification sent successfully.");
        } catch (Exception e) {
            System.out.println("Failed to notify customer: " + e.getMessage());
        }
    }

    private static void supplyParts(Scanner scanner) {
        System.out.print("Enter parts to supply (comma separated): ");
        String partsInput = scanner.nextLine();
        List<String> parts = List.of(partsInput.split(","));

        try {
            manufacturer.supplyParts(parts);
            System.out.println("Parts supplied successfully.");
        } catch (Exception e) {
            System.out.println("Failed to supply parts: " + e.getMessage());
        }
    }

    private static void deliverSupplies(Scanner scanner) {
        System.out.print("Enter supplies to deliver (comma separated): ");
        String suppliesInput = scanner.nextLine();
        List<String> supplies = List.of(suppliesInput.split(","));

        try {
            supplier.deliverSupplies(supplies);
            System.out.println("Supplies delivered successfully.");
        } catch (Exception e) {
            System.out.println("Failed to deliver supplies: " + e.getMessage());
        }
    }

    private static void manageCustomers(Scanner scanner) {
    System.out.println("\n--- Manage Customers ---");
    System.out.println("1. Add Customer");
    System.out.println("2. View All Customers");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    switch (choice) {
        case 1:
            System.out.print("Enter Customer ID: ");
            String customerId = scanner.nextLine();
            System.out.print("Enter Customer Name: ");
            String customerName = scanner.nextLine();
            System.out.print("Enter Customer Email: ");
            String customerEmail = scanner.nextLine();
            System.out.print("Enter Customer Address: ");
            String customerAddress = scanner.nextLine();

            Customer customer = new Customer(customerId, customerName, customerEmail, customerAddress);
            customers.add(customer);
            System.out.println("Customer added successfully.");
            break;
        case 2:
            System.out.println("Customers List:");
            for (Customer c : customers) {
                System.out.println(c);
            }
            break;
        default:
            System.out.println("Invalid choice.");
        }
    }

    private static void manageMechanics(Scanner scanner) {
        System.out.println("\n--- Manage Mechanics ---");
        System.out.println("1. Add Mechanic");
        System.out.println("2. View All Mechanics");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter Mechanic ID: ");
                String mechanicId = scanner.nextLine();
                System.out.print("Enter Mechanic Name: ");
                String mechanicName = scanner.nextLine();

                Mechanic mechanic = new Mechanic(mechanicId, mechanicName);
                mechanics.add(mechanic);
                System.out.println("Mechanic added successfully.");
                break;
            case 2:
                System.out.println("Mechanics List:");
                for (Mechanic m : mechanics) {
                    System.out.println(m);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void managePartsInventory(Scanner scanner) {
        System.out.println("\n--- Manage Parts Inventory ---");
        System.out.println("1. Add Part");
        System.out.println("2. View All Parts");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter Part ID: ");
                String partId = scanner.nextLine();
                System.out.print("Enter Part Name: ");
                String partName = scanner.nextLine();

                Part part = new Part(partId, partName);
                partsInventory.add(part);
                System.out.println("Part added successfully.");
                break;
            case 2:
                System.out.println("Parts Inventory:");
                for (Part p : partsInventory) {
                    System.out.println(p);
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void scheduleServiceAppointment(Scanner scanner) {
        System.out.print("Enter Vehicle ID for appointment: ");
        String vehicleId = scanner.nextLine();
        System.out.print("Enter Mechanic ID: ");
        String mechanicId = scanner.nextLine();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Vehicle vehicle = findVehicleById(vehicleId);
        Mechanic mechanic = findMechanicById(mechanicId);

        if (vehicle == null || mechanic == null) {
            System.out.println("Vehicle or Mechanic not found.");
            return;
        }

        ServiceAppointment appointment = new ServiceAppointment(vehicle, mechanic, date);
        appointments.add(appointment);
        System.out.println("Service appointment scheduled successfully.");
    }

    private static void generateServiceReport(Scanner scanner) {
        System.out.print("Enter Vehicle ID for report: ");
        String vehicleId = scanner.nextLine();

        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        ServiceReport report = new ServiceReport(vehicle);
        reports.add(report);
        System.out.println("Service report generated successfully.");
        System.out.println(report);
    }

    // Helper methods to find entities by ID
    private static Customer findCustomerById(String customerId) {
        return customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    private static Vehicle findVehicleById(String vehicleId) {
        return vehicles.stream()
                .filter(v -> v.getId().equals(vehicleId))
                .findFirst()
                .orElse(null);
    }

    private static Mechanic findMechanicById(String mechanicId) {
        return mechanics.stream()
                .filter(m -> m.getId().equals(mechanicId))
                .findFirst()
                .orElse(null);
    }
}