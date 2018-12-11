import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestAlert {
	
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
	public void deveInteragirComAlertSimples() { //testa o quadrado q aparece depois de clicar no botao
		
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceito();
		Assert.assertEquals("Alert Simples", texto);
	
		dsl.escreve("elementosForm:nome", texto);
		
	}
	
	
	@Test
	public void deveInteragirComAlertConfirm() { //testa o quadrado q aparece depois de clicar no botao
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceito());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceito());
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENegado());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENegado());

	}
	

	@Test
	public void deveInteragirComAlertPrompt() { //testa o quadrado q aparece depois de clicar no botao
	
	dsl.clicarBotao("prompt");
	Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
	dsl.obterTexto("12");
	Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceito());
	Assert.assertEquals(":D", dsl.alertaObterTextoEAceito());
	
	
	}
	
	
}
