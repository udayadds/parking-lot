package io.gojek.codingtest.parkinglot.storage;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory / non-persistent storage for parking lot
 */
public class InMemoryStorageService implements ParkingLotStorageService {

  private final int capacity;
  private SortedSet<Slot> freeSlots = new TreeSet<>();
  private Map<Slot, Car> occupiedSlots = new HashMap<>();
  private Map<Car, Slot> carsToSlots = new HashMap<>();

  public InMemoryStorageService(int capacity) {
    this.capacity = capacity;
    populateFreeSlots(capacity);
  }

  private void populateFreeSlots(int capacity) {
    for (int i = 1; i <= capacity; i++) {
      freeSlots.add(new Slot(i));
    }
  }

  @Override
  public Slot closestFreeSlot() {
    if (freeSlots.isEmpty()) {
      return null;
    }
    return freeSlots.first();
  }

  @Override
  public void add(Slot slot, Car car) {
    occupiedSlots.put(slot, car);
    carsToSlots.put(car, slot);
    freeSlots.remove(slot);
  }

  @Override
  public Slot slotFor(Car car) {
    return carsToSlots.get(car);
  }

  @Override
  public Slot remove(Slot slot) {
    if (!occupiedSlots.containsKey(slot)) {
      return null;
    }
    Car car = occupiedSlots.remove(slot);
    freeSlots.add(slot);
    carsToSlots.remove(car);
    return slot;
  }

  @Override
  public TreeMap<Slot, Car> allOccupiedSlots() {
    return new TreeMap<>(occupiedSlots);
  }

  @Override
  public List<Slot> allSlotsWithColor(String color) {
    return carsToSlots.entrySet().stream()
            .filter(entry -> entry.getKey().getColor().equals(color))
            .map(entry -> entry.getValue())
            .collect(Collectors.toList());
  }

  @Override
  public List<Car> allCarsWithColor(String color) {
    return carsToSlots.keySet().stream()
            .filter(car -> car.getColor().equals(color))
            .collect(Collectors.toList());
  }

  @Override
  public int occupiedSlotCount() {
    return occupiedSlots.size();
  }

  @Override
  public int freeSlotsCount() {
    return freeSlots.size();
  }

}
