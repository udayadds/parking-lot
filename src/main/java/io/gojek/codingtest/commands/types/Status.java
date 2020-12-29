package io.gojek.codingtest.commands.types;

import io.gojek.codingtest.commands.Command;
import io.gojek.codingtest.commands.output.CommandOutputProcessor;
import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;
import io.gojek.codingtest.parkinglot.ParkingLotManager;

import java.util.TreeMap;

public class Status implements Command {

  @Override
  public void execute(ParkingLotManager manager, CommandOutputProcessor outputProcessor) {
    TreeMap<Slot, Car> output = manager.status();
    outputProcessor.status(output);
  }
}
