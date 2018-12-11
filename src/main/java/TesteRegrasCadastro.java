import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro {
	
	private static WebDriver driver;
	private static DSL dsl;
	private static CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String[] esportes;
	@Parameter(value=5)
	public String msg;
	
	
	@BeforeClass
	public static void inicializa() {
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file://" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		dsl = new DSL(driver);
		
		page = new CampoTreinamentoPage(driver);
		
	}
	
	@AfterClass
	public static void finaliza() {
		
		driver.quit();
		
	}
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		String[] semComida = {};
		String[] semEsporte = semComida.clone();
		String[] arrayComidaCaso4 = {"Carne", "Vegetariano"};
		List<String>  listComidaCaso5 = Arrays.asList("Carne");
		String[] arrayEsportesCaso5 = {"Karate", "O que eh esporte?"};
		
		return Arrays.asList(
				new Object[][]{
					{"", "", "", Arrays.asList(), semComida, "Nome eh obrigatorio"},
					{"Flavia", "", "", Arrays.asList(), semComida, "Sobrenome eh obrigatorio"},
					{"Flavia", "Mendes", "", Arrays.asList(), semComida, "Sexo eh obrigatorio"},
					{"Flavia", "Mendes", "Feminino", arrayComidaCaso4, semEsporte, "Tem certeza que voce eh vegetariano?"},
					{"Flavia", "Mendes", "Feminino", listComidaCaso5, arrayEsportesCaso5, "Voce faz esporte ou nao?"},
				}
			);
	}
	
	@Test
	public void deveValidarRegras() {
		
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}
		
		if(sexo.equals("Feminino")){
			page.setSexoFeminino();
		}
		
		if(comidas.contains("Carne")) page.setComidaCarne();
		if(comidas.contains("Pizza")) page.setComidaPizza();
		if(comidas.contains("Frango")) page.setComidaFrango();
		if(comidas.contains("Vegetariano")) page.setComidaVegetariano();

		page.setEsporte(esportes);
		
		page.cadastrar();
		
		System.out.println(msg);

		Assert.assertEquals(msg, dsl.alertaObterTextoEAceito());
		
	}

}
