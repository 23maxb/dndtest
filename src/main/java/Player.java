import java.util.ArrayList;

public class player extends entity {
    public String playerClass;
    public String playerClassArchetype;
    public int bronzeCoins;
    public ArrayList<item> inventory;
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;
    public int wisdom;
    public int charisma;
    public int proficiencyBonus;
    public int initiative;
    public int carryingCapacity;
    public String carryingCapacityBonus;
    public ArrayList<String> proficiencies;
    public ArrayList<action> possibleActions;

    public player(String playerClass, int money, ArrayList<item> a, double maxHealth, boolean m, String name, boolean ren, String race,
                  String raceArchetype, String classArchetype) throws Exception {
        super(maxHealth, m, name, ren, race);
        this.playerClass = playerClass;
        playerClassArchetype = classArchetype;
        bronzeCoins = money;
        inventory = a;
        speed = 30;
        speedBonus = (String) (api.getAtribute("races", this.race, "speed"));
        calculateStats();
        // addBonuses(race.getBonuses(race, raceArchetype));//ask saahil
    }

    public void calculateStats()
    {

    }

    public player(String cl, int money, ArrayList<item> a, double mH, boolean m, String n, boolean ren, String race) throws Exception {
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

    public String getCarryingCapacityBonus() {
        return carryingCapacityBonus;
    }

    public void setCarryingCapacityBonus(String carryingCapacityBonus) {
        this.carryingCapacityBonus = carryingCapacityBonus;
    }

    public ArrayList<String> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(ArrayList<String> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public ArrayList<action> getPossibleActions() {
        return possibleActions;
    }

    public void setPossibleActions(ArrayList<action> possibleActions) {
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
        for (int i = 0; i < proficiencies.size(); i++) {
            if (proficiencies.get(i).indexOf("savingthrow") != -1 && proficiencies.get(i).indexOf("dex") != -1) {
                return diceRoller.multiIntRoll("1d20+" + proficiencyBonus + getModifier(throwType));
            }
        }
        return diceRoller.multiIntRoll("1d20+" + getModifier(throwType));
    }

    public int getModifier(String type) throws Exception {
        int statToCheck = -1;
        switch (type) {
            case "str":
                statToCheck = strength;
                break;
            case "dex":
                statToCheck = strength;
                break;
            case "con":
                statToCheck = strength;
                break;
            case "int":
                statToCheck = strength;
                break;
            case "wis":
                statToCheck = strength;
                break;
            case "cha":
                statToCheck = strength;
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