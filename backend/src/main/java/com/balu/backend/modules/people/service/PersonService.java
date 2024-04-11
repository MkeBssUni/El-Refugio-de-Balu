package com.balu.backend.modules.people.service;

import com.balu.backend.kernel.*;
import com.balu.backend.modules.adresses.model.model.Address;
import com.balu.backend.modules.adresses.model.model.IAddressRepository;
import com.balu.backend.modules.adresses.model.model.dto.AddressDto;
import com.balu.backend.modules.hash.service.HashService;
import com.balu.backend.modules.homeSpecification.model.Dto.HomeSpecificationDto;
import com.balu.backend.modules.homeSpecification.model.HomeImage;
import com.balu.backend.modules.homeSpecification.model.HomeSpecification;
import com.balu.backend.modules.homeSpecification.model.Repository.HomeImageRepository;
import com.balu.backend.modules.homeSpecification.model.Repository.HomeSpecificationRepository;
import com.balu.backend.modules.logs.model.LogTypes;
import com.balu.backend.modules.logs.service.LogService;
import com.balu.backend.modules.people.model.*;
import com.balu.backend.modules.people.model.dto.*;
import com.balu.backend.modules.roles.model.IRoleRepository;
import com.balu.backend.modules.roles.model.Role;
import com.balu.backend.modules.roles.model.Roles;
import com.balu.backend.modules.sms.service.SmsService;
import com.balu.backend.modules.users.model.IUserRepository;
import com.balu.backend.modules.users.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class PersonService {
    private final IPersonRepository iPersonRepository;
    private final IUserRepository iUserRepository;
    private final IRoleRepository iRoleRepository;
    private final IAddressRepository iAddressRepository;
    private final HomeSpecificationRepository homeSpecificationRepository;
    private final HomeImageRepository homeImageRepository;
    private final PasswordEncoder encoder;
    private final Validations validations = new Validations();
    private final HashService hashService;
    private final LogService logService;
    private final EmailService emailService;
    private final SmsService smsService;
    private final String tableAffected = "PEOPLE | USERS";

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Boolean> publicRegister(PublicRegisterDto dto) throws Exception {
        if(dto.getUsername() == null || dto.getLastname() == null || dto.getName() == null || dto.getPhoneNumber() == null || dto.getPassword() == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());
        if(validations.isNotBlankString(dto.getUsername()) && validations.isNotBlankString(dto.getPassword()) && validations.isNotBlankString((dto.getName())) && validations.isNotBlankString(dto.getLastname()) && validations.isNotBlankString(dto.getPhoneNumber())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        dto.setName(hashService.decrypt(dto.getName()));
        dto.setLastname(hashService.decrypt(dto.getLastname()));
        dto.setSurname(hashService.decrypt(dto.getSurname()));
        dto.setPassword(hashService.decrypt(dto.getPassword()));
        dto.setPhoneNumber(hashService.decrypt(dto.getPhoneNumber()));
        dto.setUsername(hashService.decrypt(dto.getUsername()));

        if(validations.isInvalidEmail(dto.getUsername())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidPassword(dto.getPassword())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(dto.getName())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(dto.getLastname())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(dto.getSurname())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidPhoneNumber(dto.getPhoneNumber())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        if(validations.isInvalidMinAndMaxLength(dto.getName(), 3,40)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidMinAndMaxLength(dto.getLastname(), 3,40)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidMinAndMaxLength(dto.getSurname(), 3,40)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidMinAndMaxLength(dto.getUsername(), 3,40)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidMinAndMaxLength(dto.getPassword(), 8,40)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        Optional<User> existentUser = iUserRepository.findByUsername(hashService.encrypt(dto.getUsername()));
        if(existentUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        Person person = new Person();
        User user = new User();
        Address address = new Address();
        HomeSpecification homeSpecification = new HomeSpecification();
        HomeImage homeImage = new HomeImage();

        Optional<Role> role = iRoleRepository.findByName(Roles.GENERAL);
        if(role.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.ROLE_NOT_FOUND.name());
        String activationCode = generateRandomString();
        dto.setPhoneNumber(hashService.encrypt(dto.getPhoneNumber()));

        user.save(hashService.encrypt(dto.getUsername()),encoder.encode(dto.getPassword()),role.get(), hashService.encrypt(activationCode));
        person.savePublicRegister(dto,user);

        logService.saveLog("New general user registered: " + person.getName() + " " + person.getLastName(), LogTypes.INSERT, tableAffected);

        if(dto.isViaSms()){
            if(!(sendSms(dto.getPhoneNumber(),activationCode))){
                return new ResponseApi<>(HttpStatus.METHOD_NOT_ALLOWED, true, ErrorMessages.PHONE_NOT_AVAILABLE.name());
            }
        }else{
            emailService.sendEmailNewAccount(dto.getUsername(),activationCode);
        }

        user = iUserRepository.saveAndFlush(user);
        iPersonRepository.saveAndFlush(person);

        address.saveNull(user);
        address = iAddressRepository.saveAndFlush(address);
        homeSpecification.saveNull(address);
        homeSpecification = homeSpecificationRepository.saveAndFlush(homeSpecification);
        address = iAddressRepository.saveAndFlush(address);
        address.setHomeSpecification(homeSpecification);
        homeImage.saveNull(homeSpecification);
        homeImageRepository.saveAndFlush(homeImage);
        return new ResponseApi<>(HttpStatus.CREATED, false,"OK");
    }
    @Transactional(readOnly = true)
    public boolean sendSms(String phoneNumber, String activationCode) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if(phoneNumber.equals("jDoWCITmysR369htx8cO2w==")){
            smsService.sendSMS("Este es el código para activar tu cuenta: "+ activationCode, hashService.decrypt(phoneNumber));
            return true;
        }else{
            return false;
        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<String> saveAdminOrMod(SaveAdminOrModDto dto) throws Exception {
        if(dto.getUsername() == null || dto.getLastname() == null || dto.getName() == null || dto.getPhoneNumber() == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());
        if(validations.isNotBlankString(dto.getUsername()) && validations.isNotBlankString((dto.getName())) && validations.isNotBlankString(dto.getLastname()) && validations.isNotBlankString(dto.getPhoneNumber())) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidEmail(hashService.decrypt(dto.getUsername()))) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(hashService.decrypt(dto.getName()))) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(hashService.decrypt(dto.getLastname()))) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidName(hashService.decrypt(dto.getSurname()))) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidPhoneNumber(hashService.decrypt(dto.getPhoneNumber()))) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        if(validations.isInvalidMinAndMaxLength(hashService.decrypt(dto.getName()), 3,40)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidMinAndMaxLength(hashService.decrypt(dto.getLastname()), 3,40)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidMinAndMaxLength(hashService.decrypt(dto.getSurname()), 3,40)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidMinAndMaxLength(hashService.decrypt(dto.getUsername()), 3,40)) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.INVALID_FIELD.name());

        Optional<User> existentUser = iUserRepository.findByUsername(dto.getUsername());
        if(existentUser.isPresent()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.DUPLICATE_RECORD.name());

        dto.setName(hashService.decrypt(dto.getName()));
        dto.setLastname(hashService.decrypt(dto.getLastname()));
        dto.setRoleId(Long.parseLong(hashService.decrypt(dto.getRoleString())));
        dto.setSurname(hashService.decrypt(dto.getSurname()));

        Optional<Role> role = iRoleRepository.findById(dto.getRoleId());
        if(role.isEmpty()) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.ROLE_NOT_FOUND.name());

        Person person = new Person();
        User user = new User();
        String password = generateRandomString();
        user.save(dto.getUsername(), encoder.encode(password),role.get(),generateRandomString());
        user = iUserRepository.saveAndFlush(user);
        person.saveAdminOrMod(dto,user);
        iPersonRepository.saveAndFlush(person);
        logService.saveLog("New " + role.get().getName() + " registered: " + person.getName() + " " + person.getLastName(), LogTypes.INSERT, tableAffected);
        return new ResponseApi<>(hashService.encrypt(password), HttpStatus.CREATED, false,"OK");
    }
    @Transactional(readOnly = true)
    public ResponseApi<Person> getDetails(PersonDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if(dto.getUserId() == null) return new ResponseApi<>(HttpStatus.BAD_REQUEST,true, ErrorMessages.MISSING_FIELDS.name());
        Optional<User> user = iUserRepository.findById(Long.parseLong(hashService.decrypt(dto.getUserId())));
        if(user.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());
        Optional<Person> person = iPersonRepository.findByUserId(user.get().getId());
        if(person.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());
        return new ResponseApi<>(person.get(), HttpStatus.OK, false, "OK");
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Person> changeStatus(PersonDto dto) throws Exception{
        Optional<Person> person = iPersonRepository.findById(dto.getPersonId());
        if(person.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());

        person.get().getUser().setBlocked(!(person.get().getUser().isBlocked()));
        if(person.get().getUser().isBlocked()){
            person.get().getUser().setBlockedAt(LocalDateTime.now());
        }else{
            person.get().getUser().setBlockedAt(null);
        }
        iUserRepository.saveAndFlush(person.get().getUser());
        logService.saveLog("User with id: " + person.get().getUser().getId() + " blocked changed to " + person.get().getUser().isBlocked(), LogTypes.UPDATE, tableAffected);
        return new ResponseApi<>(iPersonRepository.saveAndFlush(person.get()), HttpStatus.OK, false, "OK");
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<Person> changePassword(ChangePasswordDto dto){
        Optional<Person> person = iPersonRepository.findById(dto.getPersonId());
        if(person.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());

        if(!encoder.matches(dto.getOldPassword(),person.get().getUser().getPassword())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
        if(validations.isInvalidPassword(dto.getNewPassword())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
        person.get().getUser().setPassword(encoder.encode(dto.getNewPassword()));

        person.get().getUser().setAttempts(0);
        person.get().getUser().setBlocked(false);
        person.get().getUser().setBlockedAt(null);

        iUserRepository.saveAndFlush(person.get().getUser());
        logService.saveLog("Password changed for user with id: " + person.get().getUser().getId(), LogTypes.UPDATE, tableAffected);
        return new ResponseApi<>(iPersonRepository.saveAndFlush(person.get()), HttpStatus.OK, false, "OK");
    }
    @Transactional(readOnly = true)
    public ResponseApi<Page<IPersonViewPaged>> getPaged(SearchDto dto, Pageable pageable){
        dto.setSearchValue(dto.getSearchValue().toLowerCase());
        Page<IPersonViewPaged> page = iPersonRepository.findAllPaged(dto.getSearchValue(),dto.getSearchValue(),dto.getSearchValue(),pageable);
        return new ResponseApi<>(page, HttpStatus.OK, false, "OK");
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<String> resetPassword(PersonDto dto) throws Exception {
        dto.setUsername(dto.getUsername());
        Optional<User> user = iUserRepository.findByUsername(dto.getUsername());
        if(user.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());
        if(validations.isInvalidPassword(dto.getNewPassword())) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
        user.get().setPassword(encoder.encode(hashService.decrypt(dto.getNewPassword())));
        user.get().setAttempts(0);
        user.get().setBlocked(false);
        user.get().setBlockedAt(null);
        iUserRepository.saveAndFlush(user.get());
        emailService.passwordChanged(hashService.decrypt(dto.getUsername()));
        logService.saveLog("Password reset for user with id: " + user.get().getId(), LogTypes.UPDATE, "USERS");
        return new ResponseApi<>(HttpStatus.OK, false, "OK");
    }
    private String generateRandomString(){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "._#";
        StringBuilder newPassword = new StringBuilder();
        int randomIndex;
        for (int i = 0; i < 2; i++) {
            randomIndex = (int) (Math.random() * alphabet.length());
            newPassword.append(alphabet.charAt(randomIndex));
            randomIndex = (int) (Math.random() * alphabet.length());
            newPassword.append(randomIndex);
            newPassword.append(alphabet.toUpperCase().charAt(randomIndex));
            randomIndex = (int) (Math.random() * specialCharacters.length());
            newPassword.append(specialCharacters.charAt(randomIndex));
            randomIndex = (int) (Math.random() * 10);
            newPassword.append(randomIndex);
        }
        if(validations.isInvalidPassword(newPassword.toString())) return generateRandomString();
        return newPassword.toString();
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<User> activateAccount(ActivateAccountDto dto){
        Optional<User> user = iUserRepository.findByUsername(dto.getUsername());
        if(user.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());

        if(user.get().getActivationCode().equals(dto.getActivationCode())){
            user.get().setBlocked(false);
            user.get().setActivationCode(null);
            iUserRepository.saveAndFlush(user.get());
            logService.saveLog("User with id: " + user.get().getId() + " activated", LogTypes.UPDATE, tableAffected);
            return new ResponseApi<>(HttpStatus.OK, false, "OK");
        }
        return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<User> sendNewActivationCode(ActivateAccountDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Optional<User> existentUser = iUserRepository.findByUsername(dto.getUsername());
        if(existentUser.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());
        String activationCode = generateRandomString();
        existentUser.get().setActivationCode(hashService.encrypt(activationCode));
        iUserRepository.saveAndFlush(existentUser.get());
        emailService.sendEmailNewAccount(hashService.decrypt(dto.getUsername()),activationCode);
        logService.saveLog("New activation code sent to user with id: " + existentUser.get().getId(), LogTypes.UPDATE, "USERS");
        return new ResponseApi<>(HttpStatus.OK, false, "OK");
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<User> sendNewCode(ActivateAccountDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Optional<User> existentUser = iUserRepository.findByUsername(dto.getUsername());
        if(existentUser.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());
        String activationCode = generateRandomString();
        existentUser.get().setActivationCode(hashService.encrypt(activationCode));
        iUserRepository.saveAndFlush(existentUser.get());
        emailService.sendNewCode(hashService.decrypt(dto.getUsername()),activationCode);
        logService.saveLog("New code sent to user with id: " + existentUser.get().getId(), LogTypes.UPDATE, "USERS");
        return new ResponseApi<>(HttpStatus.OK, false, "OK");
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseApi<User> validateCode(ActivateAccountDto dto){
        Optional<User> existentUser = iUserRepository.findByUsername(dto.getUsername());
        if(existentUser.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());
        if(existentUser.get().getActivationCode().equals(dto.getActivationCode())) return new ResponseApi<>(existentUser.get(),HttpStatus.OK, false, "OK");
        return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.INVALID_FIELD.name());
    }
    @Transactional(readOnly = true)
    public ResponseApi<IContactInfoView> findContactInfo(PersonDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Optional<User> user = iUserRepository.findById(Long.valueOf(hashService.decrypt(dto.getUserId())));
        if(user.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());
        Optional<IContactInfoView> contactInfoView = iPersonRepository.findContactInfoByUserId(user.get().getId());
        return contactInfoView.map(value -> new ResponseApi<>(contactInfoView.get(), HttpStatus.OK, false, "OK")).orElseGet(() -> new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name()));
    }

    @Transactional(readOnly = true)
    public ResponseApi<AllInfoDto> findAllInfo(PersonDto dto) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if(dto.getUserId()==null) return new ResponseApi<>(HttpStatus.BAD_REQUEST, true, ErrorMessages.MISSING_FIELDS.name());
        Optional<User> optionalUser = iUserRepository.findById(Long.valueOf(hashService.decrypt(dto.getUserId())));
        if(optionalUser.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());

        Optional<Person> optionalPerson = iPersonRepository.findByUserId(optionalUser.get().getId());
        if(optionalPerson.isEmpty()) return new ResponseApi<>(HttpStatus.NOT_FOUND, true, ErrorMessages.RECORD_NOT_FOUND.name());

        AllInfoDto allInfoDto = new AllInfoDto();
        AddressDto addressDto = new AddressDto();
        HomeSpecificationDto homeSpecificationDto = new HomeSpecificationDto();

        allInfoDto.setName(optionalPerson.get().getName());
        allInfoDto.setLastname(optionalPerson.get().getLastName());
        allInfoDto.setSurname(optionalPerson.get().getSurName());
        allInfoDto.setUsername(optionalUser.get().getUsername());
        allInfoDto.setPhoneNumber(optionalPerson.get().getPhoneNumber());

        if(optionalUser.get().getAddress()!=null){
            addressDto.setAddressId(hashService.encrypt(optionalUser.get().getAddress().getId()));
            addressDto.setCountry(optionalUser.get().getAddress().getCountry());
            addressDto.setStreet(optionalUser.get().getAddress().getStreet());
            addressDto.setColony(optionalUser.get().getAddress().getColony());
            addressDto.setCity(optionalUser.get().getAddress().getCity());
            addressDto.setState(optionalUser.get().getAddress().getState());
            addressDto.setPostalCode(optionalUser.get().getAddress().getPostalCode());
            addressDto.setAddressReference(optionalUser.get().getAddress().getAddressReference());
            addressDto.setExteriorNumber(optionalUser.get().getAddress().getExteriorNumber());
            addressDto.setInteriorNumber(optionalUser.get().getAddress().getInteriorNumber());
            homeSpecificationDto.setHomeSpecificationId(hashService.encrypt(optionalUser.get().getAddress().getHomeSpecification().getId()));
            homeSpecificationDto.setType(optionalUser.get().getAddress().getHomeSpecification().getType());
            homeSpecificationDto.setOutdoorArea(optionalUser.get().getAddress().getHomeSpecification().isOutdoorArea());
            homeSpecificationDto.setNumberOfResidents(optionalUser.get().getAddress().getHomeSpecification().getNumberOfResidents());
            homeSpecificationDto.setHomeImage(optionalUser.get().getAddress().getHomeSpecification().getHomeImage().getImage());

            addressDto.setHomeSpecification(homeSpecificationDto);
            allInfoDto.setAddressDto(addressDto);
        }
        return new ResponseApi<>(allInfoDto,HttpStatus.OK, false, "INFO");
    }

}
