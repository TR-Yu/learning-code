package com.yu.mock.entity.service;

import com.yu.mock.entity.User;
import com.yu.mock.reposity.UserRepository;

/**
 * @author YU
 * @date 2022-06-18 9:54
 */
public class UserService {
    private UserRepository userRepository;
    private EmailService emailService;
    private EncryptionService encryptionService;

    public UserService(UserRepository userRepository, EmailService emailService, EncryptionService encryptionService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.encryptionService = encryptionService;
    }

    public void register(User user) {
        user.setPassword(encryptionService.sha256(user.getPassword()));

        userRepository.saveUser(user);

        String emailSubject = "Register Notification";
        String emailContent = "Register Account successful! your username is " + user.getName();
        emailService.sendEmail(user.getEmail(), emailSubject, emailContent);
    }
}
