package io.gojek.codingtest.model;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class CarTest {

  @Test
  public void hashCodeAndEquals() {

    String registrationId = "ABCD-123";

    Set<Car> cars = new HashSet<>();
    cars.add(new Car(registrationId, "RED"));

    assertTrue(cars.contains(new Car(registrationId, "RED")));
  }

  @Test
  public void unknownColor() {
    Car myCar = new Car("ABCD-123");
    assertTrue("UNKNOWN".equals(myCar.getColor()));
  }

}