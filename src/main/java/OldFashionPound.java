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

      Pence totPenceFirstPrize = null;

      OperationService operationService = new OperationService();

      if (priceMatcher.matches()) {

        System.out.println(priceMatcher.group());

        Pound pound = new Pound(Integer.parseInt(priceMatcher.group(1)));
        Shilling shilling = new Shilling(Integer.parseInt(priceMatcher.group(2)));
        Pence pence = new Pence(Integer.parseInt(priceMatcher.group(3)));

        operationService.setPound(pound);
        operationService.setShilling(shilling);
        operationService.setPence(pence);

        totPenceFirstPrize = operationService.calculateTotalPence();
        System.out.println(totPenceFirstPrize.getValue());


      }

      System.out.println(operationService.penceToPound(totPenceFirstPrize).getValue());
      System.out.println(operationService.getRemainder());

      //Price 2

      priceMatcher = digitPattern.matcher(secondPrice);

      if (priceMatcher.matches()) {

        System.out.println(priceMatcher.group());

        Pound pound = new Pound(Integer.parseInt(priceMatcher.group(1)));
        Shilling shilling = new Shilling(Integer.parseInt(priceMatcher.group(2)));
        Pence pence = new Pence(Integer.parseInt(priceMatcher.group(3)));

        operationService.setPound(pound);
        operationService.setShilling(shilling);
        operationService.setPence(pence);

        Pence totPenceFirstPrize1 = operationService.calculateTotalPence();
        System.out.println(totPenceFirstPrize1.getValue());

      }



    } else {
      System.out.println("Input not valid");
    }
  }

}