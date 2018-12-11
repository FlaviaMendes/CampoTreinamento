import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCampoTreinamento {
	
	
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
	public void testeTestField() {
		
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	
	}
	
	@Test
	public void testTextFieldDuplo() {
		
		dsl.escreve("elementosForm:nome", "Flavia");
		Assert.assertEquals("Flavia", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escreve("elementosForm:nome", "Mendes");
		Assert.assertEquals("Mendes", dsl.obterValorCampo("elementosForm:nome"));

		
	}
	
	@Test
	public void deveInteragirComTextArea() {
		
		dsl.escreve("elementosForm:sugestoes", "teste");
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));
		
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));

	}

	
	@Test
	public void deveInteragirComCheckbox() {
		
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));//confirma se esta selecionado

	}
	
	@Test
	public void deveInteragirComComboBox() {
		
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau incompleto");
		Assert.assertEquals("2o grau incompleto", dsl.obterValorCombo("elementosForm:escolaridade"));
		
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
	
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
		
	}
	
	@Test
	public void deveInteragirComBotoes() {
	
		dsl.clicarBotao("buttonSimple");
		
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	
	}	
	
	@Test
	//@Ignore //informa ao junit para não executar o teste
	public void deveInteragirComLinks() {
		
		dsl.clicaLink("Voltar");
		
		//Assert.fail(); //vai falar esse teste
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento")); // esta buscando na tela inteira
		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3"))); //gettext transforma em uma string
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar"))); 

	}	
	
	
	
}