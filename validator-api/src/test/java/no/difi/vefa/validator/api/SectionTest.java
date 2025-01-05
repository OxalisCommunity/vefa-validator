package no.difi.vefa.validator.api;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.mockito.Mockito;

import no.difi.xsd.vefa.validator._1.AssertionType;
import no.difi.xsd.vefa.validator._1.FlagType;

public class SectionTest {

    @Test
    public void simpleNullFlag() {
        FlagFilterer flagFilterer = Mockito.mock(FlagFilterer.class);
        Section section = new Section(flagFilterer);

        section.add("TEST", "Simple test", null);

        assertEquals(section.getAssertion().size(), 0);
        assertEquals(section.getFlag(), FlagType.OK);

        Mockito.verify(flagFilterer).filterFlag(Mockito.any(AssertionType.class));
        Mockito.verifyNoMoreInteractions(flagFilterer);
    }

    @Test
    public void simpleOkFlag() {
        FlagFilterer flagFilterer = Mockito.mock(FlagFilterer.class);
        Section section = new Section(flagFilterer);

        section.add("TEST", "Simple test", FlagType.OK);

        assertEquals(section.getAssertion().size(), 1);
        assertEquals(section.getFlag(), FlagType.OK);

        Mockito.verify(flagFilterer).filterFlag(Mockito.any(AssertionType.class));
        Mockito.verifyNoMoreInteractions(flagFilterer);
    }

    @Test
    public void simpleWarningFlag() {
        FlagFilterer flagFilterer = Mockito.mock(FlagFilterer.class);
        Section section = new Section(flagFilterer);

        section.add("TEST", "Simple test", FlagType.WARNING);

       assertEquals(section.getAssertion().size(), 1);
       assertEquals(section.getFlag(), FlagType.WARNING);

        Mockito.verify(flagFilterer).filterFlag(Mockito.any(AssertionType.class));
        Mockito.verifyNoMoreInteractions(flagFilterer);
    }
}
