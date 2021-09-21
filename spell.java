public class spell extends action {
    public int level;
    public int casttime;
    public int range;
    public int duration;
    public String desc;
    public String damageFormula;
    public String damageType;
    public String additionalLevelIncrease;
    public String savingThrowType;
    public String savingThrowModifier;// this should be what to multiply the damage by
    // IF THE SAVING THROW IS SUCCESSFUL
    // should be *0.5 if the damage is halved on success

    public spell(String name, boolean isAttack, int range, int level, int casttime, int duration, String desc,
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

    public spell(String name, boolean isAttack, int range, int level, int casttime, int duration, String desc,
            String damageFormula, String damageType, String additionalLevelIncrease) {
        this(name, isAttack, range, level, casttime, duration, desc, damageFormula, damageType);
        this.additionalLevelIncrease = additionalLevelIncrease;
    }

    public spell(String name, boolean isAttack, int range, int level, int casttime, int duration, String desc,
            String damageFormula, String damageType, String additionalLevelIncrease, String savingThrowType,
            String savingThrowModifier) {
        this(name, isAttack, range, level, casttime, duration, desc, damageFormula, damageType);
        this.additionalLevelIncrease = additionalLevelIncrease;
        this.savingThrowModifier = savingThrowModifier;
        this.savingThrowType = savingThrowType;
    }

    public String damageAtLevel(int spellSlotLevel) {
        if (spellSlotLevel == level)
            return damageFormula;
        if (spellSlotLevel < level)
            return "";
        return damageFormula + "+" + (level - spellSlotLevel) + "*" + additionalLevelIncrease;

    }

    public boolean cast(int spellSlotLevel, entity target, player caster) throws Exception {
        if (savingThrowType == null || !(target instanceof player)) {
            return target.takeDamage(diceRoller.multiIntRoll(damageAtLevel(spellSlotLevel)), damageType);
        }
        if (((player) target).savingThrow(savingThrowType) > calculateSpellSaveDC(caster))
            return target.takeDamage(
                    diceRoller.multiIntRoll("(" + damageAtLevel(spellSlotLevel) + ")" + savingThrowModifier),
                    damageType);
        return target.takeDamage(diceRoller.multiIntRoll("(" + damageAtLevel(spellSlotLevel) + ")"), damageType);

    }

    public int calculateSpellSaveDC(player caster) throws Exception {
        if (caster.getPlayerClass().compareTo("bard") == 0 || caster.getPlayerClass().compareTo("paladin") == 0
                || caster.getPlayerClass().compareTo("sorcerer") == 0
                || caster.getPlayerClass().compareTo("warlock") == 0) {
            return 8 + caster.getProficiencyBonus() + caster.getModifier("cha");
        }
        if (caster.getPlayerClass().compareTo("cleric") == 0 || caster.getPlayerClass().compareTo("druid") == 0) {
            return 8 + caster.getProficiencyBonus() + caster.getModifier("wis");
        }
        return 8 + caster.getProficiencyBonus() + caster.getModifier("int");

    }
}
