package com.example.test_auto;

import com.example.test_auto.DTO.ResultLoginFB;
import com.example.test_auto.Service.LoginFacebookThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class TestAutoApplication {

    @Autowired
    private LoginFacebookThread loginFacebookThread;

    public static void main(String[] args) {
        SpringApplication.run(TestAutoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runThread() throws ExecutionException, InterruptedException {
        this.loginFacebookThread.loginFB();
//        this.loginFacebookThread.getChromeLive();
    }
}
