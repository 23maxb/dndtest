import static spark.Spark.*;

public class api {
    public static String search(String s)
    {
        final String[] ret = new String[1];

        get("https://www.dnd5eapi.co/api/" + s, (req, res) -> {
            ret[0] =res.body();
            return 200;
        });
        return ret[0];
    }


}
