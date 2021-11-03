import java.lang.reflect.*;


public class main {
    public static void main(String[] args) throws Exception {
        String search = api.searchcurl(api.get5eEntryPath("/api/spells/"));
        String name = "acid-arrow";
        System.out.println(search.substring(search.substring(search.indexOf(name)).indexOf("url") + "\"url\": \"".length()));
    }
}