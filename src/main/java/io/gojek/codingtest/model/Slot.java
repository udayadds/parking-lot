package io.gojek.codingtest.model;

import java.util.Objects;

public class Slot implements Comparable<Slot> {

  private final int id;
  private final int floorId;

  public Slot(int id) {
    this.id = id;
    this.floorId = 1;
  }

  public int getFloorId() {
    return floorId;
  }

  public Slot(int id, int floorId) {
    this.id = id;
    this.floorId = floorId;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Slot slot = (Slot) o;
    return id == slot.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public int compareTo(Slot o) {
    return Integer.compare(id, o.getId());
  }
}
