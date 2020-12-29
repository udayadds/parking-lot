package io.gojek.codingtest.parkinglot;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;

import java.util.List;
import java.util.TreeMap;

/**
 * Creates parking lot and delegates subsequent operations to it.
 *
 */
public class ParkingLotManager {

  private ParkingLot parkingLot;

  public void create(int capacity) {
    // allow parking lot creation to happen only once
    if (parkingLot == null) {
      parkingLot = new ParkingLot(capacity);
    }
  }

  public Slot park(Car car) {
    if (parkingLot == null) {
      throw new IllegalStateException("Parking Lot should be created first");
    }
    return parkingLot.park(car);
  }

  public Slot slotFor(Car car) {
    if (parkingLot == null) {
      throw new IllegalStateException("Parking Lot should be created first");
    }
    return parkingLot.slotFor(car);
  }

  public Slot leave(Slot slot) {
    if (parkingLot == null) {
      throw new IllegalStateException("Parking Lot should be created first");
    }
    return parkingLot.leave(slot);
  }

  public TreeMap<Slot, Car> status() {
    if (parkingLot == null) {
      throw new IllegalStateException("Parking Lot should be created first");
    }
    return parkingLot.status();
  }

  public List<Slot> allSlotsWithColor(String color) {
    if (parkingLot == null) {
      throw new IllegalStateException("Parking Lot should be created first");
    }
    return parkingLot.allSlotsWithColor(color);
  }

  public List<Car> allCarsWithColor(String color) {
    if (parkingLot == null) {
      throw new IllegalStateException("Parking Lot should be created first");
    }
    return parkingLot.allCarsWithColor(color);
  }
}
