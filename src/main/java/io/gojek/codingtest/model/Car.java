package io.gojek.codingtest.model;

import java.util.Objects;

public class Car {

  private String registrationId;
  private String color;

  public Car(String registrationId) {
    this(registrationId, null);
  }

  public Car(String registrationId, String color) {
    if (registrationId == null) {
      throw new IllegalArgumentException("Cars without registration not allowed");
    }
    this.registrationId = registrationId;
    this.color = color == null ? "UNKNOWN" : color;
  }

  public String getRegistrationId() {
    return registrationId;
  }

  public String getColor() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Car car = (Car) o;
    return registrationId.equalsIgnoreCase(car.registrationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(registrationId);
  }
}
