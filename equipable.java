import java.util.ArrayList;

public class equipable extends item {
    public ArrayList<String> requiredAppendage;

    public String getCooldownReq() {
        return cooldownReq;
    }

    public void setCooldownReq(String cooldownReq) {
        this.cooldownReq = cooldownReq;
    }

    public boolean isOnCooldown() {
        return onCooldown;
    }

    public void setOnCooldown(boolean onCooldown) {
        this.onCooldown = onCooldown;
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

    public String cooldownReq;
    public boolean onCooldown;

    public ArrayList<String> getRequiredAppendage() {
        return requiredAppendage;
    }

    public void setRequiredAppendage(ArrayList<String> requiredAppendage) {
        this.requiredAppendage = requiredAppendage;
    }

    public action getActive() {
        return active;
    }

    public void setActive(action active) {
        this.active = active;
    }

    public ArrayList<String> getPassives() {
        return passives;
    }

    public void setPassives(ArrayList<String> passives) {
        this.passives = passives;
    }

    public action active;
    public ArrayList<String> passives;
    public boolean unequipable;
    public boolean tradable;

    public equipable(String n, boolean ren, double weight, int count, int durability, ArrayList<String> requiredApp,, boolean transferable, boolean unequipable) {
        super(n, ren, weight, count, durability);
        this.requiredAppendage = requiredApp;
        this.unequipable = unequipable;
        this.tradable = transferable;
    }

    public equipable(String n, boolean ren, double weight, int count, int durability, ArrayList<String> requiredApp,
            action active, ArrayList<String> passive, boolean transferable, boolean unequipable,
            String activeRequirement) {
        this(n, ren, weight, count, durability, requiredApp, active, passive, transferable, unequipable);
        onCooldown = false;
        this.cooldownReq = activeRequirement;
    }

    public static useActive(spell())
    {

    }
}