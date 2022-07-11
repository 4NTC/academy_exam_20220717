import lombok.Getter;

@Getter
public class Pound {

  // 1 Shilling -> 12 Pence
  // 1 Pound -> 20 Shillings
  // 1 Pound -> 240 Pence

  // Pound Shillings Pence

  private final int value;

  public Pound(int value) {
    this.value = value;
  }

  public Shilling convertToShilling() {
    int shillings = 0;
    int poundsValue = value;

    while (poundsValue >= 0) {
      shillings += 20;
      poundsValue--;
    }

    return new Shilling(shillings);
  }

  public Pence convertToPence() {
    int pence = 0;
    int poundsValue = value;

    while (poundsValue >= 0) {
      pence += 240;
      poundsValue--;
    }

    return new Pence(pence);
  }

}
