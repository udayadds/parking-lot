package io.gojek.codingtest.parkinglot.storage;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;
import io.gojek.codingtest.parkinglot.ParkingLot;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory / non-persistent storage for parking lot
 */
public class InMemoryStorageService implements ParkingLotStorageService {

  private final List<ParkingFloor> floors = new ArrayList<>();
  private final SortedSet<ParkingFloor> freeFloors = new TreeSet<>();
  private final Map<Car, Slot> carsToSlots = new HashMap<>();
  private int freeSlotCount;
  private int occupiedSlotCount;

  public InMemoryStorageService(int capacity) {
    populateFloors(1, capacity);
    freeSlotCount = capacity;
    occupiedSlotCount = 0;
  }

  public InMemoryStorageService(int floorCount, int floorCapacity) {
    populateFloors(floorCount, floorCapacity);
    freeSlotCount = floorCount * floorCapacity;
    occupiedSlotCount = 0;
  }

  private void populateFloors(int floorCount, int floorCapacity) {
    for (int i=0; i<floorCount; i++){
      floors.add(new ParkingFloor(i, floorCapacity));
    }
  }

  @Override
  public Slot closestFreeSlot() {
    if (freeFloors.isEmpty()) {
      return null;
    }
    return freeFloors.first().closestFreeSlot();
  }

  @Override
  public void add(Slot slot, Car car) {
    ParkingFloor floor = floors.get(slot.getFloorId());
    floor.addCar(slot, car);
    carsToSlots.put(car, slot);
    freeSlotCount--;
    occupiedSlotCount++;
  }

  @Override
  public Slot slotFor(Car car) {
    return carsToSlots.get(car);
  }

  @Override
  public Slot remove(Slot slot) {
    ParkingFloor floor = floors.get(slot.getFloorId());
    Car car = floor.removeCar(slot);
    carsToSlots.remove(car);
    freeSlotCount++;
    occupiedSlotCount--;
    return slot;
  }

  @Override
  public TreeMap<Slot, Car> allOccupiedSlots() {
    TreeMap<Slot, Car> allSlots = new TreeMap<>();
    for (ParkingFloor floor : floors) {
      allSlots.putAll(floor.allOccupiedSlots());
    }
    return allSlots;
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
    return occupiedSlotCount;
  }

  @Override
  public int freeSlotsCount() {
    return freeSlotCount;
  }

}
