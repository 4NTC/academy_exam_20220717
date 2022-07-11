import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldFashionPound {

  public static void main(String[] args) {

    String input = "";

    String sumOrDiv = "\\s*(\\d+p\\d+s\\d+d)\\s+([\\+-])\\s+(\\d+p\\d+s\\d+d)\\s*";

    Pattern pattern = Pattern.compile(sumOrDiv);
    Matcher matcher = pattern.matcher(input);

    System.out.println(matcher.matches());
  }

}