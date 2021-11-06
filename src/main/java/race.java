import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class race {

    // valid races: dragonborn, dwarf, elf, gnome, half-elf, halfling, half-orc,
    // human, tiefling

    @SuppressWarnings({"rawtypes", "unchecked"})//raw types detected since api accession not vewable from this ide
    //unchecked same
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
        for (int i = 0; raceArchetype.compareTo("default") != 0 && i < ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("ability_bonuses")).size(); i++)
            a.add(((Map) ((Map) ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("ability_bonuses")).get(i)).get("ability_score")).get("index") + ":+" + ((Map) ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("ability_bonuses")).get(i)).get("bonus"));
        //size
        a.add("size:" + ((String) raceInformation.get("size")).toLowerCase());
        //proficiencies
        for (int i = 0; i < ((ArrayList<?>) raceInformation.get("starting_proficiencies")).size(); i++)
            a.add("proficiency:" + ((ArrayList) raceInformation.get("starting_proficiencies")).get(i));
        //Archetype proficiencies
        for (int i = 0; raceArchetype.compareTo("default") != 0 && i < ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("starting_proficiencies")).size(); i++)
            a.add("proficiency:" + ((ArrayList) ((Map) raceInformation.get("archetype")).get("starting_proficiencies")).get(i));
        //languages
        for (int i = 0; i < ((ArrayList<?>) raceInformation.get("languages")).size(); i++)
            a.add("language:" + ((Map) ((ArrayList) raceInformation.get("languages")).get(i)).get("index"));
        for (int i = 0; raceArchetype.compareTo("default") != 0 && i < ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("languages")).size(); i++)
            a.add("language:" + ((Map) (((ArrayList) ((Map) raceInformation.get("archetype")).get("languages"))).get(i)).get("index"));

        //language options
        //EDIT HERE IN ORDER TO CHANGE HOW LANGUAGES ARE CHOSEN
        //tests whether any language options are available
        int size = -1;
        boolean hasOptionalLanguages = true;
        try {
            size = ((ArrayList) ((Map) raceInformation.get("language_options")).get("from")).size();
            if (size == 0)
                hasOptionalLanguages = false;
        } catch (Exception ignored) {
            hasOptionalLanguages = false;
        }

        if (size > 0) {
            System.out.println("Your race " + race + " has " + size + " optional languages.");
            System.out.println("You may choose " + ((Map) raceInformation.get("language_options")).get("choose") + " of the following.");
        }
        for (int i = 0; size > 0 && i < size; i++)
            System.out.print("\r\n" + "Option" + (i + 1) + ":" + ((Map) ((ArrayList) ((Map) raceInformation.get("language_options")).get("from")).get(i)).get("name"));
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