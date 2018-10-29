package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommentPage extends BasePage{

	public CommentPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="comment")
	private WebElement textArea;
		
	@FindBy(id="author")
	private WebElement author;
	
	@FindBy(id="email")
	private WebElement email;
	
	@FindBy(id="submit")
	private WebElement commentSubmit;
	
	
    public void inputTextAreaCommentForm(String text) {
    	textArea.clear();
    	textArea.sendKeys(text);
    }
    
    public void inputAuthorCommentForm(String name) {
    	author.clear();
    	author.sendKeys(name);
    }
    
    public void inputEmailCommentForm(String mail) {
    	email.clear();
    	email.sendKeys(mail);
    }
	
    public void clickSubmitCommentForm() {
    	commentSubmit.click();
    }
}
