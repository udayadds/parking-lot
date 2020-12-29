package io.gojek.codingtest.parkinglot;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertTrue;

public class ParkingLotTest {

  ParkingLot parkingLot;

  @Before
  public void setup() {
    parkingLot = new ParkingLot(5);
  }

  @Test
  public void initChecks() {
    assertTrue(parkingLot.status().isEmpty());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidCapacityOnInit() {
    parkingLot = new ParkingLot(-1);
  }

  @Test
  public void park() {

    Car myCar = new Car("KA-04-MM-0001", "BLUE");
    Slot slot = parkingLot.park(myCar);

    assertTrue(slot.getId() == 1);
    assertTrue(parkingLot.slotFor(myCar).equals(slot));

    assertTrue(parkingLot.status().size() == 1);
  }

  @Test
  public void basicParkAndLeave() {

    Car car1 = new Car("KA-04-MM-0001", "BLUE");
    Car car2 = new Car("KA-04-MM-0001");
    Car car3 = new Car("KA-04-MM-3332", "PURPLE");
    Car car4 = new Car("KA-99-OO-3332", "INDIGO");

    Slot slot = parkingLot.park(car1);
    Slot slot2 = parkingLot.park(car2);
    Slot slot3 = parkingLot.park(car3);

    assertTrue(slot.getId() == 1);
    assertTrue(slot2.getId() == 2);
    assertTrue(slot3.getId() == 3);

    parkingLot.leave(slot2);
    assertTrue(parkingLot.slotFor(car2) == null);

    parkingLot.park(car4);
    assertTrue(parkingLot.slotFor(car4).getId() == 2);
  }

  @Test
  public void statusCheck() {

    Car car1 = new Car("KA-04-MM-0001", "BLUE");
    Car car2 = new Car("KA-04-MM-0001");
    Car car3 = new Car("KA-04-MM-3332", "PURPLE");
    Car car4 = new Car("KA-99-OO-3332", "INDIGO");

    Slot slot = parkingLot.park(car1);
    Slot slot2 = parkingLot.park(car2);
    Slot slot3 = parkingLot.park(car3);

    parkingLot.leave(slot2);
    parkingLot.park(car4);

    TreeMap<Slot, Car> status = parkingLot.status();

    Map.Entry<Slot, Car> entry = status.pollFirstEntry();
    assertTrue(entry.getKey().getId() == 1);
    assertTrue(entry.getValue().equals(car1));

    entry = status.pollFirstEntry();
    assertTrue(entry.getKey().getId() == 2);
    assertTrue(entry.getValue().equals(car4));

    entry = status.pollFirstEntry();
    assertTrue(entry.getKey().getId() == 3);
    assertTrue(entry.getValue().equals(car3));
  }

  @Test
  public void allSlotsWithColor() {

    Car car1 = new Car("KA-04-MM-0001", "BLUE");
    Car car2 = new Car("KA-04-MM-0001");
    Car car3 = new Car("KA-04-MM-3332", "PURPLE");
    Car car4 = new Car("KA-99-OO-3332", "INDIGO");
    Car car5 = new Car("KA-99-44-9999", "PURPLE");

    Slot slot = parkingLot.park(car1);
    Slot slot2 = parkingLot.park(car2);
    Slot slot3 = parkingLot.park(car3);
    Slot slot4 = parkingLot.park(car4);
    Slot slot5 = parkingLot.park(car5);

    List<Slot> purpleCarSlots = parkingLot.allSlotsWithColor("PURPLE");
    assertTrue(purpleCarSlots.size() == 2);
    assertTrue(purpleCarSlots.get(0).getId() == 3);
    assertTrue(purpleCarSlots.get(1).getId() == 5);

    assertTrue(parkingLot.allCarsWithColor("BLACK").isEmpty());
  }

  @Test
  public void allCarsWithColor() {

    Car car1 = new Car("KA-04-MM-0001", "BLUE");
    Car car2 = new Car("KA-04-MM-0001");
    Car car3 = new Car("KA-04-MM-3332", "PURPLE");
    Car car4 = new Car("KA-99-OO-3332", "INDIGO");
    Car car5 = new Car("KA-99-44-9999", "PURPLE");

    Slot slot = parkingLot.park(car1);
    Slot slot2 = parkingLot.park(car2);
    Slot slot3 = parkingLot.park(car3);
    Slot slot4 = parkingLot.park(car4);
    Slot slot5 = parkingLot.park(car5);

    List<Car> purpleCars = parkingLot.allCarsWithColor("PURPLE");
    assertTrue(purpleCars.size() == 2);
    assertTrue(purpleCars.get(0).equals(car3));
    assertTrue(purpleCars.get(1).equals(car5));

    List<Car> blackCars = parkingLot.allCarsWithColor("BLACK");
    assertTrue(blackCars.isEmpty());
  }
}