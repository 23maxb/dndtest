import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class api {
    public static @NotNull String searchcurl(String urltocurl) throws Exception {
        URL url = new URL(urltocurl);
        StringBuilder toReturn = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
            for (String line; (line = reader.readLine()) != null; ) toReturn.append("\n").append(line);
        }
        return toReturn.toString();
    }

    public static Map<String, String> jsonToMap(@NotNull String JSON_SOURCE) throws Exception {
        return (Map<String, String>) (new ObjectMapper().readValue(JSON_SOURCE, HashMap.class));
    }

    public static Map<String, String> get5eEntryPath(String @NotNull ... args) throws Exception {
        String path = Arrays.stream(args).map(arg -> arg + "/").collect(Collectors.joining("", "/api/", ""));
        System.out.println("https://www.dnd5eapi.co" + path);
        return jsonToMap(searchcurl("https://www.dnd5eapi.co" + path));
    }

    public static String getValueOfItem(String @NotNull ... args) throws Exception {
        String[] a = new String[args.length - 1];
        System.arraycopy(args, 0, a, 0, a.length);
        return get5eEntryPath(a).get(args[args.length - 1]);
    }
}
