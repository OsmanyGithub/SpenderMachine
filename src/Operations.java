import java.text.DecimalFormat;

public class Operations {

    public double check(double num1, double num2){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(num1 - num2));
    }
}
