### Car Rental System 

#### Overview
This Java program implements OOPs concept through a car rental system with functionality for renting and returning cars. It consists of four main classes: `Car`, `Customer`, `Rental`, and `CarRentalSystem`.

#### Classes
1. **Car**:
   - Represents a car object with attributes such as `carID`, `brand`, `model`, `pricePerDay`, and `isAvailable`.
   - Provides methods to get car details, calculate rental price, and manage availability (`rent`, `returnCar`).

2. **Customer**:
   - Represents a customer with `customerID` and `customerName`.
   - Used to create new customers for renting cars.

3. **Rental**:
   - Represents a rental transaction associating a `Car` with a `Customer` for a specified number of `days`.

4. **CarRentalSystem**:
   - Manages the main operations of the car rental system.
   - Keeps track of available cars, customers, and ongoing rentals.
   - Includes methods to add cars/customers, rent/return cars, and display a menu for user interaction.

#### How to Use
1. **Adding Cars and Customers**:
   - Create car objects using the `Car` class and add them to the system using `addCar(car)`.
   - Create customer objects using the `Customer` class and add them using `addCustomer(customer)`.

2. **Renting a Car**:
   - Select the option to rent a car from the menu.
   - Input customer details and choose from available cars.
   - Confirm the rental to proceed.

3. **Returning a Car**:
   - Select the option to return a car from the menu.
   - Input the car ID to be returned.
   - The system will verify and process the return.

4. **Menu**:
   - The `menu()` method provides an interactive menu for users to perform operations.
   - Choose options to rent a car, return a car, or exit the system.

#### Running the Program
- The `main` class initializes the `CarRentalSystem` and adds predefined cars (`car1`, `car2`, `car3`).
- Run the `main` class to start the car rental system and interact through the console menu.

