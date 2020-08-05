import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
    // Gets config data from config file a line at a time and passes the data to setConfigData method
    private void getConfigInput(String name_of_file) {
        Path path = Paths.get(name_of_file);

        try {
            List<String> arr_of_configText = Files.readAllLines(path);

            boolean isApplicationData = false; //to check if data in the line belongs to application data
            for (String configText : arr_of_configText) {
                if (configText.contains("application")) {// sets to true when it comes across application
                    isApplicationData = true;
                    continue;
                } else if (configText.trim().equals("")) {// resets back to false when it leaves application data
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
    //Appends "application." to all application data key -"application.name"
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
