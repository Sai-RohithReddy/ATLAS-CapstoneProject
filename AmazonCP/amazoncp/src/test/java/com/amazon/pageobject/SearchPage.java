package com.amazon.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver lDriver;

	public SearchPage(WebDriver rDriver) {
		lDriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}
	
	@FindBy(id = "twotabsearchtextbox")
	private WebElement searchBox;
	
	@FindBy(id = "nav-search-submit-button")
	private WebElement submitSearch;
	
	@FindBy(xpath = "//span[@class='a-size-medium a-color-base a-text-normal']")
	private WebElement firstProduct;
	
	@FindBy(xpath = "//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']")
	private WebElement firstProductT2;
	
	@FindBy(xpath = "//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']")
	private WebElement nextPage;
	
	@FindBy(xpath = "//a[@class='s-pagination-item s-pagination-previous s-pagination-button s-pagination-separator']")
	private WebElement previousPage;
	
	@FindBy(id = "glow-ingress-block")
	private WebElement deliverTo;
	
	@FindBy(id = "GLUXZipUpdateInput")
	private WebElement deliveryToText;
	
	@FindBy(xpath = "//div[@class='a-column a-span4 a-span-last']/span/span")
	private WebElement submitZip;
	
	@FindBy(xpath = "//div[@class='a-popover-wrapper']/div[2]/span/span/input")
	private WebElement submitDeliveryTo;
	
	public void searchProduct(String product) {
		Actions action = new Actions(lDriver);
		searchBox.clear();
		action.moveToElement(searchBox).click().doubleClick().sendKeys(product).build().perform();
	}
	
	public void clickSearch() {
		submitSearch.click();
	}
	
	public void clickFirstProduct() {
		firstProduct.click();
	}
	
	public void clickFirstProductT2() {
		firstProductT2.click();
	}
	
	public void clickNextPage() {
		nextPage.click();
	}
	
	public void clickPreviousPage() {
		previousPage.click();
	}
	
	public void clickDeliveryTo() {
		deliverTo.click();
	}
	
	public void setZip(String zip) {
		deliveryToText.sendKeys(zip);
	}
	
	public void clickSubmitZip() {
		submitZip.click();
	}
	
	public void submitDeliveryTo() {
		submitDeliveryTo.click();
	}
	
}
