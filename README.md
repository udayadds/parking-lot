### Problem Statement

To create a functioning Parking Lot with following features/abilities

- Create a parking lot with N slots
- Park a car
- Leave the slot assigned
- Find car assigned to a particular slot
- Check current status of the parking lot
- Find all cars with a particular color
- Find all slots of cars with particular color

More details about the problem can be found in [this PDF](ParkingLot-1.4.2.pdf)

### Pre-requisites

- Maven 3.x
- Java 8+

### How to run

If running on Unix/Linux, use the following scripts to run the project

- ./bin/setup - This script compiles code, runs tests and builds a fat JAR artifact in target directory
- ./bin/parking_lot - This script runs the fat JAR artifact, thus starting the application

If running on Windows, run fat JAR directly using java -jar target/parking_lot-1.0-SNAPSHOT.jar

#### Interactive mode

If above script is run without any arguments the application will wait for commands to run.
Output of each command is printed in the console.

#### File mode

Above script can also be run with a file path argument, which lists all commands to run.
Output of all commands is printed in the console.


### List of valid commands

- create_parking_lot capacity (eg: create_parking_lot 6)
- park registration_number color (eg: park KA-01-HH-3141 Black)
- leave slot_id (eg: leave 4)
- status
- registration_numbers_for_cars_with_colour color (eg: registration_numbers_for_cars_with_colour White)
- slot_numbers_for_cars_with_colour color (eg: slot_numbers_for_cars_with_colour White)
- slot_number_for_registration_number registration_number (eg: slot_number_for_registration_number MH-04-AY-1111)

### Design Assumptions

- Commands are not executed concurrently. Due to time-constraint nature of the assignment, thread-safety has not been implemented. 
- Class hierarchy is not formed for car/vehicle. Though for further requirement changes, it should be straight forward to implement. 
- ParkingLotManager should ideally be a singleton. Though, without DI (Dependency Injection) support (no frameworks used), singletons make testing difficult. In this case, singleton was chosen as tradeoff over testing.
- For queries like 'find all cars with color white', code iterates over all cars to find the result. When volume makes this prohibitive, listeners can be used instead to subscribe to events and update & cache such results in real-time. 
- Commands will be valid and in correct order. Only limited validations have been implemented for inputs. Eg: Cars without registration numbers are not allowed.
