import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class Main {
    
    public static void main(String[] args) {
        ConfigParser config = null;
        String production = new File("./src/main/java/config.txt").exists() ? "./src/main/java/config.txt"
                : "config.txt";
        String staging = new File("./src/main/java/config-staging.txt").exists() ? "./src/main/java/config-staging.txt"
                : "config-staging.txt";
        String development = new File("./src/main/java/config-dev.txt").exists() ? "./src/main/java/config-dev.txt"
                : "config-dev.txt";

        //instantiate config based on commandline argument
        if(args.length == 0) {
            config = new ConfigParser(production);
        }else if("staging".equals(args[0])){
            config = new ConfigParser(staging);
        } else if("development".equals(args[0])){
            config = new ConfigParser(development);
        }else{
            System.out.println("Please enter a write argument. \n Argument can either be 'staging', 'development' or left empty.");
        }

        //call appMenu only when config is correctly instantiated
        if(config != null){
            appMenu(config);
        }

    }

    public static void appMenu(ConfigParser config) {
        String input;
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("****Menu*****");
            System.out.println("--------------");
            System.out.println("YOU ARE IN " + config.get("mode").toUpperCase() + " MODE");
            System.out.println("Enter 'all' to view all config data");
            System.out.println("enter 'view' to view a particular config value");
            System.out.println("Enter 'exit' to quit");

            System.out.print("Enter option: ");
            input = scanner.nextLine();
            //call a method based on the user input
            switch (input.trim().toLowerCase()) {
                case "all":
                    showAllConfigValues(config);
                    break;
                case "view":
                    while (true) {
                        System.out.print("Enter 'exit' to quit or the config variable you want to check: ");
                        input = scanner.nextLine();
                        if ("exit".equals(input.trim().toLowerCase())) {
                            break;
                        }
                        showConfigValuesByKey(config, input.trim().toLowerCase());
                    }
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("invalid input");
            }
        }

    }

    //displays all the key value pairs
    public static void showAllConfigValues(ConfigParser config) {
        Set<String> keys = config.keySet();
        keys.forEach((key) -> System.out.println(key+ " = " + config.get(key) ));
    }

    //Displays values for only valid keys
    public static void showConfigValuesByKey(ConfigParser config, String key) {
        if(config.containsKey(key)) {
            System.out.println(key + " = " + config.get(key));
        }else{
            System.out.println("Invalid key");
        }
    }

}