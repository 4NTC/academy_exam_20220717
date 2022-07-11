import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OperationService {

  private Pound pound;
  private Shilling shilling;
  private Pence pence;
  private int remainder;

  public Pence calculateTotalPence() {
    Pence poundToPence = pound.convertToPence();
    Pence shillingToPence = shilling.convertToPence();
    return new Pence(poundToPence.getValue() + shillingToPence.getValue() + pence.getValue());
  }

  public Pound penceToPound(Pence value) {
    int pound = 0;
    int pence = value.getValue();

    while (pence >= 240) {
      pound++;
      pence -= 240;
    }

    remainder = pence;

    return new Pound(pound);
  }


}
