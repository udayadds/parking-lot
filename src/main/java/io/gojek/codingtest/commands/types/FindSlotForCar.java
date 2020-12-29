package io.gojek.codingtest.commands.types;

import io.gojek.codingtest.commands.Command;
import io.gojek.codingtest.commands.output.CommandOutputProcessor;
import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.model.Slot;
import io.gojek.codingtest.parkinglot.ParkingLotManager;

public class FindSlotForCar implements Command {

  private Car car;

  public FindSlotForCar(Car car) {
    this.car = car;
  }

  @Override
  public void execute(ParkingLotManager manager, CommandOutputProcessor outputProcessor) {
    Slot slot = manager.slotFor(getCar());
    outputProcessor.slotFor(getCar(), slot);
  }

  public Car getCar() {
    return car;
  }

}
