package com.example.passportapp;

public class PassportValidator {

    public boolean isFormValid(String firstName, String lastName) {
        if (firstName == null || lastName == null) return false;
        return !firstName.trim().isEmpty() && !lastName.trim().isEmpty();
    }

    public String buildResultMessage(String firstName, String lastName, String eyeColor) {
        return firstName.trim() + " " + lastName.trim() + " kolor oczu " + eyeColor;
    }

    public String getEyeColor(boolean isBlue, boolean isGreen, boolean isHazel) {
        if (isGreen) return "zielone";
        if (isHazel) return "piwne";
        return "niebieskie";
    }
}