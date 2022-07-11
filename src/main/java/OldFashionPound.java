import dataObj.Pence;
import dataObj.Pound;
import dataObj.Shilling;
import dataObj.ValueOfp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import services.OperationService;

public class OldFashionPound {

  public static void main(String[] args) {

    String input = args[0];

    String price = "(\\d+)p\\s(\\d+)s\\s(\\d+)d";
    String regEx = "\\s*(\\d+p\\s\\d+s\\s\\d+d)\\s+([+\\-\\*/])\\s+(\\d+p\\s\\d+s\\s\\d+d|\\d+)\\s*";

    Pattern pattern = Pattern.compile(regEx);
    Matcher matcher = pattern.matcher(input);

    OperationService operationService = new OperationService();

    if (matcher.matches()){
      pattern = Pattern.compile(price);
      matcher = pattern.matcher(input);
      String firstPrice = "";
      int totalPenceFirstPrice = 0;
      if (matcher.find()){
        firstPrice = matcher.group();
        totalPenceFirstPrice = calculateTotPence(matcher, operationService);
      }
      String newInput = input.replace(firstPrice, "");
      pattern = Pattern.compile("[+\\-*/]");
      matcher = pattern.matcher(newInput);
      if (matcher.find()) {
        String op = matcher.group();
        newInput.replace(op, "");
        switch (op) {
          case "+":
          case "-":
            pattern = Pattern.compile(price);
            matcher = pattern.matcher(newInput);
            int totalPenceSecondPrice = 0;
            if (matcher.find()) {
              totalPenceSecondPrice = calculateTotPence(matcher, operationService);
            }
            int totalPence;
            boolean outOfMoney = false;
            if (op.equals("+")) {
              totalPence = totalPenceFirstPrice + totalPenceSecondPrice;
            } else if (totalPenceFirstPrice >= totalPenceSecondPrice){
              totalPence = totalPenceFirstPrice - totalPenceSecondPrice;
            } else {
              totalPence = totalPenceSecondPrice - totalPenceFirstPrice;
              outOfMoney = true;
            }
            //Final part
            ValueOfp valueOfp = createPrizeFromPenceValue(totalPence, operationService);
            if (outOfMoney) {
              if (valueOfp.getPound().getValue() > 0) {
                System.out.printf("(-%sp %ss %sd)\n", valueOfp.getPound().getValue(), valueOfp.getShilling().getValue(), valueOfp.getPence().getValue());
              } else if (valueOfp.getShilling().getValue() > 0) {
                System.out.printf("%sp (-%ss %sd)\n", valueOfp.getPound().getValue(), valueOfp.getShilling().getValue(), valueOfp.getPence().getValue());
              } else {
                System.out.printf("%sp %ss (-%sd)\n", valueOfp.getPound().getValue(), valueOfp.getShilling().getValue(), valueOfp.getPence().getValue());
              }
            } else {
              System.out.printf("%sp %ss %sd\n", valueOfp.getPound().getValue(), valueOfp.getShilling().getValue(), valueOfp.getPence().getValue());
            }
            break;
          case "*":
          case "/":
            pattern = Pattern.compile("\\d+");
            matcher = pattern.matcher(newInput);
            int num = 1;
            if (matcher.find()) {
              num = Integer.parseInt(matcher.group());
              if (op.equals("*")) {
                totalPence = totalPenceFirstPrice * num;
                valueOfp = createPrizeFromPenceValue(totalPence, operationService);
                System.out.printf("%sp %ss %sd\n", valueOfp.getPound().getValue(), valueOfp.getShilling().getValue(), valueOfp.getPence().getValue());
              } else {
                totalPence = totalPenceFirstPrice / num;
                valueOfp = createPrizeFromPenceValue(totalPence, operationService);
                int penceRemainder = totalPenceFirstPrice % num;
                if (penceRemainder > 0) {
                  ValueOfp remainder = createPrizeFromPenceValue(penceRemainder, operationService);
                  if (remainder.getPound().getValue() > 0) {
                    System.out.printf("%sp %ss %sd (%sp %ss %d)\n", valueOfp.getPound().getValue(), valueOfp.getShilling().getValue(), valueOfp.getPence().getValue(),
                        remainder.getPound().getValue(), remainder.getShilling().getValue(), remainder.getPence().getValue());
                  } else if (remainder.getShilling().getValue() > 0) {
                    System.out.printf("%sp %ss %sd (%ss %sd)\n", valueOfp.getPound().getValue(), valueOfp.getShilling().getValue(), valueOfp.getPence().getValue(),
                        remainder.getShilling().getValue(), remainder.getPence().getValue());
                  } else {
                    System.out.printf("%sp %ss %sd (%sd)\n", valueOfp.getPound().getValue(), valueOfp.getShilling().getValue(), valueOfp.getPence().getValue(),
                        remainder.getPence().getValue());
                  }
                } else {
                  System.out.printf("%sp %ss %sd\n", valueOfp.getPound().getValue(), valueOfp.getShilling().getValue(), valueOfp.getPence().getValue());
                }
              }
            }
            break;
        }
      }
    } else {
      System.out.println("Input Not Valid");
    }
  }

  public static int calculateTotPence(Matcher matcher, OperationService operationService) {

    Pound pound = new Pound(Integer.parseInt(matcher.group(1)));
    Shilling shilling = new Shilling(Integer.parseInt(matcher.group(2)));
    Pence pence = new Pence(Integer.parseInt(matcher.group(3)));

    Pence totPencePrize = operationService.calculateTotalPence(pound, shilling, pence);

    return totPencePrize.getValue();

  }

  public static ValueOfp createPrizeFromPenceValue(int penceValue, OperationService operationService) {

    Pence pence = new Pence(penceValue);
    Pound pound = operationService.penceToPound(pence);
    int remainder = operationService.getRemainder();
    pence = new Pence(remainder);
    Shilling shilling = operationService.penceToShilling(pence);
    remainder = operationService.getRemainder();
    pence = new Pence(remainder);

    return new ValueOfp(pound, shilling, pence);
  }
}