import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;//instancia de webdrive que virá aravés de um construtor
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	/********* TextField e TextArea *********/
	
	public void escreve(By by, String texto) {
		
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);//o id e texto para ser generico tem q vir por parametro

	}
	
	public void escreve(String id_campo, String texto) {
		
		escreve(By.id(id_campo), texto);
		
	}
	
	public String obterValorCampo(String id_campo) {
		
		return driver.findElement(By.id(id_campo)).getAttribute("value");
		
	}
	
	/********* Radio e Check *********/
	
	public void clicarRadio(String id) {
		
		driver.findElement(By.id(id)).click();
		
	}
	
	public boolean isRadioMarcado(String id) {
		
		return driver.findElement(By.id(id)).isSelected();//confirma se esta selecionado
		
	}
	
	public void clicarCheck(String id) {
		
		driver.findElement(By.id(id)).click();
				
	}
	
	public boolean isCheckMarcado(String id) {
		
		return driver.findElement(By.id(id)).isSelected();
		
	}
	
	/********* Combo *********/
	
	public void selecionarCombo(String id, String valor) {
		
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element); //encontra o combo e tranforma em uma instancia do select
		//combo.selectByIndex(3);
		//combo.selectByValue("superior");
		combo.selectByVisibleText(valor);
		
	}
	
	public void deselecionarCombo(String id, String valor) {
		
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element); //encontra o combo e tranforma em uma instancia do select
		combo.deselectByVisibleText(valor);
		
	}
	
	public String obterValorCombo(String id) {
		
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element); //encontra o combo e tranforma em uma instancia do select
		
		return combo.getFirstSelectedOption().getText();
		
	}
	
	public List<String> obterValoresCombo(String id){
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id) {
		
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
		
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao) {
		
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}
	
	
	/*************** Botao ***************/
	
	public void clicarBotao(String id) {
		
		driver.findElement(By.id(id)).click();
		
	}
	
	public String obterValueElemento(String id) {
		
		return driver.findElement(By.id(id)).getAttribute("value");
		
	}
	
	/*************** Link ***************/
	
	public void clicaLink(String link) {

		driver.findElement(By.linkText(link)).click();
		
	}
	
	/*************** Textos ***************/
	
	public String obterTexto(By by) {
		
		return driver.findElement(by).getText();
		
	}
	
	public String obterTexto(String id) {
		
		return obterTexto(By.id(id));
		
	}
	
	/*************** Alerts ***************/
	
	public String alertaObterTexto() {
		
		Alert alert = driver.switchTo().alert();
		return alert.getText();
		
	}
	
	public String alertaObterTextoEAceito() {
		
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
		
	}
	
	public String alertaObterTextoENegado() {
		
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
		
	}
	
	/*************** Frames e Janelas ***************/
	
	public void entrarFrames(String id) {
		
		driver.switchTo().frame(id);
		
	}
	
	public void sairFrames() {
		
		driver.switchTo().defaultContent();
		
	}
	
	public void trocarJanelas(String id) {
		
		driver.switchTo().window(id);
		
	}
	
	
	

}
