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
    int shillingsValue = value;

    while (shillingsValue >= 0) {
      pence += 12;
      shillingsValue--;
    }

    return new Pence(pence);
  }

}
