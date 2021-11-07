import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Race {

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
            a.add("stat:" + ((Map) ((Map) ((ArrayList<?>) raceInformation.get("ability_bonuses")).get(i)).get("ability_score")).get("index") + ":+" + ((Map) ((ArrayList<?>) raceInformation.get("ability_bonuses")).get(i)).get("bonus"));
        //Archetype ability bonuses
        for (int i = 0; raceArchetype.compareTo("default") != 0 && i < ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("ability_bonuses")).size(); i++)
            a.add("stat:" + ((Map) ((Map) ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("ability_bonuses")).get(i)).get("ability_score")).get("index") + ":+" + ((Map) ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("ability_bonuses")).get(i)).get("bonus"));
        //size
        a.add("size:" + ((String) raceInformation.get("size")).toLowerCase());
        //proficiencies
        for (int i = 0; i < ((ArrayList<?>) raceInformation.get("starting_proficiencies")).size(); i++)
            a.add("proficiency:" + ((Map) ((ArrayList) raceInformation.get("starting_proficiencies")).get(i)).get("index"));
        //Archetype proficiencies
        for (int i = 0; raceArchetype.compareTo("default") != 0 && i < ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("starting_proficiencies")).size(); i++)
            a.add("proficiency:" + ((Map) ((ArrayList) ((Map) raceInformation.get("archetype")).get("starting_proficiencies")).get(i)).get("index"));
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
        if (hasOptionalLanguages) {
            String[] availableLangs = new String[size];
            for (int i = 0; i < size; i++)
                availableLangs[i] = ((Map) ((ArrayList) ((Map) raceInformation.get("language_options")).get("from")).get(i)).get("name").toString();
            System.out.println("Your race " + race + " has " + size + " optional languages.");
            for (int i = 0; i < (int) ((Map) raceInformation.get("language_options")).get("choose"); i++) {
                System.out.println("You may choose " + ((Map) raceInformation.get("language_options")).get("choose") + " of the following.");
                System.out.println(Arrays.asList(availableLangs));
                System.out.println("Please type the language you would like to choose. (" + (i + 1) + "/" +
                        ((Map) raceInformation.get("language_options")).get("choose") + ")");
                String chosenLang = UserInput.promptUser();
                boolean valid = false;
                for (String availableLang : availableLangs) {
                    if (chosenLang.toLowerCase().compareTo(availableLang.toLowerCase()) == 0) {
                        valid = true;
                        break;
                    }
                }
                if (valid)
                    a.add("language:" + chosenLang);
                else {
                    System.out.println(chosenLang + " was not a valid language choice. Please select a new language.");
                    i--;
                }
            }
        }
        //traits
        for (int i = 0; i < ((ArrayList<?>) raceInformation.get("traits")).size(); i++)
            a.add("traits:" + ((Map) ((ArrayList) raceInformation.get("traits")).get(i)).get("index"));
        //Archetype traits
        for (int i = 0; raceArchetype.compareTo("default") != 0 && i < ((ArrayList<?>) ((Map) raceInformation.get("archetype")).get("traits")).size(); i++)
            a.add("traits:" + ((Map) ((ArrayList) ((Map) raceInformation.get("archetype")).get("traits")).get(i)).get("index"));
        return a;
    }

    // if no archetype is specifically stated use a default racetype
    public static ArrayList<String> getBonuses(String race) throws Exception {
        {
            return getBonuses(race, "default");
        }
    }
}