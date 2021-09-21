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
    public item ammo;

    public item getAmmo() {
        return ammo;
    }

    public void setAmmo(item o) {
        ammo = o;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isRanged() {
        return range > 5;
    }

    public boolean isMelee() {
        return !isRanged();
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
        return getDamageFormula(false);
    }

    public String getDamageFormula(boolean twohanded) {
        if (twohanded) {
            for (int i = 0; i < modifiers.size(); i++) {
                if (modifiers.get(i).indexOf("versatile") != -1) {
                    return modifiers.get(i).substring(modifiers.get(i).indexOf("versatile: ") + 11);
                }
            }
        }
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
            boolean im, ArrayList<String> mods, boolean magic, int range, int longrange, item ammo) {
        super(n, ren, weight, count, durability);
        weaponType = wt;
        damageType = dt;
        damageFormula = df;
        modifiers = mods;
        isMartial = im;
        magical = magic;
        this.range = range;
        this.longRange = longrange;
        this.ammo = ammo;
    }

    public weapon(String n, boolean ren, double weight, int count, int durability, String wt, String dt, String df,
            boolean im, ArrayList<String> mods, boolean magic) {
        this(n, ren, weight, count, durability, wt, dt, df, im, mods, magic, 5, 5, null);
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

                break;// this will exit the switch statement (basically we skip comparisons of every
                      // other weapon after the one we have is found)
            case "dagger":
                setWeaponType(weaponType);
                setWeight(1);
                setDamageFormula("1d4");
                setDamageType("piercing");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of("light", "light", "thrown: 20/30")));

                break;
            case "greatclub":
                setWeaponType(weaponType);
                setWeight(10);
                setDamageFormula("1d8");
                setDamageType("bludgeoning");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of("twohanded")));

                break;
            case "handaxe":
                setWeaponType(weaponType);
                setWeight(2);
                setDamageFormula("1d6");
                setDamageType("slashing");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of("light", "thrown: 20/60")));

                break;
            case "javelin":
                setWeaponType(weaponType);
                setWeight(2);
                setDamageFormula("1d6");
                setDamageType("piercing");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of("thrown: 20/60")));

                break;
            case "lighthammer":
                setWeaponType(weaponType);
                setWeight(2);
                setDamageFormula("1d4");
                setDamageType("bludgeoning");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of("light", "thrown: 20/60")));

                break;
            case "mace":
                setWeaponType(weaponType);
                setWeight(4);
                setDamageFormula("1d6");
                setDamageType("bludgeoning");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of()));

                break;
            case "quarterstaff":
                setWeaponType(weaponType);
                setWeight(4);
                setDamageFormula("1d6");
                setDamageType("bludgeoning");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of("versatile: 1d8")));

                break;
            case "sickle":
                setWeaponType(weaponType);
                setWeight(2);
                setDamageFormula("1d4");
                setDamageType("slashing");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of("light")));

                break;
            case "spear":
                setWeaponType(weaponType);
                setWeight(2);
                setDamageFormula("1d6");
                setDamageType("piercing");
                setMartial(false);
                setModifiers(new ArrayList<String>(List.of("thrown: 20/60", "versatile: 1d8")));

                break;
        }

        // checking if the object is thrown then making the ammo itself
        for (int i = 0; i < getModifiers().size(); i++) {
            if (getModifiers().get(i).indexOf("thrown") != -1)
                setAmmo(this);
        }
    }
}
