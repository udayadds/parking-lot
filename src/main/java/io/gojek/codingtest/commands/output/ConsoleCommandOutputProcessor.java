package io.gojek.codingtest.commands.output;

import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ConsoleCommandOutputProcessor implements CommandOutputProcessor {

  @Override
  public void create(int capacity, boolean created) {
    if (created) {
      System.out.println("Created a parking lot with " + capacity + " slots");
    } else {
      System.out.println("Unable to create parking lot");
    }
  }

  @Override
  public void park(Car car, Slot slot) {
    if (slot != null) {
      System.out.println("Allocated slot number: " + slot.getId());
    } else {
      System.out.println("Sorry, parking lot is full");
    }
  }

  @Override
  public void slotFor(Car car, Slot slot) {
    if (slot != null) {
      System.out.println(slot.getId());
    } else {
      System.out.println("Not found");
    }
  }

  @Override
  public void leave(Slot slot) {
    System.out.println("Slot number " + slot.getId() + " is free");
  }

  @Override
  public void status(TreeMap<Slot, Car> occupiedSlots) {

    if (occupiedSlots == null) {
      return;
    }

    System.out.println("Slot No.  Registration No  Colour");
    for (int i = 0; i < occupiedSlots.size(); i++) {
      Map.Entry<Slot, Car> entry = occupiedSlots.pollFirstEntry();
      System.out.print(entry.getKey().getId());
      System.out.print("         ");
      System.out.print(entry.getValue().getRegistrationId());
      System.out.print("    ");
      System.out.print(entry.getValue().getColor());
      System.out.println();
    }
  }

  @Override
  public void allSlotsWithColor(String color, List<Slot> slots) {
    if (slots != null && !slots.isEmpty()) {
      String output = slots.stream()
              .map(Slot::getId)
              .map(i -> i.toString())
              .collect(Collectors.joining(", "));
      System.out.println(output);
    } else {
      System.out.println("No such cars found");
    }
  }

  @Override
  public void allCarsWithColor(String color, List<Car> cars) {
    if (cars != null && !cars.isEmpty()) {
      String output = cars.stream()
              .map(Car::getRegistrationId)
              .collect(Collectors.joining(", "));
      System.out.println(output);
    } else {
      System.out.println("No such cars found");
    }
  }
}
