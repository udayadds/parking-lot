package io.gojek.codingtest.commands.types;

import io.gojek.codingtest.commands.Command;
import io.gojek.codingtest.commands.output.CommandOutputProcessor;
import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;
import io.gojek.codingtest.parkinglot.ParkingLotManager;

public class Park implements Command {

  private Car car;

  public Park(Car car) {
    this.car = car;
  }

  @Override
  public void execute(ParkingLotManager manager, CommandOutputProcessor outputProcessor) {
    Slot slot = manager.park(car);
    outputProcessor.park(car, slot);
  }

  public Car getCar() {
    return car;
  }
}
