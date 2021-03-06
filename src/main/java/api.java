import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//Use api.getAtribute("catagory", "subcatagory", "subcatagory", "atribute");
public class api {
    public static @NotNull String searchCurl(String urlToCurl) throws Exception {
        URL url = new URL(urlToCurl);
        StringBuilder toReturn = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
            for (String line; (line = reader.readLine()) != null; ) toReturn.append("\n").append(line);
        }
        return toReturn.toString();
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> jsonToMap(@NotNull String JSON_SOURCE) throws Exception {
        return (Map<String, String>) (new ObjectMapper().readValue(JSON_SOURCE, HashMap.class));
    }

    public static ArrayList jsonToArrayList(@NotNull String JSON_SOURCE) throws Exception {
        return new ObjectMapper().readValue(JSON_SOURCE, ArrayList.class);
    }

    public static @NotNull String get5eEntryPath(String @NotNull ... args) throws Exception {
        String path = Arrays.stream(args).map(arg -> arg + "/").collect(Collectors.joining("", "/api/", ""));
        return searchCurl("https://www.dnd5eapi.co" + path);
    }

    public static Object getValueOfPath(String @NotNull ... args) throws Exception {
        try {
            return jsonToMap(get5eEntryPath(args));
        } catch (Exception E) {
            return jsonToArrayList(get5eEntryPath(args));
        }
    }

    public static Object getAttribute(String @NotNull ... args) throws Exception {
        if (args.length == 0)
            return getValueOfPath();
        //changes all arguments to lower case
        IntStream.range(0, args.length).forEachOrdered(i1 -> args[i1] = args[i1].toLowerCase());
        Object toReturn = getValueOfPath(args[0]);
        for (int i = 0; i < args.length; i++) {
            assert toReturn != null : "The argument " + args[i - 1] + " was invalid and caused an error.";
            String[] a = new String[i + 1];
            System.arraycopy(args, 0, a, 0, a.length);
            try {

                toReturn = getValueOfPath(a);


            } catch (Exception E) {
                assert (toReturn instanceof Map<?, ?>);
                try {
                    toReturn = ((Map<?, ?>) toReturn).get(args[i]);
                } catch (Exception A) {
                    throw new IllegalArgumentException("The argument " + args[i - 1] + " was invalid and caused an error.");
                }
                if (toReturn instanceof String && i == args.length - 1)
                    return toReturn;
            }
        }
        assert toReturn != null : "The argument " + args[args.length - 1] + " was invalid and caused an error.";
        return toReturn;
    }
}
