import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class PlayerClass {
    public String pClass;
    public int level;
    public String archetype;

    public String getpClass() {
        return pClass;
    }

    public void setpClass(String pClass) {
        this.pClass = pClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public void setClassBonuses(ArrayList<String> classBonuses) {
        this.classBonuses = classBonuses;
    }

    public ArrayList<String> classBonuses;

    public PlayerClass(String class1, String archetype, int level) {
        this.pClass = class1;
        this.archetype = archetype;
        this.level = level;
    }

    public PlayerClass(String class1, String archetype) {
        this(class1, archetype, 1);
    }

    public PlayerClass(String class1, int level) {
        this(class1, "default", 1);
    }

    public PlayerClass(String class1) {
        this(class1, "default", 1);
    }

    public ArrayList<String> getClassBonuses() throws Exception {
        updateBonuses();
        return classBonuses;
    }

    public void updateBonuses() throws Exception {
        classBonuses = getClassBonuses(pClass, archetype, level);
    }

    public static @NotNull ArrayList<String> getClassBonuses(String pClass, String archetype, int level) throws Exception {
        ArrayList<String> a = new ArrayList<String>();
        Map<String, Object> classInformation = (Map<String, Object>) (api.getAtribute("classes", pClass));
        if (archetype.compareTo("default") != 0)
            classInformation.put("archetype", (api.getAtribute("subclasses", archetype)));
        return a;//TODO implement this
    }

    public static boolean canAddClass(Player p, PlayerClass c) {
        try {
            if (c.getpClass().compareTo(p.getPlayerClass().get(0)) == 0)
                return false;
        } catch (Exception E) {
            return true;
        }//TODO implement can multi class
        ArrayList<String> a = new ArrayList<String>();
        //Map<String, Object> classRequirements = (Map<String, Object>) (api.getAtribute("classes", pClass));
        return false;
    }

    public static String[] getRequirementsForMultiClassing(String classToCheck) throws Exception {
        try {
            int size = ((ArrayList) (api.getAtribute("classes", classToCheck, "multi_classing", "prerequisites"))).size();
            String[] reqs = new String[size];
            System.out.println(size);
            for (int i = 0; i < size; i++)
                reqs[i] = (((Map) ((Map) ((ArrayList) (api.getAtribute("classes", classToCheck, "multi_classing", "prerequisites"))).get(i)).get("ability_score")).get("index"))
                        + ":" +
                        ((Map) (((ArrayList) (api.getAtribute("classes", classToCheck, "multi_classing", "prerequisites"))).get(i))).get("minimum_score");
            return reqs;
        } catch (Exception E) {

            int size = ((ArrayList) api.getAtribute("classes", classToCheck, "multi_classing", "prerequisite_options", "from")).size();
            String[] reqs = new String[size + 1];
            reqs[0] = "need:1";
            for (int i = 1; i < size + 1; i++)
                reqs[i] = ((Map) ((Map) ((ArrayList) api.getAtribute("classes", classToCheck, "multi_classing", "prerequisite_options", "from")).get(i - 1)).get("ability_score")).get("index") +
                        ":" +
                        ((Map) (((ArrayList) api.getAtribute("classes", classToCheck, "multi_classing", "prerequisite_options", "from")).get(i - 1))).get("minimum_score");
            return reqs;
        }
    }
}
