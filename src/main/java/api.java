import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static spark.Spark.*;

public class api {
    public static response search(String s)
    {
        final String[] ret = new String[1];

        get("https://www.dnd5eapi.co/api/spells/acid-arrow/" + s, (req, res) -> {
            req.headers();
            ret[0] = res.body();
            return 200;
        });

        Gson gson = new Gson();

        return gson.fromJson(ret[0], response.class);
    }

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
