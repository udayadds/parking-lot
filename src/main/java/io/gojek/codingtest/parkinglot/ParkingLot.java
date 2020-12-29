package io.gojek.codingtest.parkinglot;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;
import io.gojek.codingtest.parkinglot.storage.InMemoryStorageService;
import io.gojek.codingtest.parkinglot.storage.ParkingLotStorageService;

import java.util.List;
import java.util.TreeMap;

public class ParkingLot {

  private ParkingLotStorageService storageService;
  private int capacity;

  public ParkingLot(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity should be greater than 0");
    }
    this.capacity = capacity;
    storageService = new InMemoryStorageService(capacity);
  }

  public Slot park(Car car) {
    if (storageService.occupiedSlotCount() == capacity) {
      return null;
    }

    Slot closestFreeSlot = storageService.closestFreeSlot();
    storageService.add(closestFreeSlot, car);
    return closestFreeSlot;
  }

  public Slot slotFor(Car car) {
    return storageService.slotFor(car);
  }

  public Slot leave(Slot slot) {
    return storageService.remove(slot);
  }

  public TreeMap<Slot, Car> status() {
    return storageService.allOccupiedSlots();
  }

  public List<Slot> allSlotsWithColor(String color) {
    return storageService.allSlotsWithColor(color);
  }

  public List<Car> allCarsWithColor(String color) {
    return storageService.allCarsWithColor(color);
  }
}
