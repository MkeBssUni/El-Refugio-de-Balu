package com.balu.backend.kernel;
import java.util.Base64;
import java.util.regex.Pattern;
public class Validations {
    private static final Pattern BASE64_PATTERN = Pattern.compile("data:image/(png|jpeg|jpg);base64,([\\w\\d+/=]+)");
    private static final String JPEG_HEADER = "data:image/jpeg;base64,";
    private static final String PNG_HEADER = "data:image/png;base64,";

    public boolean isInvalidEmail(String email){
        return !email.matches("^[a-zA-Z\\d._-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,4}$");
    }
    public boolean isInvalidPhoneNumber(String phoneNumber){
        return !phoneNumber.matches("^[\\d]{10}$");
    }
    public boolean isInvalidPassword(String password){
        return !password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
    }
    public boolean isInvalidName(String name){
        return !name.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ' .]+$");
    }
    public boolean isInvalidImage(String logo){
        return !logo.matches("data:image/(png|jpg|jpeg);base64,([a-zA-Z\\d+/=])+");
    }
    public boolean isNotBlankString(String... args){
        for (String arg : args) {
            if(arg.isBlank()) return true;
        }
        return false;
    }

    public boolean isValidBase64Image(String base64String) {
        if (!BASE64_PATTERN.matcher(base64String).matches()) {
            return true;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(base64String.split(",")[1]);

        String header = base64String.split(",")[0];
        return !header.equals(JPEG_HEADER) || !header.equals(PNG_HEADER);
    }

    public boolean isValidBooleanStatus(String str) {
        String lowerCaseStr = str.toLowerCase();
        if(lowerCaseStr.equals("true") || lowerCaseStr.equals("false")) return false;
        return  true ;
    }
    public boolean isInvalidId(String str) {
        try {
            Long.parseLong(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean isInvalidMinAndMaxLength(String str, int min, int max) {
        return str.length() < min || str.length() > max;
    }

    public boolean isInvalidEnum(String str, Class<? extends Enum<?>> enumClass) {
        for (Enum<?> e : enumClass.getEnumConstants()) {
            if (e.name().equals(str)) return false;
        }
        return true;
    }
}
