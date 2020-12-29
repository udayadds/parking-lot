package io.gojek.codingtest.commands.types;

import io.gojek.codingtest.commands.Command;
import io.gojek.codingtest.commands.output.CommandOutputProcessor;
import io.gojek.codingtest.parkinglot.ParkingLotManager;

public class Create implements Command {

  int capacity;

  public Create(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public void execute(ParkingLotManager manager, CommandOutputProcessor outputProcessor) {
    manager.create(getCapacity());
    outputProcessor.create(getCapacity(), true);
  }

  public int getCapacity() {
    return capacity;
  }
}
