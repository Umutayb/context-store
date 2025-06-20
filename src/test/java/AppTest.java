import context.ContextStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import properties.PropertyUtilities;

import java.util.logging.Logger;


/**
 * Unit test for simple App.
 */
public class AppTest {

    Logger log = Logger.getLogger(PropertyUtilities.class.getName());;

    @Before
    public void before(){
        ContextStore.loadProperties("test.properties");}

    @Test
    public void propertyReadingTest() {
        Assert.assertEquals("Incorrect property value!","secret!", ContextStore.get("test-secret"));
        log.info("propertyReadingTest() pass!");
    }

    @Test
    public void defaultValueTest() {
        Assert.assertEquals("Default value mismatch!", "default-value", ContextStore.get("non-existent-property", "default-value"));
        log.info("defaultValueTest() pass!");
    }

    @Test
    public void loadValueTest() {
        ContextStore.loadProperties("secret.properties");
        Assert.assertEquals("New did not load!", "test-value-2", ContextStore.get("test-property"));
        log.info("loadValueTest() pass!");
    }
}
