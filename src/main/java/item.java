import java.util.ArrayList;

public class item extends thing {
    public double weight;
    public int count;
    public int durability;
    public String cooldownReq;
    public boolean onCooldown;
    public ArrayList<passive> passives;
    public action active;
    public String itemType;

    public item(String n, boolean ren, double w, int c, int d, action active, ArrayList<passive> passives, String reqCooldown) {
        super(n, ren);
        weight = w;
        count = c;
        durability = d;
        this.active = active;
        this.passives = passives;
        this.cooldownReq = reqCooldown;
        onCooldown = false;
    }

    public item(String n, boolean ren, double w, int c, int d) {
        this(n, ren, w, c, d, null, null, null);
    }

    public item(String n, boolean ren, double w, int c) {
        this(n, ren, w, c, -1, null, null, null);
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public boolean isUnbreakable() {
        return durability == -1;
    }

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

    public ArrayList<passive> getPassives() {
        return passives;
    }

    public void setPassives(ArrayList<passive> passives) {
        this.passives = passives;
    }

    public void addPassive(passive np)
    {
        passives.add(np);
    }

    public action getActive() {
        return active;
    }

    public void setActive(action active) {
        this.active = active;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double w) {
        weight = w;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int c) {
        count = c;
    }

}
