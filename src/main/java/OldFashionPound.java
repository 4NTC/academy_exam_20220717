import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldFashionPound {

  public static void main(String[] args) {

    String input = "5p 17s 8d - 3p 4s 10d";

    String price = "(\\d+)p\\s(\\d+)s\\s(\\d+)d";
    String sumOrSub = "\\s*(\\d+p\\s\\d+s\\s\\d+d)\\s+([\\+\\-])\\s+(\\d+p\\s\\d+s\\s\\d+d)\\s*";
    String mulOrDiv = "\\s*(\\d+p\\s\\d+s\\s\\d+d)\\s+([\\*/]\\s+(\\d+)\\s*";

    Pattern pattern = Pattern.compile(sumOrSub);
    Matcher matcher = pattern.matcher(input);

    OperationService operationService = new OperationService();

    if (matcher.matches()) {
      String firstPrice = matcher.group(1);
      String op = matcher.group(2);
      String secondPrice = matcher.group(3);

      Pattern digitPattern = Pattern.compile(price);
      Matcher priceMatcher = digitPattern.matcher(firstPrice);

      if (op.equals("+")) {

        int totPenceFirstPrize = 0;
        int totPenceSecondPrize = 0;

        if (priceMatcher.matches()) {
          totPenceFirstPrize = calculateTotPence(priceMatcher, operationService);
        }

        //System.out.println(totPenceFirstPrize);

        priceMatcher = digitPattern.matcher(secondPrice);

        if (priceMatcher.matches()) {
          totPenceSecondPrize = calculateTotPence(priceMatcher, operationService);
        }

        //System.out.println(totPenceSecondPrize);

        int totPencePrize = totPenceFirstPrize + totPenceSecondPrize;

        //System.out.println(totPencePrize);

        Pence pence = new Pence(totPencePrize);

        Pound pound = operationService.penceToPound(pence);
        int remainder = operationService.getRemainder();
        pence = new Pence(remainder);
        Shilling shilling = operationService.penceToShilling(pence);
        remainder = operationService.getRemainder();
        pence = new Pence(remainder);

        System.out.printf("%sp %ss %sd\n", pound.getValue(), shilling.getValue(), pence.getValue());

      }

    }

    pattern = Pattern.compile(mulOrDiv);

    if (matcher.matches()) {

      String firstPrice = matcher.group(1);
      String op = matcher.group(2);
      int num = Integer.parseInt(matcher.group(3));

      Pattern digitPattern = Pattern.compile(price);
      Matcher priceMatcher = digitPattern.matcher(firstPrice);

      int totPencePrize = calculateTotPence(priceMatcher, operationService) * num;

      Pence pence = new Pence(totPencePrize);

      Pound pound = operationService.penceToPound(pence);
      int remainder = operationService.getRemainder();
      pence = new Pence(remainder);
      Shilling shilling = operationService.penceToShilling(pence);
      remainder = operationService.getRemainder();
      pence = new Pence(remainder);

      System.out.printf("%sp %ss %sd\n", pound.getValue(), shilling.getValue(), pence.getValue());

    } else {
      System.out.println("Input not valid");
    }
  }

  public static int calculateTotPence(Matcher priceMatcher, OperationService operationService) {

    Pound pound = new Pound(Integer.parseInt(priceMatcher.group(1)));
    Shilling shilling = new Shilling(Integer.parseInt(priceMatcher.group(2)));
    Pence pence = new Pence(Integer.parseInt(priceMatcher.group(3)));

    Pence totPencePrize = operationService.calculateTotalPence(pound, shilling, pence);

    return totPencePrize.getValue();
  }

}