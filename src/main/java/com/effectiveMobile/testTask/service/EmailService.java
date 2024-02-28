package com.effectiveMobile.testTask.service;

public interface EmailService {
    void deleteEmail(String login, String deleteEmailAddress);

    void createEmail(String login, String addEmailAddress);

    void updateEmail(String login, String oldEmailAddress, String updateEmailAddress);
}
