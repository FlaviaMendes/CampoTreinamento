import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class TesteDesafioRegrasDeNegocio {
	

	private WebDriver driver;

	@Before
	public void inicializa() {
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file://" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
				
	}
	
	@After
	public void finaliza() {
		
		driver.quit();
		
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
			
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Flavia");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Flavia");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Mendes");

		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		
	}
	
	@Test
	public void deveValidarcomidaVegetariana() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Flavia");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Mendes");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		
		
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
	
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Flavia");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Mendes");
		driver.findElement(By.id("elementosForm:sexo:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Select combo = new Select (driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		
	
	}

}
