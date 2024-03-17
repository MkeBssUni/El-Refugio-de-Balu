package com.balu.backend.kernel;

public class Validations {
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
}
