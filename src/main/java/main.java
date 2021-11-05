import java.util.Map;

public class main {
    public static void main(String[] args) throws Exception {
        /*
        System.out.println(api.searchCurl("https://www.dnd5eapi.co/api/spells/acid-arrow"));
        Map<String, String> a = api.jsonToMap(api.searchCurl("https://www.dnd5eapi.co/api/spells/acid-arrow"));*/
        //System.out.println(api.getValueOfItem("monsters", "adult-black-dragon", "type"));
        //System.out.println(api.getValueOfPath("equipment", "spear").get("damage"));
        System.out.println(api.getValueOfItem("equipment", "spear", "damage", "damage_type"));

    }
}