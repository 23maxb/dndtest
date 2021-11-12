import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

      /*  String classToCheck = "barbarian";
        System.out.println(api.getAtribute("classes", "barbarian", "multi_classing", "prerequisites") instanceof ArrayList);
        System.out.println(((Map) (((ArrayList) (api.getAtribute("classes", classToCheck, "multi_classing", "prerequisites"))).get(0))).get("minimum_score"));
   */
        String classToCheck = "barbarian";
        System.out.println(Arrays.asList(PlayerClass.getRequirementsForMultiClassing(classToCheck)));
    }
}