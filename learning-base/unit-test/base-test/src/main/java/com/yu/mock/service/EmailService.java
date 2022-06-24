package com.yu.mock.service;

/**
 * @author YU
 * @date 2022-06-18 9:55
 */
public class EmailService {
    public void sendEmail(String emailAddress, String emailSubject, String emailContent){
        System.out.println(emailAddress + "已发送" + emailContent + "给" + emailSubject);
    }
}
