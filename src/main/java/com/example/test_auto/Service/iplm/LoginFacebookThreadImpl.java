package com.example.test_auto.Service.iplm;

import com.example.test_auto.DTO.ResultLoginFB;
import com.example.test_auto.Service.LoginFacebookThread;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class LoginFacebookThreadImpl implements LoginFacebookThread {
    public boolean loginFBLogic() {
        // Khởi tạo trình duyệt Chrome
        System.setProperty("webdriver.chrome.driver", "D:/Users/Document/DataStudy/testJava/test_auto/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = null;
        ResultLoginFB resultLoginFB = new ResultLoginFB();
        try {

            // Tạo một đối tượng Robot để điều khiển vị trí cửa sổ
            options.addArguments("--start-maximized"); // Mở cửa sổ đã được tối đa hóa

            driver = new ChromeDriver(options);

            driver.get("https://www.facebook.com");

            WebElement emailField = driver.findElement(By.id("email"));
            WebElement passwordField = driver.findElement(By.id("pass"));
            WebElement loginButton = driver.findElement(By.name("login"));

            emailField.sendKeys("your_email@example.com");
            passwordField.sendKeys("your_password");
            loginButton.click();

            // Kiểm tra xem bạn đã đăng nhập thành công hay không
            return driver.getCurrentUrl().equals("https://www.facebook.com/");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            assert driver != null;
            driver.quit();
        }
    }

    @Override
    public void loginFB() throws InterruptedException, ExecutionException {
        ResultLoginFB resultLoginFB = new ResultLoginFB();

        int totalThread = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(totalThread);

        //Tạo 1 list Callable để nhật dữ liệu trả ra và thêm vào list Callable
        List<Callable<Boolean>> callables = new ArrayList<>();
        for (int i = 0; i < totalThread; i++) {
            callables.add(this::loginFBLogic);
        }

        // Thực thi các Callable và nhận các Future đại diện cho kết quả
        List<Future<Boolean>> futures = executorService.invokeAll(callables);

        // Lặp qua danh sách Future để lấy kết quả từ các tác vụ
        for (Future<Boolean> future : futures) {
            Boolean result = future.get();
            if (result) {
                resultLoginFB.setCountSuccess(resultLoginFB.getCountSuccess() + 1);
            } else {
                resultLoginFB.setCountFailed(resultLoginFB.getCountFailed() + 1);
            }
        }

        // Đóng ExecutorService
        executorService.shutdown();

        System.out.println("Kết quả: " + resultLoginFB);
//        return resultLoginFB;
    }
}
