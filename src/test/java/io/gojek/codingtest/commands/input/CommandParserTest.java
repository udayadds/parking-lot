package io.gojek.codingtest.commands.input;

import io.gojek.codingtest.commands.Command;
import io.gojek.codingtest.commands.types.*;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CommandParserTest {

  private CommandParser parser = new CommandParser();

  @Test
  public void create() {
    Command command = parser.parse("create_parking_lot 6");
    assertTrue(command instanceof Create);
    assertTrue(((Create) command).getCapacity() == 6);
  }

  @Test
  public void park() {
    Command command = parser.parse("park KA-01-HH-9999 White");
    assertTrue(command instanceof Park);
    assertTrue(((Park) command).getCar().getColor().equals("White"));
    assertTrue(((Park) command).getCar().getRegistrationId().equals("KA-01-HH-9999"));
  }

  @Test
  public void leave() {
    Command command = parser.parse("leave 6");
    assertTrue(command instanceof Leave);
    assertTrue(((Leave) command).getSlot().getId() == 6);
  }

  @Test
  public void status() {
    Command command = parser.parse("status");
    assertTrue(command instanceof Status);
  }

  @Test
  public void findAllCars() {
    Command command = parser.parse("registration_numbers_for_cars_with_colour White");
    assertTrue(command instanceof FindAllCarsWithColor);
    assertTrue(((FindAllCarsWithColor) command).getColor().equals("White"));
  }

  @Test
  public void findAllSlots() {
    Command command = parser.parse("slot_numbers_for_cars_with_colour White");
    assertTrue(command instanceof FindAllSlotsForCarWithColor);
    assertTrue(((FindAllSlotsForCarWithColor) command).getColor().equals("White"));
  }

  @Test
  public void findSlot() {
    Command command = parser.parse("slot_number_for_registration_number KA-01-HH-3141");
    assertTrue(command instanceof FindSlotForCar);
    assertTrue(((FindSlotForCar) command).getCar().getRegistrationId().equals("KA-01-HH-3141"));
  }

  @Test
  public void parseFile() {
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("sample_input.txt").getFile());

    List<Command> commands = parser.parse(file.toPath());

    assertTrue(commands.size() == 7);

    assertTrue(commands.get(0) instanceof Create);
    assertTrue(((Create) commands.get(0)).getCapacity() == 6);

    assertTrue(commands.get(1) instanceof Park);
    assertTrue(((Park) commands.get(1)).getCar().getColor().equals("Black"));
    assertTrue(((Park) commands.get(1)).getCar().getRegistrationId().equals("KA-01-HH-3141"));

    assertTrue(commands.get(2) instanceof Leave);
    assertTrue(((Leave) commands.get(2)).getSlot().getId() == 4);

    assertTrue(commands.get(3) instanceof Status);

    assertTrue(commands.get(4) instanceof FindAllCarsWithColor);
    assertTrue(((FindAllCarsWithColor) commands.get(4)).getColor().equals("White"));

    assertTrue(commands.get(5) instanceof FindAllSlotsForCarWithColor);
    assertTrue(((FindAllSlotsForCarWithColor) commands.get(5)).getColor().equals("White"));

    assertTrue(commands.get(6) instanceof FindSlotForCar);
    assertTrue(((FindSlotForCar) commands.get(6)).getCar().getRegistrationId().equals("KA-01-HH-3141"));
  }
}