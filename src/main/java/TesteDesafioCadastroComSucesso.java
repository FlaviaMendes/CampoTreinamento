import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TesteDesafioCadastroComSucesso {
	
	
	
	private WebDriver driver;
	private CampoTreinamentoPage page; // instancia da page campotreinamentopage

	@Before
	public void inicializa() {
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file://" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		page = new CampoTreinamentoPage(driver);
		
	}
	
	@After
	public void finaliza() {
		
		//driver.quit();
		
	}
	
	@Test
	public void cadatroComSucesso() {
				
		page.setNome("Flavia");
		page.setSobrenome("Mendes");
		
		page.setSexoFeminino();
		page.setComidaPizza();
		
		page.setEscolaridade("Superior");
		page.setEsporte("Natacao");
		
		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Flavia"));
		Assert.assertEquals("Sobrenome: Mendes", page.obterSobrenomeCadastro() );
		Assert.assertEquals("Sexo: Feminino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Natacao", page.obterEsporteCadastro());
		
	}	
	

	
}
	
