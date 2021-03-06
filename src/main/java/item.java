import java.util.ArrayList;

public class item extends thing {
    public double weight;
    public int count;
    public int durability;
    public String cooldownReq;
    public boolean onCooldown;
    public ArrayList<Passive> passives;
    public Action active;
    public String itemType;

    public item(String n, boolean ren, double w, int c, int d, Action active, ArrayList<Passive> passives, String reqCooldown) {
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

    public ArrayList<Passive> getPassives() {
        return passives;
    }

    public void setPassives(ArrayList<Passive> passives) {
        this.passives = passives;
    }

    public void addPassive(Passive np)
    {
        passives.add(np);
    }

    public Action getActive() {
        return active;
    }

    public void setActive(Action active) {
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
