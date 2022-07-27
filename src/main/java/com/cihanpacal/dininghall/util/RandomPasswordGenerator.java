package com.cihanpacal.dininghall.util;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class RandomPasswordGenerator {

    public static String generate(){
        CharacterRule digits=new CharacterRule(EnglishCharacterData.Digit);
        CharacterRule lowerCaseChar=new CharacterRule(EnglishCharacterData.LowerCase);
        CharacterRule upperCaseChar=new CharacterRule(EnglishCharacterData.UpperCase);
        CharacterRule special=new CharacterRule(new CharacterData() {
            @Override
            public String getErrorCode() {
                return "desteklenmeyen karakter";
            }

            @Override
            public String getCharacters() {
                return ".-_";
            }
        });

        PasswordGenerator passwordGenerator=new PasswordGenerator();

        return passwordGenerator.generatePassword(12,digits,lowerCaseChar,upperCaseChar,special);
    }
}
