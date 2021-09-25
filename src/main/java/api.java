import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class api {
    public static String searchcurl(String urltocurl) throws Exception {
        URL url = new URL(urltocurl);
        String toReturn = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                toReturn += "\n" + line;
            }
        }
        return toReturn;
    }

    public static String get5eEntryPath(String searchterm) throws Exception
    {
        return searchcurl("https://www.dnd5eapi.co/api/" + searchterm);
    }
}
