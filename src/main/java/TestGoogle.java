import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestGoogle {

	
	@Test
	public void teste() {
	
		//WebDriver driver = new ChromeDriver();
		//WebDriver driver = new InternetExploreDriver();
			
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\FlaviaLindona\\Desktop\\Tests\\geckodriver-v0.23.0-win64");
		WebDriver driver = new FirefoxDriver();
		
		//driver.manage().window().setPosition(new Point(100,100));
		//driver.manage().window().setSize(new Dimension(1200,765));
		driver.manage().window().maximize();

		driver.get("https://www.google.com/");
		
		Assert.assertEquals("Google", driver.getTitle());
		
		driver.quit();//fecha todas as abas e acaba com todas as instancias do driver
	}
	
	
}
