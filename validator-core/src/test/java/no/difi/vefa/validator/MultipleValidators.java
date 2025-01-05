package no.difi.vefa.validator;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.assertNotNull;

import no.difi.vefa.validator.source.ClasspathSource;

/**
 * Testing opening and closing two validators in row.
 */
public class MultipleValidators {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        validator = ValidatorBuilder.newValidator().setSource(new ClasspathSource("/rules/")).build();
    }

    @AfterClass
    public static void tearDown() {
        validator.close();
    }

    @Test
    public void test1() {
        assertNotNull(validator);
    }

    @Test
    public void test2() {
        assertNotNull(validator);
    }

}
