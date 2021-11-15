import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class Player extends Entity {//TODO add multi classing
    public ArrayList<String> playerClass;
    public Map<String, Object> classSpecificData;//TODO implement class specific data
    public ArrayList<StatusEffect> statuses;

    public ArrayList<StatusEffect> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<StatusEffect> statuses) {
        this.statuses = statuses;
    }

    public ArrayList<String> getHitDie() throws Exception {
        return Player.getHitDie(getPlayerClass());
    }

    public static ArrayList<String> getHitDie(ArrayList<String> classes) throws Exception {
        ArrayList<String> a = new ArrayList<String>();
        for (String b : classes) {
            a.add(b.substring(b.lastIndexOf(".") + 1) + "d" + api.getAttribute("classes", b.substring(0, b.indexOf(".")), "hit_die").toString());
        }
        return a;
    }

    //format baseclass.archetype
    //ex: fighter.gunslinger
    public void addClass(String classToAdd) throws Exception {
        //checking if the level is accidentally added
        try {
            int level = Integer.parseInt(classToAdd.split("\\.")[2]);
            addClass(classToAdd.substring(0, classToAdd.lastIndexOf(".")));
            return;
        } catch (Exception ignored) {
        }
        //checking if the format is correct and correcting as needed
        String a = classToAdd.split("\\.")[2];
        if (!classToAdd.contains(".")) {
            addClass(classToAdd + ".default");
            return;
        }
        String[] req = PlayerClass.getRequirementsForMultiClassing(classToAdd.substring(0, classToAdd.indexOf(".")));
        if (req[0].contains("need")) {
            int needed = Integer.parseInt(req[0].substring(req[0].lastIndexOf(":") + 1));
            int have = 0;
            for (String l : req) {
                final int i = Integer.parseInt(l.substring(l.indexOf(".") + 1));
                final String message = "Cannot add class since the " + l.substring(0, l.indexOf(":")) + " stat does not meet the requirement " + (l.substring(l.indexOf(".") + 1));
                switch (l.substring(0, l.indexOf(":"))) {
                    case "need":
                        break;
                    case "str":
                        if (!(strength < i))
                            have++;
                        break;
                    case "dex":
                        if (!(dexterity < i))
                            have++;
                        break;
                    case "con":
                        if (!(constitution < i))
                            have++;
                        break;
                    case "int":
                        if (!(intelligence < i))
                            have++;
                        break;
                    case "wis":
                        if (!(wisdom < i))
                            have++;
                        break;
                    case "cha":
                        if (!(charisma < i))
                            have++;
                        break;
                }
            }
            if (have >= needed) {
                playerClass.add(classToAdd + ".1");
                return;
            } else
                throw new Exception("You do not meet enough requirements to add this class");
        }
        for (String j : req) {
            final int i = Integer.parseInt(j.substring(j.indexOf(".") + 1));
            final String message = "Cannot add class since the " + j.substring(0, j.indexOf(":")) + " stat does not meet the requirement " + (j.substring(j.indexOf(".") + 1));
            switch (j.substring(0, j.indexOf(":"))) {
                case "str":
                    if (strength < i)
                        throw new Exception(message);
                    break;
                case "dex":
                    if (dexterity < i)
                        throw new Exception(message);
                    break;
                case "con":
                    if (constitution < i)
                        throw new Exception(message);
                    break;
                case "int":
                    if (intelligence < i)
                        throw new Exception(message);
                    break;
                case "wis":
                    if (wisdom < i)
                        throw new Exception(message);
                    break;
                case "cha":
                    if (charisma < i)
                        throw new Exception(message);
                    break;
            }
        }
        playerClass.add(classToAdd + ".1");
    }

    public void checkStatuses() {
        for (StatusEffect a : statuses) {
            a.update();
            this.apply(a.getBonus());//TODO IMPLEMENT applying statuses
        }
    }

    public void apply(ArrayList<String> bonus) {
        for (String a : bonus)
            switch (a.substring(0, a.indexOf(":"))) {
                case "":

            }
    }//TODO fix thsi method

    public void turnTickStatuses() {
        for (StatusEffect a : statuses)
            a.turnTick();
    }

    public void turnTickStatuses(int turns) {
        for (StatusEffect a : statuses)
            a.turnTick(turns);
    }

    public void hourTickStatuses(int hours) {
        for (StatusEffect a : statuses) {
            a.hourTick(hours);
        }
    }

    public void hourTickStatuses() {
        hourTickStatuses(1);
    }

    public int bronzeCoins;
    public ArrayList<item> inventory;
    public int strength;
    public ArrayList<String> bonusSourceStrength;
    public int dexterity;
    public ArrayList<String> bonusSourceDexterity;
    public int constitution;
    public ArrayList<String> bonusSourceConstitution;
    public int intelligence;
    public ArrayList<String> bonusSourceIntelligence;
    public int wisdom;
    public ArrayList<String> bonusSourceWisdom;
    public int charisma;
    public ArrayList<String> bonusSourceCharisma;
    public int proficiencyBonus;
    public int initiative;
    public int carryingCapacity;
    public ArrayList<String> carryingCapacityBonus;
    public ArrayList<String[]> traits;
    public ArrayList<String[]> proficiencies;
    public ArrayList<Action> possibleActions;

    public Player(String playerClass, int money, ArrayList<item> a, double maxHealth, boolean m, String name, boolean ren, String race,
                  String raceArchetype, String classArchetype) throws Exception {
        super(maxHealth, name, ren, race, raceArchetype);
        this.playerClass.add(playerClass + "." + classArchetype + "." + "1");
        bronzeCoins = money;
        inventory = a;
        calculateStats();
        // addBonuses(race.getBonuses(race, raceArchetype));
        // TODO ask saahil
    }

    public void calculateStats() throws Exception {
        //race calculated bonus
        for (String bonus : super.calculateBonuses()) {
            interpretBonus(bonus, "race." + super.getRace() + "." + super.getRaceArchetype());
        }
        //TODO class calculated bonus
    }

    public void interpretBonus(@NotNull String bonus, String source) throws Exception {
        if (bonus.contains("speed")) {
            super.speed = Integer.parseInt(bonus.substring(bonus.indexOf(":") + 1));
            super.speedBonus.add("defaultRaceSpeed:" + super.speed);
        } else if (bonus.contains("stat:")) {
            int y = Integer.parseInt(bonus.substring(bonus.lastIndexOf(":") + 1));
            switch (bonus.substring(bonus.indexOf(":") + 1, bonus.lastIndexOf(":")).toLowerCase()) {
                case "str":
                    this.strength += y;
                    bonusSourceStrength.add(source + ":" + bonus);
                    break;
                case "dex":
                    this.dexterity += y;
                    bonusSourceDexterity.add(source + ":" + bonus);
                    break;
                case "con":
                    constitution += y;
                    bonusSourceConstitution.add(source + ":" + bonus);
                    break;
                case "int":
                    intelligence += y;
                    bonusSourceIntelligence.add(source + ":" + bonus);
                    break;
                case "wis":
                    wisdom += y;
                    bonusSourceWisdom.add(source + ":" + bonus);
                    break;
                case "cha":
                    charisma += y;
                    bonusSourceCharisma.add(source + ":" + bonus);
                    break;
            }

        } else if (bonus.contains("size:")) {
            if (size == null) {
                size = bonus.substring(bonus.indexOf(":") + 1);
                sizeSource = source;
            } else
                throw new Exception("Size previously declared.");
        } else if (bonus.contains("traits"))
            traits.add(new String[]{bonus.substring(bonus.indexOf(":") + 1), source});
        else if (bonus.contains("language"))
            super.languages.add(new String[]{bonus.substring(bonus.indexOf(":") + 1), source});
        else if (bonus.contains("proficiency"))
            proficiencies.add(new String[]{bonus.substring(bonus.indexOf(":") + 1), source});
        else
            throw new Exception("The bonus indicated " + bonus + "was invalid");

    }

    public Player(String cl, int money, ArrayList<item> a, double mH, boolean m, String n, boolean ren, String race) throws Exception {
        this(cl, money, a, mH, m, n, ren, race, "default", "default");
    }

    public ArrayList<String> getPlayerClass() {
        return playerClass;
    }

    public String getMainPlayerClass() {
        return playerClass.get(0);
    }

    public boolean hasClass(String classToCompare) {
        for (String a : playerClass) {
            if (a.contains(classToCompare)) return true;
        }
        return false;
    }

    public void setPlayerClass(ArrayList<String> playerClass) {
        this.playerClass = playerClass;
    }

    public void addPlayerClass(String newClass) {
        playerClass.add(newClass);
    }

    public int getBronzeCoins() {
        return bronzeCoins;
    }

    public void setBronzeCoins(int bronzeCoins) {
        this.bronzeCoins = bronzeCoins;
    }

    public ArrayList<item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<item> inventory) {
        this.inventory = inventory;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public ArrayList<String> getCarryingCapacityBonus() {
        return carryingCapacityBonus;
    }

    public void addCarryingCapacityBonus(String carryingCapacityBonus) {
        this.carryingCapacityBonus.add(carryingCapacityBonus);
    }

    public ArrayList<String[]> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(ArrayList<String[]> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public ArrayList<Action> getPossibleActions() {
        return possibleActions;
    }

    public void setPossibleActions(ArrayList<Action> possibleActions) {
        this.possibleActions = possibleActions;
    }

    public void addBonuses(ArrayList<String> a) {
        return; //TODO fix here

    }

    public void addItem(item newItem) {
        inventory.add(newItem);
    }

    public boolean isEncumbered() {
        int z = 0;
        for (item item : inventory) {
            z += item.getWeight() * item.getCount();
        }
        return z > carryingCapacity;
    }

    public int savingThrow(String throwType) throws Exception {
        for (String[] proficiency : proficiencies) {
            if (proficiency[0].contains("savingthrow") && proficiency[0].contains("dex")) {
                return DiceRoller.multiIntRoll("1d20+" + proficiencyBonus + getModifier(throwType));
            }
        }
        return DiceRoller.multiIntRoll("1d20+" + getModifier(throwType));
    }

    public int getModifier(@NotNull String type) throws Exception {
        int statToCheck = -1;
        switch (type) {
            case "str":
                statToCheck = strength;
                break;
            case "dex":
                statToCheck = dexterity;
                break;
            case "con":
                statToCheck = constitution;
                break;
            case "int":
                statToCheck = intelligence;
                break;
            case "wis":
                statToCheck = wisdom;
                break;
            case "cha":
                statToCheck = charisma;
                break;
        }
        if (statToCheck == -1)
            throw new Exception("Invalid Type Specified; Valid Types: str, dex, con, int, wis, cha");

        if (statToCheck <= 1)
            return -5;
        if (statToCheck <= 3)
            return -4;
        if (statToCheck <= 5)
            return -3;
        if (statToCheck <= 7)
            return -2;
        if (statToCheck <= 9)
            return -1;
        if (statToCheck <= 11)
            return 0;
        if (statToCheck <= 13)
            return 1;
        if (statToCheck <= 15)
            return 2;
        if (statToCheck <= 17)
            return 3;
        if (statToCheck <= 19)
            return 4;
        if (statToCheck <= 21)
            return 5;
        if (statToCheck <= 23)
            return 6;
        if (statToCheck <= 25)
            return 7;
        if (statToCheck <= 27)
            return 8;
        if (statToCheck <= 29)
            return 9;
        return 10;


    }

}
