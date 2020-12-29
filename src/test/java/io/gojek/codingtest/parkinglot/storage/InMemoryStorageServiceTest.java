package io.gojek.codingtest.parkinglot.storage;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertTrue;

public class InMemoryStorageServiceTest {

  private InMemoryStorageService service;

  @Before
  public void setup() {
    service = new InMemoryStorageService(3);
  }

  @Test
  public void initChecks() {
    assertTrue(service.freeSlotsCount() == 3);
    assertTrue(service.occupiedSlotCount() == 0);
  }

  @Test
  public void closestFreeSlot() {
    service.add(new Slot(1), new Car("KA-01-HH-1234", "RED"));
    service.add(new Slot(3), new Car("KA-01-HH-456", "BLUE"));

    Slot freeSlot = service.closestFreeSlot();
    assertTrue(freeSlot.getId() == 2);
  }

  @Test
  public void closestFreeSlotWhenFull() {
    service.add(new Slot(1), new Car("KA-01-HH-1234", "RED"));
    service.add(new Slot(2), new Car("KA-01-HH-456", "BLACK"));
    service.add(new Slot(3), new Car("KA-04-MM-0001", "BLUE"));

    Slot freeSlot = service.closestFreeSlot();
    assertTrue(freeSlot == null);
  }

  @Test
  public void add() {
    Slot freeSlot = service.closestFreeSlot();
    Car myCar = new Car("KA-04-MM-0001", "BLUE");
    service.add(freeSlot, myCar);

    Slot slot = service.slotFor(myCar);
    assertTrue(slot.getId() == 1);
  }

  @Test
  public void removeUnoccupiedSlot() {
    service.add(new Slot(2), new Car("KA-04-MM-0001"));
    Slot removed = service.remove(new Slot(1));

    assertTrue(removed == null);
    assertTrue(service.freeSlotsCount() == 2);
    assertTrue(service.occupiedSlotCount() == 1);
  }

  @Test
  public void remove() {
    service.add(new Slot(2), new Car("KA-04-MM-0001"));
    service.add(new Slot(3), new Car("KA-04-MM-3332", "PURPLE"));
    Slot removed = service.remove(new Slot(2));

    assertTrue(removed.getId() == 2);
    assertTrue(service.freeSlotsCount() == 2);
    assertTrue(service.occupiedSlotCount() == 1);
  }

  @Test
  public void allOccupiedSlots() {
    Car car1 = new Car("KA-04-MM-3332", "PURPLE");
    Car car2 = new Car("MH-88-MM-1111", "MAGENTA");
    Car car3 = new Car("KA-04-MM-0001");

    service.add(new Slot(3), car3);
    service.add(new Slot(1), car1);
    service.add(new Slot(2), car2);

    TreeMap<Slot, Car> occupiedSlots = service.allOccupiedSlots();
    assertTrue(occupiedSlots.size() == 3);

    Map.Entry<Slot, Car> firstSlot = occupiedSlots.pollFirstEntry();
    assertTrue(firstSlot.getValue().equals(car1));
    assertTrue(firstSlot.getKey().getId() == 1);

    Map.Entry<Slot, Car> secondSlot = occupiedSlots.pollFirstEntry();
    assertTrue(secondSlot.getValue().equals(car2));
    assertTrue(secondSlot.getKey().getId() == 2);

    Map.Entry<Slot, Car> thirdSlot = occupiedSlots.pollFirstEntry();
    assertTrue(thirdSlot.getValue().equals(car3));
    assertTrue(thirdSlot.getKey().getId() == 3);
  }

  @Test
  public void slotsCountChanges() {
    assertTrue(service.occupiedSlotCount() == 0);
    assertTrue(service.freeSlotsCount() == 3);

    service.add(new Slot(1), new Car("KA-01-HH-1234", "RED"));
    assertTrue(service.occupiedSlotCount() == 1);
    assertTrue(service.freeSlotsCount() == 2);

    service.add(new Slot(2), new Car("KA-01-HH-456", "BLACK"));
    service.add(new Slot(3), new Car("KA-04-MM-0001", "BLUE"));
    assertTrue(service.occupiedSlotCount() == 3);
    assertTrue(service.freeSlotsCount() == 0);
  }

  @Test
  public void allSlotsWithColor() {
    service.add(new Slot(1), new Car("KA-01-HH-1234", "RED"));
    service.add(new Slot(2), new Car("KA-01-HH-456", "BLACK"));
    service.add(new Slot(3), new Car("KA-04-MM-0001", "RED"));

    List<Slot> slots = service.allSlotsWithColor("RED");
    assertTrue(slots.size() == 2);
    assertTrue(slots.get(0).getId() == 1);
    assertTrue(slots.get(1).getId() == 3);

    List<Slot> noColorSlots = service.allSlotsWithColor("UNKNOWN");
    assertTrue(noColorSlots.isEmpty());
  }

  @Test
  public void allCarsWithColor() {

    Car car1 = new Car("KA-01-HH-1234", "RED");
    Car car2 = new Car("KA-01-HH-456", "BLACK");
    Car car3 = new Car("KA-04-MM-0001", "RED");

    service.add(new Slot(1), car1);
    service.add(new Slot(2), car2);
    service.add(new Slot(3), car3);

    List<Car> cars = service.allCarsWithColor("RED");
    assertTrue(cars.size() == 2);
    assertTrue(cars.get(0).equals(car1));
    assertTrue(cars.get(1).equals(car3));

    List<Car> noColorCars = service.allCarsWithColor("UNKNOWN");
    assertTrue(noColorCars.isEmpty());
  }
}