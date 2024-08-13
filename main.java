import java.util.*;

class Car {
    private String carID;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;

    public Car(String carID, String brand, String model, double pricePerDay) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }

    public String getCarID() {
        return carID;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentDays) {
        return pricePerDay * rentDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}

class Customer {
    private String customerID;
    private String customerName;

    public Customer(String customerId, String customerName) {
        this.customerID = customerId;
        this.customerName = customerName;

    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Car is not available for rent.");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentalToRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentalToRemove = rental;
                break;
            }
            if (rentalToRemove != null) {
                rentals.remove(rentalToRemove);
            } else {
                System.out.println("Car was not rented");
            }
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("---Car Rental System---");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.println("\n --Rent a Car--\n");
                System.out.print("Enter your name: ");
                String customerName = sc.nextLine();

                System.out.println("\n Available Cars:");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarID() + " - " + car.getBrand() + " " + car.getModel());
                    }
                }
                System.out.print("Enter the car Id you want to rent: ");
                String carID = sc.nextLine();

                System.out.print("\n Enter the number of days for rental: ");
                int days = sc.nextInt();
                sc.nextLine();

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCarID().equals(carID) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }
                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(days);
                    System.out.println("\n --Rental Information--\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerID());
                    System.out.println("Customer Name: " + newCustomer.getCustomerName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + days);
                    System.out.printf("Total Price: Rs.%.2f%n", totalPrice);

                    System.out.print("\nConfirm Rental(Y/N): ");
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, days);
                        System.out.println("\nCar rented Siccessfully");
                    } else {
                        System.out.println("\nRental cancelled");
                    }
                } else {
                    System.out.println("\n Invalid car selection or car not available for rent");
                }

            } else if (choice == 2) {
                System.out.println("\n--Return a car--\n");
                System.out.print("Enter the car ID you want to return: ");
                String carID = sc.nextLine();

                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarID().equals(carID) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }
                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by" + customer.getCustomerName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing");
                    }
                } else {
                    System.out.println("Invalid car ID or car is not rented");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid choice.");
            }
        }
        System.out.println("Thankyou for using the Car Rental System!");
    }
}

public class main {
    public static void main(String[] args) {
        CarRentalSystem crs = new CarRentalSystem();
        Car car1 = new Car("C001", "Toyota", "Camry", 2000.0);
        Car car2 = new Car("C002", "Honda", "Accord", 2500.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 4000.0);
        crs.addCar(car1);
        crs.addCar(car2);
        crs.addCar(car3);
        crs.menu();
    }
}
