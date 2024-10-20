package one.lab.petproject.aop;

import lombok.extern.slf4j.Slf4j;
import one.lab.petproject.model.Users;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class DatabaseAspect {

    @After("execution(* one.lab.petproject.repository.impl.UserRepositoryImpl.saveUser(..)) && args(user)")
    public void logAfterUserSaved(Users user) {
        log.info("User has been saved successfully: {}", user);
    }

    @Before("execution(* one.lab.petproject.service.impl.UserServiceImpl.showAllUsers(..))")
    public void logBeforeShowAllUsers() {
        log.info("Starting to show all users from db:");
    }

    @After("execution(* one.lab.petproject.service.impl.UserServiceImpl.showAllUsers(..))")
    public void logAfterShowAllUsers() {
        log.info("End of the list\n");
    }
}
