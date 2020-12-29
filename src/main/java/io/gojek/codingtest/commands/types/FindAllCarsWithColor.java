package io.gojek.codingtest.commands.types;

import io.gojek.codingtest.commands.Command;
import io.gojek.codingtest.commands.output.CommandOutputProcessor;
import io.gojek.codingtest.model.Car;
import io.gojek.codingtest.parkinglot.ParkingLotManager;

import java.util.List;

public class FindAllCarsWithColor implements Command {

  private String color;

  public FindAllCarsWithColor(String color) {
    this.color = color;
  }

  @Override
  public void execute(ParkingLotManager manager, CommandOutputProcessor outputProcessor) {
    List<Car> cars = manager.allCarsWithColor(getColor());
    outputProcessor.allCarsWithColor(getColor(), cars);
  }

  public String getColor() {
    return color;
  }
}
