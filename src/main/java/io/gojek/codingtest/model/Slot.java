package io.gojek.codingtest.model;

import java.util.Objects;

public class Slot implements Comparable<Slot> {

  private int id;

  public Slot(int id) {
    this.id = id;
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
