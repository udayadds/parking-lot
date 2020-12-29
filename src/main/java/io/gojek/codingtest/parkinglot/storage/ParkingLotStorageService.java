package io.gojek.codingtest.parkinglot.storage;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;

import java.util.List;
import java.util.TreeMap;

/**
 * Storage interface for ParkingLot
 */
public interface ParkingLotStorageService {

  Slot closestFreeSlot();

  void add(Slot slot, Car car);

  int occupiedSlotCount();

  int freeSlotsCount();

  Slot slotFor(Car car);

  Slot remove(Slot slot);

  TreeMap<Slot, Car> allOccupiedSlots();

  List<Slot> allSlotsWithColor(String color);

  List<Car> allCarsWithColor(String color);
}
