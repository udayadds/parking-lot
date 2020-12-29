package io.gojek.codingtest.commands.types;

import io.gojek.codingtest.commands.Command;
import io.gojek.codingtest.commands.output.CommandOutputProcessor;
import io.gojek.codingtest.model.Slot;
import io.gojek.codingtest.parkinglot.ParkingLotManager;

import java.util.List;

public class FindAllSlotsForCarWithColor implements Command {

  private String color;

  public FindAllSlotsForCarWithColor(String color) {
    this.color = color;
  }

  @Override
  public void execute(ParkingLotManager manager, CommandOutputProcessor outputProcessor) {
    List<Slot> slots = manager.allSlotsWithColor(getColor());
    outputProcessor.allSlotsWithColor(getColor(), slots);
  }

  public String getColor() {
    return color;
  }
}
