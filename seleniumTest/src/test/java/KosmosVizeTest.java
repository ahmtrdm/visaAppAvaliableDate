import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KosmosVizeTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // ChromeDriver'ı tanımlayın (doğru driver sürümünü indirip eklediğinizden emin olun)
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        // Tarayıcıya genel ayarları uygulayın
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testAppointmentFormPage() throws InterruptedException {
        // Hedef URL'ye gidin
        driver.get("https://basvuru.kosmosvize.com.tr/appointmentform");

        // Sayfa başlığını kontrol edin
        String expectedTitle = "Kosmos Vize - Appointment Form";
        String actualTitle = driver.getTitle();
//        assertEquals("Sayfa başlığı beklenenden farklı!", expectedTitle, actualTitle);

        // Form elemanlarının sayfada var olduğunu kontrol edin

        WebElement cityField = driver.findElement(By.id("cities"));
        cityField.sendKeys("ISTANBUL");

        Thread.sleep(1000);

        WebElement applicationCenter = driver.findElement(By.xpath("//*[@id=\"buttonContainer\"]/div[1]/div/div"));
        applicationCenter.click();

        Thread.sleep(1000);

        WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/div/div/div[2]/div/div/div[3]/div[2]/span/button"));
        nextButton.click();

        Thread.sleep(1000);

        //-------------------------------------------------Form2---------------------------

        //Select applicationType = new Select(driver.findElement(By.linkText("Başvuru Tipi")));
        //applicationType.selectByValue("1");

        List<WebElement> webElementList = driver.findElements(By.className("form-control"));
        System.out.println("element list :" + webElementList);
        Select applicationType = new Select(webElementList.get(0));
        applicationType.selectByValue("1");

        Thread.sleep(1000);

        //WebElement identityNumber = driver.findElement(By.className(""));
        WebElement identityNumber = webElementList.get(2);
        identityNumber.sendKeys("61375340986");
        identityNumber.sendKeys(Keys.TAB);

        Thread.sleep(1000);

        //Select appplicationShape = new Select(driver.findElement(By.xpath("//*[@id=\"__BVID__94\"]/div/span/select")));
        //webElementList = driver.findElements(By.className("form-control"));
        Select appplicationShape = new Select(webElementList.get(3));
        appplicationShape.selectByValue("16");

        Thread.sleep(1000);

        WebElement captcha = driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]"));
        captcha.click();

        Thread.sleep(1000);

        WebElement submitButton2 = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/div/div/div[2]/div/div/div[3]/div[2]/span/button"));
        submitButton2.click();
        Thread.sleep(1000);


        // Elemanların görünür olup olmadığını doğrula
//        assertEquals("Name field görünür değil!", true, nameField.isDisplayed());
//        assertEquals("Email field görünür değil!", true, emailField.isDisplayed());
//        assertEquals("Submit button görünür değil!", true, submitButton.isDisplayed());
    }

    @After
    public void tearDown() {
        // Tarayıcıyı kapatın
        if (driver != null) {
            driver.quit();
        }
    }
}
