package io.gojek.codingtest.parkinglot;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertTrue;

public class ParkingLotManagerTest {

  ParkingLotManager manager;

  @Before
  public void setup() {
    manager = new ParkingLotManager();
    manager.create(5);
  }

  @Test
  public void initChecks() {
    assertTrue(manager.status().isEmpty());
  }

  @Test
  public void recreateParkingLot() {

    Car car = new Car("KA-04-MM-0001", "BLUE");
    manager.park(car);
    manager.create(5); // this will be ignored
    assertTrue(manager.slotFor(car).getId() == 1);
  }

  @Test
  public void park() {

    Car myCar = new Car("KA-04-MM-0001", "BLUE");
    Slot slot = manager.park(myCar);

    assertTrue(slot.getId() == 1);
    assertTrue(manager.slotFor(myCar).equals(slot));

    assertTrue(manager.status().size() == 1);
  }

  @Test
  public void basicParkAndLeave() {

    Car car1 = new Car("KA-04-MM-0001", "BLUE");
    Car car2 = new Car("KA-04-MM-0001");
    Car car3 = new Car("KA-04-MM-3332", "PURPLE");
    Car car4 = new Car("KA-99-OO-3332", "INDIGO");

    Slot slot = manager.park(car1);
    Slot slot2 = manager.park(car2);
    Slot slot3 = manager.park(car3);

    assertTrue(slot.getId() == 1);
    assertTrue(slot2.getId() == 2);
    assertTrue(slot3.getId() == 3);

    manager.leave(slot2);
    assertTrue(manager.slotFor(car2) == null);

    manager.park(car4);
    assertTrue(manager.slotFor(car4).getId() == 2);
  }

  @Test
  public void statusCheck() {

    Car car1 = new Car("KA-04-MM-0001", "BLUE");
    Car car2 = new Car("KA-04-MM-0001");
    Car car3 = new Car("KA-04-MM-3332", "PURPLE");
    Car car4 = new Car("KA-99-OO-3332", "INDIGO");

    Slot slot = manager.park(car1);
    Slot slot2 = manager.park(car2);
    Slot slot3 = manager.park(car3);

    manager.leave(slot2);
    manager.park(car4);

    TreeMap<Slot, Car> status = manager.status();

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

    Slot slot = manager.park(car1);
    Slot slot2 = manager.park(car2);
    Slot slot3 = manager.park(car3);
    Slot slot4 = manager.park(car4);
    Slot slot5 = manager.park(car5);

    List<Slot> purpleCarSlots = manager.allSlotsWithColor("PURPLE");
    assertTrue(purpleCarSlots.size() == 2);
    assertTrue(purpleCarSlots.get(0).getId() == 3);
    assertTrue(purpleCarSlots.get(1).getId() == 5);

    assertTrue(manager.allCarsWithColor("BLACK").isEmpty());
  }

  @Test
  public void allCarsWithColor() {

    Car car1 = new Car("KA-04-MM-0001", "BLUE");
    Car car2 = new Car("KA-04-MM-0001");
    Car car3 = new Car("KA-04-MM-3332", "PURPLE");
    Car car4 = new Car("KA-99-OO-3332", "INDIGO");
    Car car5 = new Car("KA-99-44-9999", "PURPLE");

    Slot slot = manager.park(car1);
    Slot slot2 = manager.park(car2);
    Slot slot3 = manager.park(car3);
    Slot slot4 = manager.park(car4);
    Slot slot5 = manager.park(car5);

    List<Car> purpleCars = manager.allCarsWithColor("PURPLE");
    assertTrue(purpleCars.size() == 2);
    assertTrue(purpleCars.get(0).equals(car3));
    assertTrue(purpleCars.get(1).equals(car5));

    List<Car> blackCars = manager.allCarsWithColor("BLACK");
    assertTrue(blackCars.isEmpty());
  }

  @Test(expected = IllegalStateException.class)
  public void illegalState() {

    manager = new ParkingLotManager();
    manager.park(new Car("MH-05-1234"));
  }
}