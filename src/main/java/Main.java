import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(Player.getHitDie(new ArrayList<String>(Collections.singleton("barbarian.gunslinger.5"))));
    }
}