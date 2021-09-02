package tests;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import suporte.Web;

public class LoginTest {
    private WebDriver navegador;

    @Before
    public void setUp() {
        navegador = Web.createChrome();

        //Digitar no campo de id "user-name" o texto "standar_user"
        navegador.findElement(By.id("user-name")).sendKeys("standard_user");

        //Digitar senha no campo de Password
        navegador.findElement(By.id("password")).sendKeys("secret_sauce");

        //Clicar no botão Login
        navegador.findElement(By.id("login-button")).click();
    }

    @Test
    public void testaLogin() {

        String validaUrl = navegador.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html",validaUrl);
    }

    @Test
    public void testaCompra(){
        //Clicar no botão "ADD TO CART" pelo xpath //button[@data-test="add-to-cart-sauce-labs-backpack"]
        navegador.findElement(By.xpath("//button[@data-test=\"add-to-cart-sauce-labs-backpack\"]")).click();

        //Clicar no link q possui a class "shopping_cart_link"
        navegador.findElement(By.className("shopping_cart_link")).click();

        //Validar se o produto foi inserido no carrinho
        WebElement textoClass = navegador.findElement(By.className("inventory_item_name"));
        String textoClassCapturado = textoClass.getText();
        assertEquals("Sauce Labs Backpack", textoClassCapturado);

        //Clicar no botão "CHECKOUT"
        navegador.findElement(By.id("checkout")).click();
        //Preencher campo de First Name no formulário de Checkout
        navegador.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("Evander Cristiano");
        //Preencher campo de Last Name no formulário de Checkout
        navegador.findElement(By.id("last-name")).sendKeys("Souza");
        //Preencher campo de Zip/Postal Code no formulário de Checkout
        navegador.findElement(By.id("postal-code")).sendKeys("06515030");
        //Clicar no botão "CONTINUE"
        navegador.findElement(By.id("continue")).click();
        //Validar que o checkout foi completo

    }

    @After
    public void tearDown() {
       // navegador.quit();
    }
}
