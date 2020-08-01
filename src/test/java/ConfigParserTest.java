import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigParserTest {
    ConfigParser config = null;
    @Test
    @DisplayName("Test getConfigInput with an empty constructor parameter")
    void getConfigInputWithEmptyConstructor() {
        config = new ConfigParser();
        String expected = config.getConfigInput();
        String actual = "dbname=sq04_db\n" +
                "host=127.0.0.1\n" +
                "\n" +
                "[application]\n" +
                "name=fintek\n" +
                "port=8080\n" +
                "context-url=/api/v1\n" +
                "\n" +
                "mode=production\n" +
                "theme=green\n" +
                "pipeline=fast\n" +
                "\n" +
                "[application]\n" +
                "name=fintrack";
        assertEquals(expected, actual, "Method should return correct config text");
    }



    @Test
    @DisplayName("Test getConfigInput with a development as constructor parameter")
    void getConfigInputWithConstructor() {
        config = new ConfigParser("development");
        String expected = config.getConfigInput();
        String actual = "dbname=sq04_db-development\n" +
                "host=127.0.0.1\n" +
                "\n" +
                "\n" +
                "[application]\n" +
                "name=fintek-development\n" +
                "port=8082\n" +
                "context-url=/api/v1\n" +
                "\n" +
                "mode=development\n" +
                "theme=yellow\n" +
                "pipeline=fast-development\n" +
                "\n" +
                "[application]\n" +
                "name=fintrack-development";
        assertEquals(expected, actual, "Method should return correct config text");
    }



    @Test
    @DisplayName("Test getConfigInput with a development as constructor parameter")
    void getConfigInputWithStagingConstructorParameter() {
        config = new ConfigParser("staging");
        String expected = config.getConfigInput();
        String actual = "dbname=sq04_db\n" +
                "host=127.0.0.1\n" +
                "\n" +
                "[application]\n" +
                "name=fintek-staging\n" +
                "port=8081\n" +
                "context-url=/api/v1\n" +
                "\n" +
                "mode=staging\n" +
                "theme=blue\n" +
                "pipeline=fast-staging\n" +
                "\n" +
                "[application]\n" +
                "name=fintrack-staging";
        assertEquals(expected, actual, "Method should return correct config text");
    }

    @Test
    @DisplayName("Testing to ensure setConfigData method correctly sets the right config data and get method works correctly")
    void testForSetConfigDataStagingAndGetMethod() {
        config = new ConfigParser("staging");
        assertAll(
                () -> assertEquals("sq04_db",config.get("dbname")),
                () -> assertEquals("127.0.0.1",config.get("host")),
                () -> assertEquals("fintek-staging",config.get("application.name")),
                () -> assertEquals("staging",config.get("mode")),
                () -> assertEquals("/api/v1",config.get("context-url")),
                () -> assertEquals("8081",config.get("port")),
                () -> assertEquals("fast-staging",config.get("pipeline")),
                () -> assertEquals("blue",config.get("theme"))
        );
    }

    @Test
    @DisplayName("Testing to ensure setConfigData method correctly sets the right config data")
    void testForSetConfigDataDevelopment() {
        config = new ConfigParser("development");
        assertAll(
                () -> assertEquals("sq04_db-development",config.get("dbname")),
                () -> assertEquals("127.0.0.1",config.get("host")),
                () -> assertEquals("fintek-development",config.get("application.name")),
                () -> assertEquals("development",config.get("mode")),
                () -> assertEquals("/api/v1",config.get("context-url")),
                () -> assertEquals("8082",config.get("port")),
                () -> assertEquals("fast-development",config.get("pipeline")),
                () -> assertEquals("yellow",config.get("theme"))
        );
    }

    @Test
    @DisplayName("Testing to ensure setConfigData method correctly sets the right config data and get method works correctly")
    void testForSetConfigDataProductionAndGetMethod() {
        config = new ConfigParser();
        assertAll(
                () -> assertEquals("sq04_db",config.get("dbname")),
                () -> assertEquals("127.0.0.1",config.get("host")),
                () -> assertEquals("fintek",config.get("application.name")),
                () -> assertEquals("production",config.get("mode")),
                () -> assertEquals("/api/v1",config.get("context-url")),
                () -> assertEquals("8080",config.get("port")),
                () -> assertEquals("fast",config.get("pipeline")),
                () -> assertEquals("green",config.get("theme"))
        );
    }


}