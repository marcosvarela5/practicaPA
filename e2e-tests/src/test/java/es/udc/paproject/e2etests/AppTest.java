package es.udc.paproject.e2etests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        //options.addArguments("--headless"); // Si necesitas que sea sin cabeza
        options.addArguments("--remote-debugging-port=9222");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testLogin() {
        login("testparticipant1", "pa2324");
    }

    @Test
    void testTrialDetails() {
        login("testparticipant1", "pa2324");

        WebElement trialTypeElement = driver.findElement(By.id("trialTypeId"));
        WebElement provinceTypeElement = driver.findElement(By.id("provinceId"));
        WebElement startDateSelector = driver.findElement(By.id("startDate"));
        WebElement endDateSelector = driver.findElement(By.id("endDate"));
        WebElement submitTrialSearchButton = driver.findElement(By.id("submitTrialSearchButton"));

        Select trialTypeSelect = new Select(trialTypeElement);
        Select provinceTypeSelect = new Select(provinceTypeElement);

        trialTypeSelect.selectByVisibleText("Running");
        provinceTypeSelect.selectByVisibleText("A Coruña");
        startDateSelector.sendKeys("01-01-2023");
        endDateSelector.sendKeys("15-09-2030");
        submitTrialSearchButton.click();

        // FindTrialsResult:
        WebElement table = driver.findElement(By.cssSelector("table.table-striped.table-hover"));
        WebElement firstTrial = table.findElement(By.cssSelector("tbody tr:first-child td:first-child"));
        assertEquals("Sporting Event Test", firstTrial.getText());
        firstTrial.click();

        WebElement trialName = driver.findElement(By.tagName("h5"));
        assertEquals("Sporting Event Test", trialName.getText());

        // Verifica la presencia de los elementos con los IDs específicos. Se hace de esta manera para
        // que el orden de los elementos no influya en el resultado de los tests
        List<WebElement> paragraphs = driver.findElements(By.tagName("p"));

        boolean trialDatePresent = false;
        boolean trialDescriptionPresent = false;
        boolean inscriptionPricePresent = false;
        boolean avgScorePresent = false;
        boolean trialPlacePresent = false;
        boolean trialTypeNamePresent = false;
        boolean provinceNamePresent = false;
        boolean maxParticipantsPresent = false;
        boolean numParticipantsPresent = false;

        for (WebElement paragraph : paragraphs) {
            String id = paragraph.getAttribute("id");
            System.out.println(id);
            if ("trialDate".equals(id)) {
                trialDatePresent = true;
            } else if ("trialDescription".equals(id)) {
                trialDescriptionPresent = true;
            } else if ("inscriptionPrice".equals(id)) {
                inscriptionPricePresent = true;
            } else if ("avgScore".equals(id)) {
                avgScorePresent = true;
            } else if ("trialPlace".equals(id)) {
                trialPlacePresent = true;
            } else if ("trialTypeName".equals(id)) {
                trialTypeNamePresent = true;
            }  else if ("provinceName".equals(id)) {
                provinceNamePresent = true;
            } else if ("maxParticipants".equals(id)) {
                maxParticipantsPresent = true;
            } else if ("numParticipants".equals(id)) {
                numParticipantsPresent = true;
            }
        }

        assertTrue(trialDatePresent);
        assertTrue(trialDescriptionPresent);
        assertTrue(inscriptionPricePresent);
        assertTrue(avgScorePresent);
        assertTrue(trialPlacePresent);
        assertTrue(trialTypeNamePresent);
        assertTrue(provinceNamePresent);
        assertTrue(maxParticipantsPresent);
        assertTrue(numParticipantsPresent);

        WebElement registrationForm = driver.findElement(By.id("RegistrationForm"));
        assertNotNull(registrationForm);
    }

    void login(String username, String password) {
        driver.get("http://localhost:5173/");
        WebElement loginLink = driver.findElement(By.id("loginLink"));
        loginLink.click();

        WebElement userNameInput = driver.findElement(By.id("userName"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("SubmitLoginButton"));

        userNameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();

        WebElement userNameLink = driver.findElement(By.id("userNameLink"));
        assertEquals(userNameLink.getText().trim(), username);
    }
}
