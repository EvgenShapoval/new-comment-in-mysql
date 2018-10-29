package tests;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import data.JDBCBase;
import pages.CommentPage;
import pages.HomePage;

public class MySQLTest {

	private String commentAuthor = "Олег";
	private String expectedEmail = "oleg@gmail.com";
	private String textAreaComment = "Пример комментария";
	
	protected static WebDriver driver;
	private JDBCBase dBMySQL;
	
	@BeforeClass
	public static void beforeClassTest() {
		
//		System.setProperty("webdriver.chrome.driver", "D:/soft/java/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	
	@Test
	public void newNameToBDTest() throws SQLException, ClassNotFoundException {

		driver.get("https://agrobuilding.com/");
		
		HomePage homePage = new HomePage(driver);
			homePage.clickNews1Link();
		
// 		Switch focus of WebDriver to the next found window handle
//		https://stackoverflow.com/questions/19112209/how-to-handle-the-new-window-in-selenium-webdriver-using-java
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle);
		}
		
		CommentPage commentPage = new CommentPage(driver);
		
//			Fill in the comment form fields
			commentPage.inputTextAreaCommentForm(textAreaComment);
			commentPage.inputAuthorCommentForm(commentAuthor);
			commentPage.inputEmailCommentForm(expectedEmail);
			commentPage.clickSubmitCommentForm();
		
//		Connect MySQL DB
		dBMySQL = new JDBCBase();
			System.out.println("--- Connected to MySQL --------");
		
//		Get email from database by author
		String actualEmail = dBMySQL
				.selectAuthorEmail(commentAuthor);
		
		
//		Check for a new entry in the database
		Assert.assertTrue("Email comment author Not equals expected email", 
				actualEmail.equals(expectedEmail));
	}

	@After
	public void cleanUpNewNameToBD() throws SQLException, ClassNotFoundException{
	
//		Delete the record in the database about the new comment
		dBMySQL.deleteRow(commentAuthor);
	}

	@AfterClass
	public static void afterTest() {
	
		driver.manage().deleteAllCookies();
		driver.quit();
		System.out.println("--- Webdriver quit --------");
	}
}