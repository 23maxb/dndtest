import java.util.ArrayList;
import java.util.List;

public class weapon extends item {
    public boolean isMartial;
    public String weaponType;
    public String damageFormula;
    public ArrayList<String> modifiers;
    public String damageType;
    public boolean magical;
    public int range;
    public String Ammo;

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getLongRange() {
        return longRange;
    }

    public void setLongRange(int longRange) {
        this.longRange = longRange;
    }

    public int longRange;

    public boolean isMartial() {
        return isMartial;
    }

    public boolean isMagical() {
        return magical;
    }

    public void setMagicgal(boolean martial) {
        this.magical = martial;
    }

    public void setMartial(boolean martial) {
        isMartial = martial;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getDamageFormula() {
        return damageFormula;
    }

    public void setDamageFormula(String damageFormula) {
        this.damageFormula = damageFormula;
    }

    public ArrayList<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(ArrayList<String> modifiers) {
        this.modifiers = modifiers;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public weapon(String n, boolean ren, double weight, int count, int durability, String wt, String dt, String df,
            boolean im, ArrayList<String> mods, boolean magic, int range, int longrange) {
        super(n, ren, weight, count, durability);
        weaponType = wt;
        damageType = dt;
        damageFormula = df;
        modifiers = mods;
        isMartial = im;
        magical = magic;
        this.range = range;
        this.longRange = longrange;
    }

    public weapon(String n, boolean ren, double weight, int count, int durability, String wt, String dt, String df,
            boolean im, ArrayList<String> mods, boolean magic) {
        this(n, ren, weight, count, durability, wt, dt, df, im, mods, magic, 5, 5);
    }

    public weapon(String weaponType) {
        this(weaponType, false, 0, 1, 0, weaponType, "", "", false, null, false);
        switch (weaponType) {
            case "club":
                setWeaponType(weaponType);
                setWeight(2);
                setDamageFormula("1d4");
                setDamageType("bludgeoning");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of("light")));

                break;
            case "dagger":
                setWeaponType(weaponType);
                setWeight(2);
                setDamageFormula("1d4");
                setDamageType("bludgeoning");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of("light")));
                break;
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
