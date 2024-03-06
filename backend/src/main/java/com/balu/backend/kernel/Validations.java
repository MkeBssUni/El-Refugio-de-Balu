package com.balu.backend.kernel;

public class Validations {
    public static boolean isInvalidEmail(String email){
        return !email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
    }
    public static boolean isInvalidPhoneNumber(String phoneNumber){
        return !phoneNumber.matches("^[0-9]{10}$");
    }
    public static boolean isInvalidPassword(String password){
        return !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
    }
    public static boolean isInvalidName(String name){
        return !name.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ' .]+$\n");
    }
    public boolean isInvalidImage(String logo){
        return !logo.matches("data:image/(png|jpg|jpeg);base64,([a-zA-Z0-9+/=])+");
    }
}
