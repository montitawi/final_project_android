package kmitl.finalproject.montita58070114.bingo;

import org.junit.Test;

import kmitl.finalproject.montita58070114.bingo.Test.NumberValidation;
import kmitl.finalproject.montita58070114.bingo.Test.ValidationResult;

import static org.junit.Assert.assertFalse;

public class NumberValidationFailTest {


    @Test
    public void numberIsNull() {
        NumberValidation validation = new NumberValidation();
        ValidationResult result = validation.validateNumber("");
        assertFalse(result.getMessage(), result.isValidationValid());
    }

    @Test
    public void numberIsInCorrectPattern() {
        NumberValidation validation = new NumberValidation();
        ValidationResult result = validation.validateNumber("100");
        assertFalse(result.getMessage(), result.isValidationValid());
    }


}

