package com.example.passportapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PassportValidatorTest {

    private PassportValidator validator;

    @Before
    public void setUp() {
        validator = new PassportValidator();
    }


    @Test
    public void isFormValid_validFirstAndLastName_returnsTrue() {
        assertTrue(validator.isFormValid("Jan", "Nowak"));
    }

    @Test
    public void isFormValid_emptyFirstName_returnsFalse() {
        assertFalse(validator.isFormValid("", "Nowak"));
    }

    @Test
    public void isFormValid_emptyLastName_returnsFalse() {
        assertFalse(validator.isFormValid("Jan", ""));
    }

    @Test
    public void isFormValid_nullFirstName_returnsFalse() {
        assertFalse(validator.isFormValid(null, "Nowak"));
    }

    @Test
    public void isFormValid_nullLastName_returnsFalse() {
        assertFalse(validator.isFormValid("Jan", null));
    }

    @Test
    public void isFormValid_onlyWhitespaces_returnsFalse() {
        assertFalse(validator.isFormValid("   ", "   "));
    }


    @Test
    public void buildResultMessage_correctData_returnsFormattedString() {
        String expected = "Jan Nowak kolor oczu piwne";
        String actual = validator.buildResultMessage("Jan", "Nowak", "piwne");
        assertEquals(expected, actual);
    }

    @Test
    public void buildResultMessage_namesWithWhitespaces_returnsTrimmedString() {
        String expected = "Jan Nowak kolor oczu zielone";
        String actual = validator.buildResultMessage("  Jan  ", " Nowak ", "zielone");
        assertEquals(expected, actual);
    }


    @Test
    public void getEyeColor_blueSelected_returnsNiebieskie() {
        assertEquals("niebieskie", validator.getEyeColor(true, false, false));
    }

    @Test
    public void getEyeColor_greenSelected_returnsZielone() {
        assertEquals("zielone", validator.getEyeColor(false, true, false));
    }

    @Test
    public void getEyeColor_hazelSelected_returnsPiwne() {
        assertEquals("piwne", validator.getEyeColor(false, false, true));
    }

    @Test
    public void getEyeColor_noneSelected_returnsDefaultNiebieskie() {
        assertEquals("niebieskie", validator.getEyeColor(false, false, false));
    }
}