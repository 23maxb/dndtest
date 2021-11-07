import java.util.ArrayList;

public class Equipable extends item {
    public ArrayList<String> requiredAppendage;
    public boolean unequipable;
    public boolean tradable;

    {

    }

    public Equipable(String n, boolean ren, double weight, int count, int durability, ArrayList<String> requiredApp, boolean transferable, boolean unequipable) {
        super(n, ren, weight, count, durability);
        this.requiredAppendage = requiredApp;
        this.unequipable = unequipable;
        this.tradable = transferable;
    }

    public boolean isUnequipable() {
        return unequipable;
    }

    public void setUnequipable(boolean unequipable) {
        this.unequipable = unequipable;
    }

    public boolean isTradable() {
        return tradable;
    }

    public void setTradable(boolean tradable) {
        this.tradable = tradable;
    }

    public ArrayList<String> getRequiredAppendage() {
        return requiredAppendage;
    }

    public void setRequiredAppendage(ArrayList<String> requiredAppendage) {
        this.requiredAppendage = requiredAppendage;
    }


}