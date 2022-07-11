package dataObj;

import dataObj.Pence;
import dataObj.Pound;
import dataObj.Shilling;
import lombok.Getter;

@Getter
public class ValueOfp {

  private final Pound pound;
  private final Shilling shilling;
  private final Pence pence;


  public ValueOfp(Pound pound, Shilling shilling, Pence pence) {
    this.pound = pound;
    this.shilling = shilling;
    this.pence = pence;
  }
}
