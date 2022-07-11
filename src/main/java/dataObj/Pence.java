package dataObj;

import lombok.Getter;

@Getter
public class Pence {

  private final int value;

  public Pence(int value) {
    this.value = value;
  }

}
