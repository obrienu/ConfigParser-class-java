import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigParser {
    private Map<String, String> config = new HashMap<>();
    /**
     *
     * @param name_of_file
     */
    public ConfigParser(String name_of_file) {
        //dynamically sets the config file based on the name of the file
        getConfigInput(name_of_file);
    }


    /**
     *
     * @return
     */
    // Gets config data from config file and passes the data to setConfigData method
    public void getConfigInput(String name_of_file) {
        Path path = Paths.get(name_of_file);

        try {
            List<String> arr_of_onfigText = Files.readAllLines(path);
            boolean isApplicationData = false;
            for (String configText : arr_of_onfigText) {
                if (configText.contains("application")) {
                    isApplicationData = true;
                    continue;
                } else if (configText.trim().equals("")) {
                    isApplicationData = false;
                    continue;
                }
                setConfigData(configText, isApplicationData);
            }
        }
        catch(FileNotFoundException e){
            e.getMessage();
        }
        catch(IOException e){
                e.getMessage();
            }
        catch(Exception e){
                e.getMessage();
            }
        }

    /**
     *
     * @param configKeyValuePair
     */
    //Splits config data into key value pair and save them to Map
    private void setConfigData(String configKeyValuePair, boolean isApplicationData) {
        String[] arrOfKeyAndValue = configKeyValuePair.split("=");
        String key = isApplicationData ? "application." + arrOfKeyAndValue[0]
                : arrOfKeyAndValue[0];
        if (!config.containsKey(key)) {
            config.put(key, arrOfKeyAndValue[1]);
        }
    }


    /**
     *
     * @param key
     * @return
     */
    //checks if a key exists
    public boolean containsKey(Object key) {
        return config.containsKey(key);
    }

    /**
     *
     * @param key
     * @return
     */
    // returns value for valid keys
    public String get(Object key) {
        return config.get(key);
    }

    /**
     *
     * @return
     */
    //return a set of all keys
    public Set<String> keySet() {
        return config.keySet();
    }

}
