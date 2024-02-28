package com.effectiveMobile.testTask.service;

import com.effectiveMobile.testTask.entity.EmailEntity;
import com.effectiveMobile.testTask.entity.UserEntity;
import com.effectiveMobile.testTask.exception.NotFoundException;
import com.effectiveMobile.testTask.repository.EmailRepository;
import com.effectiveMobile.testTask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    public static final String USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_WITH_LOGIN = "Пользователь с логином %s не был найден";
    public static final String EMAIL_IS_NOT_FOUND_MESSAGE = "Email %s не найден";

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;

    @Override
    public void deleteEmail(String login, String deleteEmailAddress) {
        var userEntity = getUserEntity(login);
        var emails = userEntity.getEmails();
        if (emails.size() > 1) {
            var emailEntity = findEmailByAddress(deleteEmailAddress, emails);
            emailRepository.delete(emailEntity);
        }
        //todo with phone throw exception
    }

    @Override
    public void createEmail(String login, String addEmailAddress) {
        var userEntity = getUserEntity(login);
        var emailEntity = new EmailEntity();
        emailEntity.setEmail(addEmailAddress);
        emailEntity.setUser(userEntity);

        emailRepository.save(emailEntity);
    }

    @Override
    public void updateEmail(String login, String oldEmailAddress, String newEmailAddress) {
        var userEntity = getUserEntity(login);
        var emailEntity = findEmailByAddress(oldEmailAddress, userEntity.getEmails());
        emailEntity.setEmail(newEmailAddress);

        emailRepository.save(emailEntity);
    }

    private UserEntity getUserEntity(String login) { //todo maybe move
        return userRepository.findUserByLogin(login).orElseThrow(() ->
                new NotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_WITH_LOGIN.formatted(login)));
    }

    private static EmailEntity findEmailByAddress(String emailAddress, Set<EmailEntity> emails) {
        return emails.stream().filter(emailEntity ->
                        emailEntity
                                .getEmail()
                                .equals(emailAddress))
                .findAny()
                .orElseThrow(() -> new NotFoundException(EMAIL_IS_NOT_FOUND_MESSAGE.formatted(emailAddress)));
    }
}
