package ch.awae.mytools.validation;

import ch.awae.mytools.user.UserRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<Role, String> {

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return true;
        }
        try {
            UserRole.byValue(object);
            return true;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }
}