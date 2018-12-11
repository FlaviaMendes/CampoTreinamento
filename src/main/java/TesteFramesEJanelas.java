import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {


	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file://" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	
		dsl = new DSL(driver);
		 
	}
	
	@After
	public void finaliza() {
		
		driver.quit();
		
	}
	
	@Test
	public void deveInteragirComFrames() {
		
		dsl.entrarFrames("frame1"); //id do frame
		dsl.clicarBotao("frameButton");//id do button
		
		String msg = dsl.alertaObterTextoEAceito();
		Assert.assertEquals("Frame OK!", msg);
		
		dsl.sairFrames();
		dsl.escreve("elementosForm:nome", msg);
	
	}
	
	@Test
	public void deveInteragirComJanelas() {
		
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanelas("Popup"); //popup cpm título
		
		dsl.escreve(By.tagName("textarea"),"Deu certo?");
		driver.close();
		
		dsl.trocarJanelas("");
		dsl.escreve(By.tagName("textarea"), "e agora?");
		
	}
	
	@Test
	public void deveInteragirComJanelasSemTítulo() {
		
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		
		dsl.trocarJanelas((String) driver.getWindowHandles().toArray()[1]);
		dsl.escreve(By.tagName("textarea"),"Deu certo?");
		dsl.trocarJanelas((String) driver.getWindowHandles().toArray()[0]);
		dsl.escreve(By.tagName("textarea"), "E agora?");
	
	}
	
}
