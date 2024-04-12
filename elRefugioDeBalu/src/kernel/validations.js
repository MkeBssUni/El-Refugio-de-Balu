export function isInvalidEmail(email){
    email = email.trim();
    return !email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
}

export function isInvalidPhoneNumber(phone){
    phone = phone.trim();
    return !phone.matches("^[0-9]{10}$");
}

export function isInvalidPassword(password){
    password = password.trim();
    return !password.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\S).{8,}$/);
}

export function isInvalidName(name){
    name = name.trim();
    return !name.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ' .]+$");
}

export function isInvalidImage(image){;
    return !image.matches("data:image/(png|jpg|jpeg);base64,([a-zA-Z0-9+/=])+");
}

export function isNotBlank(string){
    return string.trim() === "";
}

export function isInvalidNoSpecialCharactersString(string) {    
    string = string.trim();
    return string.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ' .0-9]+$");
}

export function isInvalidOnlyNumbersString(string) {
    string = string.trim();
    return !(/^\d+$/).test(string);
  }  