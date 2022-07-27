package com.cihanpacal.dininghall.validation;

import org.passay.*;
import org.passay.spring.SpringMessageResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {


    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        Locale trlocale= Locale.forLanguageTag("tr_TR");

        PasswordValidator passwordValidator=new PasswordValidator(
                new SpringMessageResolver(messageSource,trlocale),
                Arrays.asList(
                        new LengthRule(8,20),
                        new CharacterRule(EnglishCharacterData.UpperCase,1),
                        new CharacterRule(EnglishCharacterData.LowerCase,1),
                        new CharacterRule(EnglishCharacterData.Digit,1),
                        new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false,false),
                        new IllegalSequenceRule(EnglishSequenceData.Numerical,5,false,false),
                        new WhitespaceRule(MatchBehavior.Contains,false)
                )
        );



        RuleResult result=passwordValidator.validate(new PasswordData(password));


        if(result.isValid()){
            return true;
        }

        constraintValidatorContext.disableDefaultConstraintViolation();

        constraintValidatorContext.buildConstraintViolationWithTemplate(
                passwordValidator.getMessages(result).stream().collect(Collectors.joining(","))
        ).addConstraintViolation();

        return false;
    }
}
