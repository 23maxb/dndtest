import java.util.ArrayList;

public class race {

    // valid races: dragonborn, dwarf, elf, gnome, half-elf, halfling, half-orc,
    // human, tiefling
    public static ArrayList<String> getBonuses(String race, String raceArchetype) {
        ArrayList<String> a = new ArrayList<String>();
        switch (race) {
            case ("dragonborn"):
                a.add("size:medium");
                a.add("speed:30");
                switch (raceArchetype) {
                    case ("black"):
                        a.add("resistance:acid");
                        a.add("equipment:equipable(\"dragonBornBreathWeaponAcid\", false, 0, 1, -1, null, new spell(\"dragonBornBreathWeaponAcid\", true, 30, 0, ), null, false, false, \"shortrest\")");
                    case ("blue"):
                    case ("brass"):
                    case ("bronze"):
                    case ("copper"):
                    case ("gold"):
                    case ("green"):
                    case ("red"):
                    case ("silver"):
                    case ("white"):
                }
                break;
            case ("dwarf"):
                a.add("size:medium");
                a.add("speed:25");
                break;
            case ("elf"):
                a.add("size:medium");
                a.add("speed:30");
                break;
            case ("gnome"):
                a.add("size:small");
                a.add("speed:25");
                break;
            case ("half-elf"):
                a.add("size:medium");
                a.add("speed:30");
                break;
            case ("halfling"):
                a.add("size:small");
                a.add("speed:25");
                break;
            case ("half-orc"):
                a.add("size:medium");
                a.add("speed:30");
                break;
            case ("human"):
                a.add("size:medium");
                a.add("speed:30");
                break;
            case ("tiefling"):
                a.add("size:medium");
                a.add("speed:30");
                break;
        }
        return a;

    }

    // if no archetype is specifically stated use a default racetype
    public static ArrayList<String> getBonuses(String race) {
        {
            return getBonuses(race, "default");
        }
    }
}