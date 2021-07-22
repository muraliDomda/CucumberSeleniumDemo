package com.testing;




import cucumber.api.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Lazy
@ComponentScan(basePackages = {"com.testing.pages","com.testing.helper","com.testing.pages.EbayHomePage.hooks","com.testing.*"})
@SpringBootApplication
public class TestApplication {
    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<JavascriptExecutor> javascriptExecutorThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<WebDriverWait> webDriverWaitThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<WebDriverEventListener> webDriverEventListenerThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<Scenario> scenarioThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<String> scenarioId = new ThreadLocal<>();

    public static void main(final String[] args) {
        SpringApplication.run(TestApplication.class, args);

    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean()
    public static PropertiesFactoryBean properties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("application.properties"));
        return bean;
    }

    @Bean
    @Scope("prototype")
    public WebDriver driver(){
        return driverThreadLocal.get();
    }

    @Bean
    @Scope("prototype")
    public WebDriverWait webDriverWait(){
        return webDriverWaitThreadLocal.get();
    }

    @Bean
    @Scope("prototype")
    public Scenario scenario(){
        return scenarioThreadLocal.get();
    }

    @Bean
    @Scope("prototype")
    @Qualifier("javascriptExecutor")
    public JavascriptExecutor executor(){
        return javascriptExecutorThreadLocal.get();
    }

    @Bean
    @Scope("prototype")
    public String scenarioId(){
        return scenarioId.get();
    }
}
