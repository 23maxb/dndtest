import java.util.ArrayList;

public class race {

    // valid races: dragonborn, dwarf, elf, gnome, half-elf, halfling, half-orc,
    // human, tiefling
    public static ArrayList<String> getBonuses(String race) {
        ArrayList<String> a = new ArrayList<String>();
        switch (race) {
            case ("dragonborn"):
                a.add("size:medium");
                a.add("speed:30");
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
}
