package kmitl.finalproject.montita58070114.bingo.Test;

public class ValidationResult {
    private boolean isValidationValid;
    private String message;

    ValidationResult(boolean isValidationValid, String message) {
        this.isValidationValid = isValidationValid;
        this.message = message;
    }

    public boolean isValidationValid() {
        return isValidationValid;
    }

    public String getMessage() {
        return message;
    }

}