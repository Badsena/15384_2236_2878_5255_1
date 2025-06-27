package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;

public class WebFormAutomation {

    public static void main(String[] args) {
        // ✅ Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // ✅ Resolve local HTML file to file:// URL
        File file = new File("src/main/resources/web-form.html");
        String htmlFilePath = file.toURI().toString();
        System.out.println("Resolved HTML path: " + htmlFilePath);

        // ✅ Chrome headless options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = null;

        try {
            driver = new ChromeDriver(options);
            System.out.println("WebDriver initialized.");

            driver.get(htmlFilePath);
            System.out.println("Opened: " + htmlFilePath);

            // ✅ Fill form elements with IDs matching your HTML
            WebElement fullNameInput = driver.findElement(By.id("fullName"));
            fullNameInput.sendKeys("Automated Test User");
            System.out.println("Entered full name.");

            WebElement emailInput = driver.findElement(By.id("emailAddress"));
            emailInput.sendKeys("test.user@example.com");
            System.out.println("Entered email.");

            WebElement passwordInput = driver.findElement(By.id("userPassword"));
            passwordInput.sendKeys("MySecurePass123");
            System.out.println("Entered password.");

            WebElement commentsArea = driver.findElement(By.id("userComments"));
            commentsArea.sendKeys("This is a sample automated feedback.");
            System.out.println("Entered comments.");

            WebElement submitButton = driver.findElement(By.id("submitButton"));
            submitButton.click();
            System.out.println("Clicked the submit button.");

            System.out.println("\n✅ Form automation completed successfully.");

        } catch (Exception e) {
            System.err.println("❌ An error occurred:");
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("WebDriver closed.");
            }
            // ✅ This kills any lingering Selenium threads cleanly when using exec-maven-plugin
            System.exit(0);
        }
    }
}
