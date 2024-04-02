package com.balu.backend.modules.sms.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService{
    @Value("${TWILIO_ACCOUNT_SID}")
    String sid;
    @Value("${TWILIO_AUTH_TOKEN}")
    String token;
    @Value("${TWILIO_NUMBER}")
    String phoneNumber;

    public void sendSMS(String message, String phone){
        Twilio.init(sid,token);
        phone = "+52"+phone;
        Message.creator(new PhoneNumber(phone),
                new PhoneNumber(phoneNumber),
                message).create();
    }
}
