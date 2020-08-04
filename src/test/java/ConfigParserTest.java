import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ConfigParserTest {


    @Test
    @DisplayName("Testing to ensure setConfigData method correctly sets the right config data and get method works correctly")
    void testForSetConfigDataStagingAndGetMethod() {
        ConfigParser config = new ConfigParser("./src/main/java/config-staging.txt");
        assertAll(
                () -> assertEquals("sq04_db",config.get("dbname")),
                () -> assertEquals("127.0.0.1",config.get("host")),
                () -> assertEquals("fintek-staging",config.get("application.name")),
                () -> assertEquals("staging",config.get("mode")),
                () -> assertEquals("/api/v1",config.get("application.context-url")),
                () -> assertEquals("8081",config.get("application.port")),
                () -> assertEquals("fast-staging",config.get("pipeline")),
                () -> assertEquals("blue",config.get("theme"))
        );
    }

    @Test
    @DisplayName("Testing to ensure setConfigData method correctly sets the right config data")
    void testForSetConfigDataDevelopment() {
        ConfigParser config = new ConfigParser("./src/main/java/config-dev.txt");
        assertAll(
                () -> assertEquals("sq04_db-development",config.get("dbname")),
                () -> assertEquals("127.0.0.1",config.get("host")),
                () -> assertEquals("fintek-development",config.get("application.name")),
                () -> assertEquals("development",config.get("mode")),
                () -> assertEquals("/api/v1",config.get("application.context-url")),
                () -> assertEquals("8082",config.get("application.port")),
                () -> assertEquals("fast-development",config.get("pipeline")),
                () -> assertEquals("yellow",config.get("theme"))
        );
    }

    @Test
    @DisplayName("Testing to ensure setConfigData method correctly sets the right config data and get method works correctly")
    void testForSetConfigDataProductionAndGetMethod() {
        ConfigParser config = new ConfigParser("./src/main/java/config.txt");
        assertAll(
                () -> assertEquals("sq04_db",config.get("dbname")),
                () -> assertEquals("127.0.0.1",config.get("host")),
                () -> assertEquals("fintek",config.get("application.name")),
                () -> assertEquals("production",config.get("mode")),
                () -> assertEquals("/api/v1",config.get("application.context-url")),
                () -> assertEquals("8080",config.get("application.port")),
                () -> assertEquals("fast",config.get("pipeline")),
                () -> assertEquals("green",config.get("theme"))
        );
    }


}