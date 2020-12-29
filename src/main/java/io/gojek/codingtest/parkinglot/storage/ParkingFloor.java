package io.gojek.codingtest.parkinglot.storage;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;
import io.gojek.codingtest.parkinglot.storage.InMemoryStorageService;
import io.gojek.codingtest.parkinglot.storage.ParkingLotStorageService;

import java.util.*;

public class ParkingFloor implements Comparable<ParkingFloor> {

  private final int floorId;
  private final SortedSet<Slot> freeSlots = new TreeSet<>();
  private final Map<Slot, Car> occupiedSlots = new HashMap<>();
//  private final Map<Car, Slot> carsToSlots = new HashMap<>();


  public ParkingFloor(int floorId, int capacity) {
    this.floorId = floorId;
    populateFreeSlots(capacity);
  }

  private void populateFreeSlots(int capacity) {
    for (int i = 1; i <= capacity; i++) {
      freeSlots.add(new Slot(i, floorId));
    }
  }

  public int getFloorId() {
    return floorId;
  }

  @Override
  public int compareTo(ParkingFloor o) {
    return Integer.compare(floorId, o.getFloorId());
  }

  public Slot closestFreeSlot() {
    if (freeSlots.isEmpty()) {
      return null;
    }
    return freeSlots.first();
  }

  public void addCar(Slot slot, Car car) {
    occupiedSlots.put(slot, car);
    freeSlots.remove(slot);
  }

  public Car removeCar(Slot slot) {
    if (!occupiedSlots.containsKey(slot)) {
      return null;
    }
    Car car = occupiedSlots.remove(slot);
    freeSlots.add(slot);
    return car;
  }

  public TreeMap<Slot, Car> allOccupiedSlots() {
    return new TreeMap<>(occupiedSlots);
  }
}
