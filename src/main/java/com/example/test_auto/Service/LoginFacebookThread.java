package com.example.test_auto.Service;

import com.example.test_auto.DTO.ResultLoginFB;

import java.util.concurrent.ExecutionException;

public interface LoginFacebookThread {
    void loginFB() throws InterruptedException, ExecutionException;
}
