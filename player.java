import java.nio.IntBuffer;
import java.util.ArrayList;

public class player extends entity {
    public String playerClass;
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

    public player(String cl, int money, ArrayList<item> a, double mH, boolean m, String n, boolean ren, String race) {
        super(mH, m, n, ren, race);
        playerClass = cl;
        bronzeCoins = money;
        inventory = a;
        speed = 30;
        speedBonus = "Base: 30ft";//needstobeupdated
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

}
