package io.gojek.codingtest.commands.output;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;

import java.util.List;
import java.util.TreeMap;

/**
 * Defines output processing for each command
 */
public interface CommandOutputProcessor {

  public void create(int capacity, boolean created);

  public void park(Car car, Slot slot);

  public void slotFor(Car car, Slot slot);

  public void leave(Slot slot);

  public void status(TreeMap<Slot, Car> occupiedSlots);

  public void allSlotsWithColor(String color, List<Slot> slots);

  public void allCarsWithColor(String color, List<Car> cars);
}
