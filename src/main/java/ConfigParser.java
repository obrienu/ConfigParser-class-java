import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigParser {
    private Map<String, String> config = new HashMap<>();
    private String production = "./src/main/java/config.txt";
    private String staging = "./src/main/java/config.txt.staging";
    private String development = "./src/main/java/config.txt.dev";
    private String name_of_file;

    /**
     *
     * @param name_of_file
     */
    public ConfigParser(String name_of_file) {
        //dynamically sets the config file based on the name of the file
        if("staging".equals(name_of_file)) {
            this.name_of_file = staging;
        } else if("development".equals(name_of_file)) {
            this.name_of_file = development;
        }
        getConfigInput();
    }

    public ConfigParser() {
        //defaults to production in absence of a name of file
        this.name_of_file = production;
        getConfigInput();
    }

    /**
     *
     * @return
     */
    // Gets config data from config file and passes the data to setConfigData method
    public String getConfigInput() {

        StringBuffer configText = new StringBuffer();
        int data;
        File configFile = new File(name_of_file);

        try(Reader bufferedInputStream = new BufferedReader( new FileReader(configFile))) {
            while((data = bufferedInputStream.read())  != -1) {
                //Build returned data from input file
                configText.append((char) data);
            }
            setConfigData(configText.toString());

            return configText.toString();
        }catch (FileNotFoundException e){
            e.getMessage();
        }
        catch (IOException e) {
            e.getMessage();
        }
        catch (Exception e) {
            e.getMessage();
        }
        return configText.toString();
    }

    /**
     *
     * @param str
     */
    //Splits config data into key value pair and save them to Map
    private void setConfigData(String str) {
        System.out.println(str);
        String key;
        str = str.replaceAll("\\s", " ");
        /*The regex splits the string into groups of key and value pair
        *It further spits each group into two group, group1 = key and group2 = value
         */
        Pattern pattern = Pattern.compile("((?:\\[\\w+\\]\\s)?\\w+(?:-?\\w+)*)=(\\/?(?:\\w+)(?:(?:\\/|-|\\.)?\\w)+)",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            key = matcher.group(1);
            //replace spaces in key with '.' and remove square brackets
            key = key.replaceAll("\\s", ".").replaceAll("(\\[|\\])", "");
            if (!config.containsKey(key)) {
                config.put(key, matcher.group(2));
            }
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
