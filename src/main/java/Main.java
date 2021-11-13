import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        String b = "bruh.hi.1";
        String classToAdd = b;
        System.out.println(b.split("\\.")[2]);
        System.out.println(classToAdd.substring(0, classToAdd.lastIndexOf(".")));

        /*
        String b = "barbarian";
        System.out.println(api.getAtribute("classes", b, "hit_die"));*/
    }
}