import lombok.Getter;

@Getter
public class Pence {

  private final int value;

  public Pence(int value) {
    this.value = value;
  }

  /*
  public Pound convertToPound() {
    int pounds = 0;
    int penceValue = value;

    while (penceValue >= 0) {
      pounds += 1;
      penceValue -= 240;
    }

    return new Pound(pounds);
  }
   */

}
