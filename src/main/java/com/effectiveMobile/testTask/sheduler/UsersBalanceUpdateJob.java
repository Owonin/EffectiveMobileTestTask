package com.effectiveMobile.testTask.sheduler;

import com.effectiveMobile.testTask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsersBalanceUpdateJob implements Job {

    private final UserService userService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        userService.increaseAllUsersBalance();
    }
}
