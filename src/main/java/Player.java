import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class Player extends Entity {
    public String playerClass;
    public String playerClassArchetype;
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
    public ArrayList<String> proficiencies;
    public ArrayList<Action> possibleActions;

    public Player(String playerClass, int money, ArrayList<item> a, double maxHealth, boolean m, String name, boolean ren, String race,
                  String raceArchetype, String classArchetype) throws Exception {
        super(maxHealth, m, name, ren, race);
        this.playerClass = playerClass;
        playerClassArchetype = classArchetype;
        bronzeCoins = money;
        inventory = a;
        calculateStats();
        // addBonuses(race.getBonuses(race, raceArchetype));//ask saahil
    }

    public void calculateStats() throws Exception {
        for (String bonus : super.calculateBonuses()) {
            //interpretBonus(bonus);
        }
    }

    public void interpretBonus(@NotNull String bonus) {
        if (bonus.contains("speed")) {
            super.speed = Integer.parseInt(bonus.substring(bonus.indexOf(":") + 1));
            super.speedBonus.add("defaultRaceSpeed:" + super.speed);
        } else if (bonus.contains("stat:")) {
            switch (bonus.substring(bonus.indexOf(":") + 1, bonus.lastIndexOf(":")).toLowerCase()) {
                case "str":
                    strength =
                    break;
                case "dex":
                    break;
                case "con":
                    break;
                case "int":
                    break;
                case "wis":
                    break;
                case "cha":
                    break;

            }

        } else if (bonus.contains("stat:")) {
            System.out.println("a");
        }

    }

    public Player(String cl, int money, ArrayList<item> a, double mH, boolean m, String n, boolean ren, String race) throws Exception {
        this(cl, money, a, mH, m, n, ren, race, "default", "default");
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
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

    public ArrayList<String> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(ArrayList<String> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public ArrayList<Action> getPossibleActions() {
        return possibleActions;
    }

    public void setPossibleActions(ArrayList<Action> possibleActions) {
        this.possibleActions = possibleActions;
    }

    public void addBonuses(ArrayList<String> a) {
        return; //fix here

    }

    public void addItem(item newItem) {
        inventory.add(newItem);
    }

    public boolean isEncumbered() {
        int z = 0;
        for (int i = 0; i < inventory.size(); i++) {
            z += inventory.get(i).getWeight() * inventory.get(i).getCount();
        }
        return z > carryingCapacity;
    }

    public int savingThrow(String throwType) throws Exception {
        for (String proficiency : proficiencies) {
            if (proficiency.contains("savingthrow") && proficiency.contains("dex")) {
                return DiceRoller.multiIntRoll("1d20+" + proficiencyBonus + getModifier(throwType));
            }
        }
        return DiceRoller.multiIntRoll("1d20+" + getModifier(throwType));
    }

    public int getModifier(String type) throws Exception {
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
