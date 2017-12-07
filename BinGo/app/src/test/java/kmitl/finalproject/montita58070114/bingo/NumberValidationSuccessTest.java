package kmitl.finalproject.montita58070114.bingo;


import org.junit.Test;
import static org.junit.Assert.assertTrue;

import kmitl.finalproject.montita58070114.bingo.Test.NumberValidation;
import kmitl.finalproject.montita58070114.bingo.Test.ValidationResult;

public class NumberValidationSuccessTest {
    @Test
    public void NumberNotNull() {
        NumberValidation validation = new NumberValidation();
        ValidationResult result = validation.validateNumber("9");
        assertTrue(result.getMessage(), result.isValidationValid());
    }

    @Test
    public void NumberIsInCorrectPattern(){
        NumberValidation validation = new NumberValidation();
        ValidationResult result = validation.validateNumber("99");
        assertTrue(result.getMessage(),result.isValidationValid());
    }
}
