import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// Abstract class User
abstract class User {
    private String userId;
    private String name;
    private String contactInfo;

    public User(String userId, String name, String contactInfo) {
        if (userId == null || name == null || contactInfo == null) {
            throw new IllegalArgumentException("User details cannot be null.");
        }
        this.userId = userId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void login() {
        System.out.println(name + " logged in.");
    }

    public void logout() {
        System.out.println(name + " logged out.");
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

// Manager class inheriting from User
class Manager extends User {
    private String role;

    public Manager(String userId, String name, String contactInfo, String role) {
        super(userId, name, contactInfo);
        this.role = role;
    }

    public void allocateTask(Task task, Mechanic mechanic) {
        task.assignToMechanic(mechanic);
        System.out.println("Task allocated to mechanic: " + mechanic.getName());
    }

    public void viewReports() {
        System.out.println("Viewing reports...");
        // Implementation for viewing reports
    }

    // Getter
    public String getRole() {
        return role;
    }
}

// Mechanic class inheriting from User
class Mechanic extends User {
    private List<String> skills;

    public Mechanic(String userId, String name, String contactInfo, List<String> skills) {
        super(userId, name, contactInfo);
        this.skills = skills;
    }

    public void viewTasks(List<Task> tasks) {
        System.out.println("Tasks assigned to " + getName() + ":");
        for (Task task : tasks) {
            System.out.println(task.getDescription() + " - " + task.getStatus());
        }
    }

    public void completeTask(Task task) {
        task.updateStatus("Completed");
        System.out.println("Task " + task.getTaskId() + " completed.");
    }

    // Getter
    public List<String> getSkills() {
        return skills;
    }
}

// Customer class inheriting from User
class Customer extends User {
    private String address;

    public Customer(String userId, String name, String contactInfo, String address) {
        super(userId, name, contactInfo);
        this.address = address;
    }

    public void viewVehicleStatus() {
        System.out.println("Checking vehicle status...");
        // Implementation to check the status of the vehicle in the queue
    }

    // Getter
    public String getAddress() {
        return address;
    }
}

// Task class
class Task {
    private String taskId;
    private String description;
    private String status;
    private Mechanic assignedMechanic;

    public Task(String taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = "Pending";
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public void assignToMechanic(Mechanic mechanic) {
        this.assignedMechanic = mechanic;
    }

    // Getter for assigned mechanic
    public Mechanic getAssignedMechanic() {
        return assignedMechanic;
    }
}

// Vehicle class
class Vehicle {
    private String vehicleId;
    private String model;
    private Customer owner;

    public Vehicle(String vehicleId, String model, Customer owner) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.owner = owner;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public Customer getOwner() {
        return owner;
    }

    public void getServiceHistory() {
        System.out.println("Retrieving service history for vehicle " + vehicleId);
        // Implementation to retrieve service history
    }
}

// VehicleServiceQueue class
class VehicleServiceQueue {
    private String queueId;
    private List<Vehicle> vehicles = new ArrayList<>();

    public VehicleServiceQueue(String queueId) {
        this.queueId = queueId;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        System.out.println("Vehicle " + vehicle.getVehicleId() + " added to the queue.");
    }

    public void removeVehicle(String vehicleId) {
        vehicles.removeIf(vehicle -> vehicleId.equals(vehicle.getVehicleId()));
        System.out.println("Vehicle " + vehicleId + " removed from the queue.");
    }

    public Vehicle getNextVehicle() {
        if (vehicles.isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return vehicles.get(0);
    }
}

// NotificationService class
class NotificationService {
    public void notifyCustomer(String customerId, String message) {
        System.out.println("Notification sent to customer " + customerId + ": " + message);
    }
}

// Manufacturer class
class Manufacturer {
    private String name;
    private String contactInfo;

    public Manufacturer(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void supplyParts(List<String> partList) {
        System.out.println("Supplying parts: " + partList);
        // Implementation to supply parts
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

// Supplier class
class Supplier {
    private String name;
    private String contactInfo;

    public Supplier(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void deliverSupplies(List<String> supplyList) {
        System.out.println("Delivering supplies: " + supplyList);
        // Implementation to deliver supplies
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}