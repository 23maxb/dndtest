import java.util.ArrayList;

public class item extends thing {
    public double weight;
    public int count;
    public int durability;

    public int getDurability() {
        return durability;
    }

    public boolean isUnbreakable() {
        return durability == -1;
    }

    public void setDurability(int durability) {
        this.durability = durability;
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
    public String activeCooldown;
    public boolean onCooldown;

    public item(String n, boolean ren, double w, int c, int d, action active, ArrayList<String> passives, String reqCooldown) {
        super(n, ren);
        weight = w;
        count = c;
        durability = d;
        this.active = active;
        this.passives = passives;
        this.activeCooldown = reqCooldown;
        onCooldown = false;
    }

    public item(String n, boolean ren, double w, int c, int d) {
        this(n, ren, w, c, d, null, null);
    }

    public item(String n, boolean ren, double w, int c) {
        this(n, ren, w, c, -1, null, null);
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
