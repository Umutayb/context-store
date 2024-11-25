import context.ContextStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import properties.PropertyUtilities;
import java.util.logging.Logger;

/**
 * Unit test for simple App.
 */
class AppTest {

    Logger log = Logger.getLogger(PropertyUtilities.class.getName());;

    @BeforeEach
    public void before(){
        ContextStore.loadProperties("test.properties");}

    @Test
    public void propertyReadingTest() {
        Assertions.assertEquals("secret!", ContextStore.get("test-secret"), "Incorrect property value!");
        log.info("propertyReadingTest() pass!");
    }

    @Test
    public void defaultValueTest() {
        Assertions.assertEquals("default-value", ContextStore.get("non-existent-property", "default-value"), "Default value mismatch!");
        log.info("defaultValueTest() pass!");
    }

    @Test
    public void loadValueTest() {
        ContextStore.loadProperties("secret.properties");
        Assertions.assertEquals("test-value-2", ContextStore.get("test-property"), "New did not load!");
        log.info("loadValueTest() pass!");
    }
}
