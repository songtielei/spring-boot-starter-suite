package com.startersuite.sms.service;

import java.util.Map;

public interface SmsService {

    void send(String mobile, Long templateId, String[] data);

}
