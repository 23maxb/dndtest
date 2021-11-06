import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class race {

    // valid races: dragonborn, dwarf, elf, gnome, half-elf, halfling, half-orc,
    // human, tiefling
    public static @NotNull ArrayList<String> getBonuses(@NotNull String race, @NotNull String raceArchetype) throws Exception {
        ArrayList<String> a = new ArrayList<String>();
        Map<String, Object> raceInformation = (Map<String, Object>) (api.getAtribute("races", race));
        //subrace
        if (raceArchetype.compareTo("default") != 0)
            raceInformation.put("archetype", (api.getAtribute("subraces", raceArchetype)));
        //speed
        a.add("speed:" + raceInformation.get("speed"));
        //ability bonuses
        for (int i = 0; i < ((ArrayList<?>) raceInformation.get("ability_bonuses")).size(); i++)
            a.add(((Map) ((Map) ((ArrayList<?>) raceInformation.get("ability_bonuses")).get(i)).get("ability_score")).get("index") + ":+" + ((Map) ((ArrayList<?>) raceInformation.get("ability_bonuses")).get(i)).get("bonus"));
        //Archetype ability bonuses
        for (int i = 0; i < ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("ability_bonuses")).size(); i++)
            a.add(((Map) ((Map) ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("ability_bonuses")).get(i)).get("ability_score")).get("index") + ":+" + ((Map) ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("ability_bonuses")).get(i)).get("bonus"));
        //size
        a.add("size:" + ((String) raceInformation.get("size")).toLowerCase());
        //proficiencies
        for (int i = 0; i < ((ArrayList<?>) raceInformation.get("starting_proficiencies")).size(); i++)
            a.add("proficiency:" + ((ArrayList) raceInformation.get("starting_proficiencies")).get(i));
        //Archetype proficiencies
        for (int i = 0; i < ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("starting_proficiencies")).size(); i++)
            a.add("proficiency:" + ((ArrayList) ((Map) raceInformation.get("archetype")).get("starting_proficiencies")).get(i));
        //languages
        //traits

        return a;
    }

    // if no archetype is specifically stated use a default racetype
    public static ArrayList<String> getBonuses(String race) throws Exception {
        {
            return getBonuses(race, "default");
        }
    }
}