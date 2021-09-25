public class main {
    public static void main(String[] args) throws Exception {
        System.out.println(api.searchcurl("https://www.dnd5eapi.co/api/spells/acid-arrow/"));
        System.out.println("Run a command?");
        String j = userInput.promptUser();
        System.out.println(j);


        while (j.compareTo("stop") != 0) {
            userInput.runThisCode("");
            j = userInput.promptUser();
            System.out.println(j);
        }
    }
}