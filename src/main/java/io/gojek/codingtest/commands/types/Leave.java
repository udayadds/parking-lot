package io.gojek.codingtest.commands.types;

import io.gojek.codingtest.commands.Command;
import io.gojek.codingtest.commands.output.CommandOutputProcessor;
import io.gojek.codingtest.model.Slot;
import io.gojek.codingtest.parkinglot.ParkingLotManager;

public class Leave implements Command {

  private Slot slot;

  public Leave(Slot slot) {
    this.slot = slot;
  }

  public Slot getSlot() {
    return slot;
  }

  @Override
  public void execute(ParkingLotManager manager, CommandOutputProcessor outputProcessor) {
    Slot slot = manager.leave(getSlot());
    outputProcessor.leave(slot);
  }
}
