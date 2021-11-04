import java.util.Map;


public class main {
    public static void main(String[] args) throws Exception {
        System.out.println(api.searchcurl("https://www.dnd5eapi.co/api/spells/acid-arrow"));
        Map<String, String> a = api.jsonToMap(api.searchcurl("https://www.dnd5eapi.co/api/spells/acid-arrow"));
        System.out.println(getValueOfItem);
    }
}