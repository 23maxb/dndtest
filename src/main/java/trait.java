import java.util.ArrayList;
import java.util.Map;

public class trait {
    public String desc;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Passive> getPassives() {
        return passives;
    }

    public void setPassives(ArrayList<Passive> passives) {
        this.passives = passives;
    }

    public ArrayList<Action> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Action> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<String> getOtherBonuses() {
        return otherBonuses;
    }

    public void setOtherBonuses(ArrayList<String> otherBonuses) {
        this.otherBonuses = otherBonuses;
    }

    public Map getMapOfStats() {
        return mapOfStats;
    }

    public void setMapOfStats(Map mapOfStats) {
        this.mapOfStats = mapOfStats;
    }

    public ArrayList<Passive> passives;
    public ArrayList<Action> abilities;
    public ArrayList<String> otherBonuses;
    public Map mapOfStats;

    public trait(String traitName) throws Exception {
        mapOfStats = (Map) api.getAttribute("traits", traitName);
        updateTrait(mapOfStats);
    }

    public trait(Map newMap) {
        mapOfStats = newMap;
        updateTrait(newMap);
    }

    public void updateTrait(Map a) {
        desc = (String) a.get("desc");
    }


}
