package dataObj;

import dataObj.Pence;
import lombok.Getter;

@Getter
public class Pound {

  // 1 dataObj.Shilling -> 12 dataObj.Pence
  // 1 dataObj.Pound -> 20 Shillings
  // 1 dataObj.Pound -> 240 dataObj.Pence

  // dataObj.Pound Shillings dataObj.Pence

  //5 p -> 1200 d
  //17 s -> 204 d
  //8 d
  // = 1412 d

  //3 p -> 720 d
  //4 s -> 48 d
  //10 d
  // = 778

  // tot -> 2190 d

  private final int value;

  public Pound(int value) {
    this.value = value;
  }

  public Shilling convertToShilling() {
    int shillings = 0;
    int pound = value;

    while (pound > 0) {
      shillings += 20;
      pound--;
    }

    return new Shilling(shillings);
  }

  public Pence convertToPence() {
    int pence = 0;
    int pound = value;

    while (pound > 0) {
      pence += 240;
      pound--;
    }

    return new Pence(pence);
  }

}
