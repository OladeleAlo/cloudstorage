package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class CloudStorageApplicationTests {

	String firstName = "ola";
	String lastName = "dele";
	String username = "ola";
	String password = "ola";

	String filePath = "/cloudstorage/files/test_upload";

	String title = "Test";
	String description = "My new test";

	String newTitle = "Test2";
	String newDescription = "Update test";

	String credentialUrl = "www.google.com";
	String credentialUsername = "testing";
	String credentialPassword = "ola";

	String newCredentialUrl = "www.google.com";
	String newCredentialUsername = "testing2";
	String newCredentialPassword = "ola";

	@LocalServerPort
	private int port;
	private WebDriverWait wait;
	private static WebDriver driver;
	private JavascriptExecutor js;


	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.js = (JavascriptExecutor) driver;
	}

	@AfterAll
	public static void afterAll() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
		driver = null;

	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}

	@Test
	public void sendNote() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		Assertions.assertEquals("Home", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");

		Homepage homePage = new Homepage(driver);
		homePage.sendNote(title, description);
	}

	@Test
	public void updateNote() throws InterruptedException {

		driver.get("http://localhost:" + this.port + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		Assertions.assertEquals("Home", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");

		Homepage homePage = new Homepage(driver);
		homePage.updateNote(newTitle, newDescription);
	}

	@Test
	public void deleteNote() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		Assertions.assertEquals("Home", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");

		Homepage homePage = new Homepage(driver);
		homePage.sendNote(title, description);

		Thread.sleep(1000);
		homePage.deleteNote();
	}

	@Test
	public void sendCredentialTest() {
		driver.get("http://localhost:" + this.port + "/signup");

		SignupPage signuppage = new SignupPage(driver);
		signuppage.signup(firstName, lastName, username, password);

		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		Assertions.assertEquals("Home", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");

		Homepage homePage = new Homepage(driver);

		homePage.sendCredential(credentialUrl, credentialUsername, credentialPassword);
	}

	@Test
	public void updateCredentialTest() {
		driver.get("http://localhost:" + this.port + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		Assertions.assertEquals("Home", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");

		Homepage homePage = new Homepage(driver);

		homePage.sendCredential(credentialUrl, credentialUsername, credentialPassword);

		homePage.updateCredential(newCredentialUrl, newCredentialUsername, newCredentialPassword);
	}

	@Test
	public void deleteCredentialTest() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		Assertions.assertEquals("Sign Up", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/login");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		Assertions.assertEquals("Home", driver.getTitle());

		driver.get("http://localhost:" + this.port + "/home");

		Homepage homePage = new Homepage(driver);

		homePage.sendCredential(credentialUrl, credentialUsername, credentialPassword);
		Thread.sleep(3000);

		homePage.deleteCredential();
	}
}