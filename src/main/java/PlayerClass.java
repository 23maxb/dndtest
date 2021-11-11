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
        classBonuses = getClassBonuses(pClass, archetype, level);//TODO fix this error
    }

    public static @NotNull ArrayList<String> getClassBonuses(String pClass, String archetype, int level) throws Exception {
        ArrayList<String> a = new ArrayList<String>();
        Map<String, Object> raceInformation = (Map<String, Object>) (api.getAtribute("classes", pClass));
        return a;//TODO implement this
    }

    public static boolean canAddClass(Player p, PlayerClass c)
    {
        class.getpClass();//TODO implement can multi class
    }
}
