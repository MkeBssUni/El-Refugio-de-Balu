export function isInvalidEmail(email){
    email = email.trim();
    return !RegExp("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$").test(email);
}

export function isInvalidPhoneNumber(phone){
    phone = phone.trim();
    return !RegExp("^[0-9]{10}$").test(phone);
}

export function isInvalidPassword(password){
    password = password.trim();
    return !RegExp("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$").test(password);
}

export function isInvalidName(name){
    name = name.trim();
    return !RegExp("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ' .]+$").test(name);
}
export function isInvalidImage(image){;
    return !image.matches("data:image/(png|jpg|jpeg);base64,([a-zA-Z0-9+/=])+");
}

export function isNotBlank(string){
    return string.trim() === "";
}