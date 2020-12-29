package io.gojek.codingtest.commands;

import io.gojek.codingtest.commands.output.CommandOutputProcessor;
import io.gojek.codingtest.parkinglot.ParkingLotManager;

/**
 * Marker interface to define commands
 */
public interface Command {
  void execute(ParkingLotManager manager, CommandOutputProcessor outputProcessor);
}
