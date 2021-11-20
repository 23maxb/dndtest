import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Spell extends Action {
    public int level;
    public int casttime;
    public int range;// unit is feet
    public int duration;
    public String desc;
    public String damageFormula;
    public String damageType;
    public String additionalLevelIncrease;
    public String savingThrowType;
    public String savingThrowModifier;// this should be what to multiply the damage by
    // IF THE SAVING THROW IS SUCCESSFUL
    // should be *0.5 if the damage is halved on success

    public Spell(String name, boolean isAttack, int range, int level, int casttime, int duration, String desc,
                 String damageFormula, String damageType) {
        super(name, isAttack);
        this.level = level;
        this.casttime = casttime;
        this.range = range;
        this.duration = duration;
        this.desc = desc;
        this.damageFormula = damageFormula;
        this.damageType = damageType;
    }

    public Spell(String name, boolean isAttack, int range, int level, int casttime, int duration, String desc,
                 String damageFormula, String damageType, String additionalLevelIncrease) {
        this(name, isAttack, range, level, casttime, duration, desc, damageFormula, damageType);
        this.additionalLevelIncrease = additionalLevelIncrease;
    }

    public Spell(String name, boolean isAttack, int range, int level, int casttime, int duration, String desc,
                 String damageFormula, String damageType, String additionalLevelIncrease, String savingThrowType,
                 String savingThrowModifier) {
        this(name, isAttack, range, level, casttime, duration, desc, damageFormula, damageType);
        this.additionalLevelIncrease = additionalLevelIncrease;
        this.savingThrowModifier = savingThrowModifier;
        this.savingThrowType = savingThrowType;
    }

    public Spell(String name) throws Exception {
        super(name, true);
        Map newMap = (Map) api.getAttribute("spells", name);
        configureAttributes(newMap);
    }

    public void configureAttributes(Map details) {
        String a = ((String) details.get("range"));
        range = Integer.parseInt(a.substring(0, a.indexOf("feet")));
    }

    //returns the new unit
    public static String interpretRange(String range) {
        int unitStartIndex = 0;
        for (int i = 1; i < range.length(); i++) {
            try {
                Integer.parseInt(range.substring(0, i));
            } catch (Exception E) {
                unitStartIndex = i;
                i = Integer.MAX_VALUE - 1;
            }
        }
        String unit = range.substring(unitStartIndex).toLowerCase();
        int value = Integer.parseInt(range.substring(0, unitStartIndex));
        if (unit.compareTo("meter") == 0)
            return (value * 3.28084) + " " + "feet";
        if (unit.compareTo("kilometer") == 0)
            return (value * 3280.84) + " " + "feet";
        if (unit.compareTo("centimeter") == 0)
            return (value * 0.0328084) + " " + "feet";
        if (unit.compareTo("yard") == 0)
            return (value * 3) + " " + "feet";
        if (unit.compareTo("nautical mile") == 0)
            return (value * 6076.11568) + " " + "feet";
        if (unit.compareTo("mile") == 0)
            return (value * 5280.00016896) + " " + "feet";
        return value + " " + unit;//TODO verify this works
    }

    public String damageAtLevel(int spellSlotLevel) {
        if (spellSlotLevel == level)
            return damageFormula;
        if (spellSlotLevel < level)
            return "";
        return damageFormula + "+" + (level - spellSlotLevel) + "*" + additionalLevelIncrease;

    }

    public boolean cast(int spellSlotLevel, Entity target, Player caster) throws Exception {
        if (savingThrowType == null || !(target instanceof Player)) {
            return target.takeDamage(DiceRoller.multiIntRoll(damageAtLevel(spellSlotLevel)), damageType);
        }
        if (((Player) target).savingThrow(savingThrowType) > calculateSpellSaveDC(caster))
            return target.takeDamage(
                    DiceRoller.multiIntRoll("(" + damageAtLevel(spellSlotLevel) + ")" + savingThrowModifier),
                    damageType);
        return target.takeDamage(DiceRoller.multiIntRoll("(" + damageAtLevel(spellSlotLevel) + ")"), damageType);

    }

    public int calculateSpellSaveDC(@NotNull Player caster) throws Exception {
        if (caster.hasClass("bard") || caster.hasClass("paladin")
                || caster.hasClass("sorcerer")
                || caster.hasClass("warlock")) {
            return 8 + caster.getProficiencyBonus() + caster.getModifier("cha");
        }
        if (caster.hasClass("cleric") || caster.hasClass("druid")) {
            return 8 + caster.getProficiencyBonus() + caster.getModifier("wis");
        }
        return 8 + caster.getProficiencyBonus() + caster.getModifier("int");

    }
}
