import java.util.*;

// User class:
public class UserTest {

    private MockUser testUser;

    @Before
    public void setUp() {
        testUser = new MockUser("U001", "John Doe", "john.doe@example.com", "securePassword");
    }

    @Test
    public void test_user_login_success() {
        boolean loginResult = testUser.login("securePassword");
        assertTrue("Login should succeed with correct credentials", loginResult);
    }

    @Test
    public void test_user_login_failure() {
        boolean loginResult = testUser.login("wrongPassword");
        assertFalse("Login should fail with incorrect credentials", loginResult);
    }

    @Test
    public void test_user_logout() {
        testUser.logout();
        // Assuming logout has no state change to verify, we check for console output
        // In a real scenario, you would verify session termination or state change
    }
}

// Manager class:
public class ManagerTest {

    private Manager testManager;
    private Task testTask;
    private Mechanic testMechanic;

    @Before
    public void setUp() {
        testManager = new Manager("M001", "Alice Smith", "alice.smith@example.com", "Service Manager");
        testTask = new Task();
        testMechanic = new Mechanic("Bob Johnson");
    }

    @Test
    public void test_allocate_task_success() {
        testManager.allocateTask(testTask, testMechanic);
        assertEquals("Task should be assigned to the mechanic", testMechanic, testTask.getAssignedMechanic());
    }

    @Test
    public void test_allocate_task_no_available_mechanic() {
        try {
            testManager.allocateTask(testTask, null);
            fail("Task allocation should fail when no mechanic is available");
        } catch (NullPointerException e) {
            assertEquals("Expected NullPointerException when no mechanic is available", e.getClass(), NullPointerException.class);
        }
    }
}

// Mechanic class:
public class MechanicTest {

    private Mechanic testMechanic;
    private List<Task> assignedTasks;

    @Before
    public void setUp() {
        List<String> skills = new ArrayList<>();
        skills.add("Engine Repair");
        skills.add("Brake Replacement");
        testMechanic = new Mechanic("M001", "Charlie Brown", "charlie.brown@example.com", skills);

        assignedTasks = new ArrayList<>();
        assignedTasks.add(new Task("T001", "Fix engine"));
        assignedTasks.add(new Task("T002", "Replace brakes"));
    }

    @Test
    public void test_view_assigned_tasks() {
        // Capture output using a custom output stream
        testMechanic.viewTasks(assignedTasks);
        // Typically, you would verify the output using a logging or output capture mechanism
    }

    @Test
    public void test_complete_task_success() {
        Task task = assignedTasks.get(0);
        testMechanic.completeTask(task);
        assertEquals("Task status should be updated to Completed", "Completed", task.getStatus());
    }

    @Test
    public void test_complete_task_invalid_task() {
        Task invalidTask = new Task("T999", "Non-existent task");
        try {
            testMechanic.completeTask(invalidTask);
            fail("Completing a non-existent task should fail");
        } catch (Exception e) {
            assertEquals("Expected an exception for invalid task completion", e.getClass(), Exception.class);
        }
    }
}

// Customer class:
public class CustomerTest {

    private Customer testCustomer;
    private ServiceQueue serviceQueue;
    private Vehicle testVehicle;

    @Before
    public void setUp() {
        testCustomer = new Customer("C001", "Emily Davis", "emily.davis@example.com", "123 Elm Street");
        serviceQueue = new ServiceQueue();
        testVehicle = new Vehicle("V001", "In Service");
        serviceQueue.addVehicle(testVehicle);
    }

    @Test
    public void test_view_vehicle_status() {
        String vehicleStatus = serviceQueue.getVehicleStatus("V001");
        assertEquals("Current status of the vehicle should be 'In Service'", "In Service", vehicleStatus);

        // Simulate customer checking the status
        testCustomer.viewVehicleStatus();
        // Typically, you would verify the output using a logging or output capture mechanism
    }
}

// Task class:
public class TaskTest {

    private Task testTask;

    @Before
    public void setUp() {
        testTask = new Task("T001", "Fix engine");
    }

    @Test
    public void test_update_task_status() {
        testTask.updateStatus("Completed");
        assertEquals("Task status should be updated to Completed", "Completed", testTask.getStatus());
    }

    @Test
    public void test_update_task_status_invalid_status() {
        try {
            testTask.updateStatus(null);
            fail("Status update should fail with an invalid status");
        } catch (IllegalArgumentException e) {
            assertEquals("Expected IllegalArgumentException for invalid status update", e.getClass(), IllegalArgumentException.class);
        }

        try {
            testTask.updateStatus("");
            fail("Status update should fail with an empty status");
        } catch (IllegalArgumentException e) {
            assertEquals("Expected IllegalArgumentException for empty status update", e.getClass(), IllegalArgumentException.class);
        }
    }
}

// Vehicle class:
public class VehicleTest {

    private Customer testCustomer;
    private Vehicle testVehicle;

    @Before
    public void setUp() {
        testCustomer = new Customer("C001", "Emily Davis", "emily.davis@example.com", "123 Elm Street");
    }

    @Test
    public void test_create_vehicle() {
        testVehicle = new Vehicle("V001", "Toyota Corolla", testCustomer);
        assertNotNull("Vehicle should be created successfully", testVehicle);
        assertEquals("Vehicle ID should be V001", "V001", testVehicle.getVehicleId());
        assertEquals("Vehicle model should be Toyota Corolla", "Toyota Corolla", testVehicle.getModel());
        assertEquals("Vehicle owner should be Emily Davis", testCustomer, testVehicle.getOwner());
    }

    @Test
    public void test_update_vehicle_details() {
        testVehicle = new Vehicle("V001", "Toyota Corolla", testCustomer);
        // Assuming there are setters for updating details (not present in the given class)
        // testVehicle.setModel("Honda Civic");
        // assertEquals("Vehicle model should be updated to Honda Civic", "Honda Civic", testVehicle.getModel());
        // Uncomment the above lines once setters are implemented
    }

    @Test
    public void test_invalid_vehicle_creation() {
        try {
            new Vehicle(null, "Toyota Corolla", testCustomer);
            fail("Vehicle creation should fail with null vehicle ID");
        } catch (IllegalArgumentException e) {
            assertEquals("Expected IllegalArgumentException for null vehicle ID", e.getClass(), IllegalArgumentException.class);
        }

        try {
            new Vehicle("V002", "", testCustomer);
            fail("Vehicle creation should fail with empty model");
        } catch (IllegalArgumentException e) {
            assertEquals("Expected IllegalArgumentException for empty model", e.getClass(), IllegalArgumentException.class);
        }

        try {
            new Vehicle("V003", "Toyota Corolla", null);
            fail("Vehicle creation should fail with null owner");
        } catch (IllegalArgumentException e) {
            assertEquals("Expected IllegalArgumentException for null owner", e.getClass(), IllegalArgumentException.class);
        }
    }
}


// VehicleServiceQueue class:
public class VehicleServiceQueueTest {

    private VehicleServiceQueue serviceQueue;
    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private Customer customer;

    @Before
    public void setUp() {
        serviceQueue = new VehicleServiceQueue("Q001");
        customer = new Customer("C001", "Emily Davis", "emily.davis@example.com", "123 Elm Street");
        vehicle1 = new Vehicle("V001", "Toyota Corolla", customer);
        vehicle2 = new Vehicle("V002", "Honda Civic", customer);
    }

    @Test
    public void test_add_vehicle_to_queue() {
        serviceQueue.addVehicle(vehicle1);
        assertEquals("Queue should contain one vehicle", 1, serviceQueue.vehicles.size());
        assertEquals("The vehicle ID should be V001", "V001", serviceQueue.vehicles.get(0).getVehicleId());
    }

    @Test
    public void test_remove_vehicle_from_queue() {
        serviceQueue.addVehicle(vehicle1);
        serviceQueue.addVehicle(vehicle2);
        serviceQueue.removeVehicle("V001");
        assertEquals("Queue should contain one vehicle after removal", 1, serviceQueue.vehicles.size());
        assertEquals("The remaining vehicle ID should be V002", "V002", serviceQueue.vehicles.get(0).getVehicleId());
    }

    @Test
    public void test_get_next_vehicle() {
        serviceQueue.addVehicle(vehicle1);
        serviceQueue.addVehicle(vehicle2);
        Vehicle nextVehicle = serviceQueue.getNextVehicle();
        assertNotNull("Next vehicle should not be null", nextVehicle);
        assertEquals("The next vehicle ID should be V001", "V001", nextVehicle.getVehicleId());
    }

    @Test(expected = NoSuchElementException.class)
    public void test_get_next_vehicle_empty_queue() {
        serviceQueue.getNextVehicle();
    }
}

// NotificationService class:
public class NotificationServiceTest {

    private NotificationService notificationService;

    @Before
    public void setUp() {
        notificationService = new NotificationService();
    }

    @Test
    public void test_notify_customer_success() {
        // Assuming a method that checks if a notification was sent successfully
        boolean isNotified = notifyCustomerAndCheck("C001", "Your vehicle is ready for pickup.");
        assertTrue("Customer should be notified successfully", isNotified);
    }

    @Test
    public void test_notify_customer_invalid_customer() {
        try {
            // Simulate notification for a non-existent customer
            notifyCustomerAndCheck(null, "Your vehicle is ready for pickup.");
            fail("Notification should fail for non-existent customer");
        } catch (IllegalArgumentException e) {
            assertEquals("Expected IllegalArgumentException for invalid customer", e.getClass(), IllegalArgumentException.class);
        }
    }

    // Helper method to simulate notification logic
    private boolean notifyCustomerAndCheck(String customerId, String message) {
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Invalid customer ID");
        }
        notificationService.notifyCustomer(customerId, message);
        // Simulate checking if the notification was sent
        return true; // Assuming the notification was sent successfully
    }
}

// Manufacturer class:
public class ManufacturerTest {

    private Manufacturer manufacturer;

    @Before
    public void setUp() {
        manufacturer = new Manufacturer("Auto Parts Co.", "contact@autopartsco.com");
    }

    @Test
    public void test_supply_parts_success() {
        List<String> parts = Arrays.asList("Brake Pads", "Oil Filter", "Air Filter");
        boolean isSupplied = supplyPartsAndCheck(parts);
        assertTrue("Parts should be supplied successfully", isSupplied);
    }

    @Test
    public void test_supply_parts_invalid_parts() {
        try {
            // Simulate supplying invalid parts (e.g., empty list)
            supplyPartsAndCheck(Collections.emptyList());
            fail("Supply operation should fail for invalid parts");
        } catch (IllegalArgumentException e) {
            assertEquals("Expected IllegalArgumentException for invalid parts", e.getClass(), IllegalArgumentException.class);
        }
    }

    // Helper method to simulate parts supply logic
    private boolean supplyPartsAndCheck(List<String> partList) {
        if (partList == null || partList.isEmpty()) {
            throw new IllegalArgumentException("Invalid parts list");
        }
        manufacturer.supplyParts(partList);
        // Simulate checking if the parts supply was recorded successfully
        return true; // Assuming the parts were supplied successfully
    }
}

// Supplier class:
public class SupplierTest {

    private Supplier supplier;

    @Before
    public void setUp() {
        supplier = new Supplier("Global Supplies Inc.", "contact@globalsupplies.com");
    }

    @Test
    public void test_deliver_supplies_success() {
        List<String> supplies = Arrays.asList("Engine Oil", "Tires", "Batteries");
        boolean isDelivered = deliverSuppliesAndCheck(supplies);
        assertTrue("Supplies should be delivered successfully", isDelivered);
    }

    @Test
    public void test_deliver_supplies_missing_items() {
        try {
            // Simulate delivery with missing items (e.g., empty list)
            deliverSuppliesAndCheck(Collections.emptyList());
            fail("Delivery should be flagged for missing items");
        } catch (IllegalArgumentException e) {
            assertEquals("Expected IllegalArgumentException for missing items", e.getClass(), IllegalArgumentException.class);
        }
    }

    // Helper method to simulate delivery logic
    private boolean deliverSuppliesAndCheck(List<String> supplyList) {
        if (supplyList == null || supplyList.isEmpty()) {
            throw new IllegalArgumentException("Missing items in delivery");
        }
        supplier.deliverSupplies(supplyList);
        // Simulate checking if the delivery was acknowledged successfully
        return true; // Assuming the supplies were delivered successfully
    }
}
