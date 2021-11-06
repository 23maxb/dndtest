import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class main {
    public static void main(String[] args) throws Exception {
        String[] a = "str dex con int wis cha".split(" ");
        System.out.println(new ArrayList<>(Arrays.asList(a)));
        Map<String, Object> raceInformation = (Map<String, Object>) (api.getAtribute("races", "human"));
        System.out.println(((ArrayList<?>) raceInformation.get("ability_bonuses")).get(0));
        int i = 0;
        System.out.println(((Map) ((Map) ((ArrayList<?>) raceInformation.get("ability_bonuses")).get(i)).get("ability_score")).get("index") + ":+" + ((Map) ((ArrayList<?>) raceInformation.get("ability_bonuses")).get(i)).get("bonus"));

    }
}