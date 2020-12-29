package io.gojek.codingtest;

import io.gojek.codingtest.commands.Command;
import io.gojek.codingtest.commands.input.CommandParser;
import io.gojek.codingtest.commands.output.CommandOutputProcessor;
import io.gojek.codingtest.commands.output.ConsoleCommandOutputProcessor;
import io.gojek.codingtest.parkinglot.ParkingLotManager;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ApplicationMain {

  public static void main(String[] args) {

    if (args.length > 0 && !args[0].isEmpty()) {
      String fileName = args[0];
      parseAndExecute(fileName);
    } else {
      readAndExecuteUntilExit();
    }
  }

  private static void readAndExecuteUntilExit() {

    CommandOutputProcessor outputProcessor = new ConsoleCommandOutputProcessor();
    ParkingLotManager manager = new ParkingLotManager();
    CommandParser parser = new CommandParser();

    Scanner scanner = new Scanner(System.in);

    String input = scanner.nextLine();
    while (!input.equalsIgnoreCase("exit")) {
      try {
        Command command = parser.parse(input);
        command.execute(manager, outputProcessor);
      } catch (Exception e) {
        System.out.println("Invalid command");
      }
      input = scanner.nextLine();
    }
  }

  private static void parseAndExecute(String fileName) {

    ClassLoader classLoader = ApplicationMain.class.getClassLoader();
    File file = new File(fileName);

    CommandOutputProcessor outputProcessor = new ConsoleCommandOutputProcessor();
    ParkingLotManager manager = new ParkingLotManager();
    CommandParser parser = new CommandParser();

    List<Command> commands = parser.parse(file.toPath());
    for (Command command : commands) {
      command.execute(manager, outputProcessor);
    }
  }
}
