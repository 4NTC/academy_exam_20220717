package dataObj;

import dataObj.Pence;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shilling {

  private final int value;

  public Shilling(int value) {
    this.value = value;
  }

  public Pence convertToPence() {
    int pence = 0;
    int shillings = value;

    while (shillings > 0) {
      pence += 12;
      shillings--;
    }

    return new Pence(pence);
  }

}
