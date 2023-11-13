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
	WebElement searchBox;
	
	@FindBy(id = "nav-search-submit-button")
	WebElement submitSearch;
	
	@FindBy(xpath = "//span[@class='a-size-medium a-color-base a-text-normal']")
	WebElement firstProduct;
	
	@FindBy(xpath = "//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']")
	WebElement nextPage;
	
	@FindBy(xpath = "//a[@class='s-pagination-item s-pagination-previous s-pagination-button s-pagination-separator']")
	WebElement previousPage;
	
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
	
	public void clickNextPage() {
		nextPage.click();
	}
	
	public void clickPreviousPage() {
		previousPage.click();
	}
}
