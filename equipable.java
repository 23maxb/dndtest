import java.util.ArrayList;

public class equipable extends item {
    public ArrayList<String> requiredAppendage;

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

    public equipable(String n, boolean ren, double w, int c, int d, ArrayList<String> requiredApp) {
        super(n, ren, w, c, d);
        this.requiredAppendage = requiredApp;
    }
    public equipable(String n, boolean ren, double w, int c, int d)
    {
        this(n, ren, w, c, d, null);
    }
}