package io.gojek.codingtest.model;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class SlotTest {

  @Test
  public void isComparable() {

    List<Slot> slots = new ArrayList<>();
    slots.add(new Slot(5));
    slots.add(new Slot(2));
    slots.add(new Slot(1));

    Collections.sort(slots);

    assertTrue(slots.get(0).getId() == 1);
    assertTrue(slots.get(1).getId() == 2);
    assertTrue(slots.get(2).getId() == 5);
  }

  @Test
  public void hashCodeAndEquals() {

    Set<Slot> slots = new HashSet<>();
    slots.add(new Slot(65));

    assertTrue(slots.contains(new Slot(65)));
  }

}