import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldFashionPound {

  public static void main(String[] args) {

    String input = "5p 17s 8d + 3p 4s 10d";

    String price = "(\\d+)p\\s(\\d+)s\\s(\\d+)d";
    String sumOrDiv = "\\s*(\\d+p\\s\\d+s\\s\\d+d)\\s+([\\+-])\\s+(\\d+p\\s\\d+s\\s\\d+d)\\s*";

    Pattern pattern = Pattern.compile(sumOrDiv);
    Matcher matcher = pattern.matcher(input);

    if (matcher.matches()) {
      String firstPrice = matcher.group(1);
      String op = matcher.group(2);
      String secondPrice = matcher.group(3);

      //SUM

      //Price 1

      Pattern digitPattern = Pattern.compile(price);
      Matcher priceMatcher = digitPattern.matcher(firstPrice);

      int totPenceFirstPrize = 0;
      int totPenceSecondPrize = 0;

      OperationService operationService = new OperationService();

      if (priceMatcher.matches()) {
        totPenceFirstPrize = calculateTotPence(priceMatcher, operationService);
      }

      System.out.println(totPenceFirstPrize);

      //Price 2

      priceMatcher = digitPattern.matcher(secondPrice);

      if (priceMatcher.matches()) {
        totPenceSecondPrize = calculateTotPence(priceMatcher, operationService);
      }

      System.out.println(totPenceSecondPrize);

      int totPencePrize = totPenceFirstPrize + totPenceSecondPrize;

      System.out.println(totPencePrize);

      Pence pence = new Pence(totPencePrize);

      Pound pound = operationService.penceToPound(pence);
      int remainder = operationService.getRemainder();
      pence = new Pence(remainder);
      Shilling shilling = operationService.penceToShilling(pence);
      remainder = operationService.getRemainder();
      System.out.println(remainder);
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