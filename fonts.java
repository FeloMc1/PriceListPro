import java.awt.GraphicsEnvironment;
import java.util.Arrays;

public class fonts {
    public static void main(String[] Args){
        String[] fontNames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
System.out.println(Arrays.toString(fontNames));
    }
}
