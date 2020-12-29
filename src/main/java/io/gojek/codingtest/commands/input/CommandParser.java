package io.gojek.codingtest.commands.input;

import io.gojek.codingtest.commands.Command;
import io.gojek.codingtest.commands.types.*;
import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class CommandParser {

  public Command parse(String input) {

    if (input == null || input.isEmpty()) {
      throw new IllegalArgumentException("Invalid command");
    }

    String[] tokens = input.trim().split(" ");

    switch (tokens[0].trim()) {

      case "create_parking_lot":
        String arg = tokens[1].trim();
        int capacity = Integer.parseInt(arg);
        return new Create(capacity);

      case "park":
        Car car = new Car(tokens[1].trim(), tokens[2].trim());
        return new Park(car);

      case "leave":
        Slot slot = new Slot(Integer.parseInt(tokens[1].trim()));
        return new Leave(slot);

      case "status":
        return new Status();

      case "registration_numbers_for_cars_with_colour":
        return new FindAllCarsWithColor(tokens[1].trim());

      case "slot_numbers_for_cars_with_colour":
        return new FindAllSlotsForCarWithColor(tokens[1].trim());

      case "slot_number_for_registration_number":
        return new FindSlotForCar(new Car(tokens[1].trim()));
    }

    return null;
  }

  public List<Command> parse(Path file) {

    List<String> allLines;

    try {
      allLines = Files.readAllLines(file);
    } catch (IOException e) {
      return null;
    }

    return allLines.stream().map(this::parse).collect(Collectors.toList());
  }
}
