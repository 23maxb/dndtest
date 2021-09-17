import java.util.ArrayList;
import java.util.List;

public class weapon extends item {
    public boolean isMartial;
    public String weaponType;
    public String damageFormula;
    public ArrayList<String> modifiers;
    public String damageType;

    public weapon(String n, boolean ren, double weight, int c, int d, String wt, String dt, String df, boolean im,
            ArrayList<String> mods) {
        super(n, ren, weight, c, d);
        weaponType = wt;
        damageType = dt;
        damageFormula = df;
        modifiers = mods;
        isMartial = im;
    }

    public weapon(String weaponType) {

        switch (weaponType) {
            case "club":
                this(weaponType, false, 0, 1, 100, weaponType, "bludgeoning", "1d4", false,
                        new ArrayList<String>(List.of("light"))); // ask saahil to help fix here
            case "dagger":
            case "greatclub":
            case "handaxe":
            case "javelin":
            case "lighthammer":
            case "mace":
            case "quarterstaff":
            case "sickle":
            case "spear":
        }
    }
}
