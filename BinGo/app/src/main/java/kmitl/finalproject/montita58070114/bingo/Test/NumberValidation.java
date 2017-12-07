package kmitl.finalproject.montita58070114.bingo.Test;


import java.util.regex.Pattern;

public class NumberValidation {
    public ValidationResult validateNumber(String number) {

        try {
            isNumberEmpty(number);
            isNumberPatternCorrect(number);
        } catch (ValidationException e) {
            return new ValidationResult(false, e.getMessage());
        }

        return new ValidationResult(true, "Validate Success");

    }
    private void isNumberEmpty(String number) throws ValidationException {
        if (number.isEmpty()) {
            throw new ValidationException("Number is Empty !");
        }

    }

    private void isNumberPatternCorrect(String email) throws ValidationException {
        String numberPattern = "^(0?[1-9]|[1-9][0-9])$";

        if (!Pattern.matches(numberPattern, email)) {
            throw new ValidationException("Number is incorrect !");
        }

    }

}
