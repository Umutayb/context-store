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
        Assert.assertEquals("Value mismatch!", "default-value", ContextStore.get("non-existent-property", "default-value"));
        log.info("defaultValueTest() pass!");
    }

    @Test
    public void loadValueTest() {
        ContextStore.loadProperties("secret.properties");
        Assert.assertEquals("New did not load!", "test-value-2", ContextStore.get("test-property"));
        log.info("loadValueTest() pass!");
    }

    @Test
    public void getBooleanTest() {
        Assert.assertTrue("Value mismatch!", ContextStore.getBoolean("test-bool"));
        log.info("getBooleanTest() pass!");
    }

    @Test
    public void getBooleanDefaultTest() {
        Assert.assertTrue("Value mismatch!", ContextStore.getBoolean("test-false-bool", true));
        log.info("getBooleanDefaultTest() pass!");
    }

    @Test
    public void getBooleanUnspecifiedDefaultTest() {
        Assert.assertFalse("Value mismatch!", ContextStore.getBoolean("test-false-bool"));
        log.info("getBooleanUnspecifiedDefaultTest() pass!");
    }

    @Test
    public void getBooleanIncorrectDefaultTest() {
        Assert.assertFalse("Value mismatch!", ContextStore.getBoolean("test-false-primitive"));
        log.info("getBooleanIncorrectDefaultTest() pass!");
    }

    @Test
    public void getIntTest() {
        Assert.assertEquals("Value mismatch!", 15, ContextStore.getInt("test-int"));
        log.info("getIntTest() pass!");
    }

    @Test
    public void getIntDefaultTest() {
        Assert.assertEquals("Value mismatch!", 10, ContextStore.getInt("test-false-int", 10));
        log.info("getIntDefaultTest() pass!");
    }

    @Test
    public void getIntUnspecifiedDefaultTest() {
        Assert.assertEquals("Value mismatch!", 0, ContextStore.getInt("test-false-int"));
        log.info("getIntUnspecifiedDefaultTest() pass!");
    }

    @Test
    public void getIntIncorrectDefaultTest() {
        Assert.assertEquals("Value mismatch!", 0, ContextStore.getInt("test-false-primitive"));
        log.info("getIntIncorrectDefaultTest() pass!");
    }

    @Test
    public void getDoubleTest() {
        Assert.assertTrue("Value mismatch!", 4.3 == ContextStore.getDouble("test-double"));
        log.info("getDoubleTest() pass!");
    }

    @Test
    public void getDoubleDefaultTest() {
        Assert.assertTrue("Value mismatch!", 10.0 == ContextStore.getDouble("test-false-double", 10.0));
        log.info("getDoubleDefaultTest() pass!");
    }

    @Test
    public void getDoubleUnspecifiedDefaultTest() {
        Assert.assertTrue("Value mismatch!", 0.0 == ContextStore.getDouble("test-false-double"));
        log.info("getDoubleUnspecifiedDefaultTest() pass!");
    }

    @Test
    public void getDoubleIncorrectDefaultTest() {
        Assert.assertTrue("Value mismatch!", 0.0 == ContextStore.getDouble("test-false-primitive"));
        log.info("getDoubleIncorrectDefaultTest() pass!");
    }
}
