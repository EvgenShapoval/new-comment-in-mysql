package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}

// 	News block - link to the first news
	@FindBy(css="#rpwwt-recent-posts-widget-with-thumbnails-2 > ul > li:nth-child(1) > a")
	private WebElement news1Link;
	
    public void clickNews1Link() {
    	news1Link.click();
    }
}
