package travelSiteTest;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TravelSiteTest {
	
	WebDriver driver;
	
	@BeforeMethod
	void setup() {
		WebDriverManager.chromedriver().setup();
		this.driver= new ChromeDriver();
		this.driver.get("https://www.gozayaan.com");
		this.driver.manage().window().maximize();
		
	}
	
	@AfterMethod
	void end() throws InterruptedException {
		Thread.sleep(10000);
		driver.quit();
	}

	@Test
	void searchFlight() throws InterruptedException {
		
		Actions action = new Actions(driver);
		
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Enter Departure Location: ");
		String departureLocation = scn.nextLine();
		
		System.out.println("Enter Destination Location: ");
		String destinationLocation = scn.nextLine();
		
		System.out.println("Enter date of the flight (date should be in the current month): ");
		int dateOfFlight = scn.nextInt();
		String dateOfFlightString = Integer.toString(dateOfFlight+2);
		
		scn.close();
		
		WebElement from = this.driver.findElement(By.xpath("//*[@id=\"searchbar\"]/div[3]/div[2]/div[1]/div[1]"));
		action.moveToElement(from).click().moveToElement(from).sendKeys(departureLocation).perform();
		Thread.sleep(2000);
		WebElement fromAirport = this.driver.findElement(By.xpath("//*[@id=\"searchbar\"]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div[1]/div[2]"));
		action.click(fromAirport).perform();
		
		action.click();
		
		WebElement to = this.driver.findElement(By.xpath("//*[@id=\"searchbar\"]/div[3]/div[2]/div[2]/div[1]"));
		action.moveToElement(to).click().moveToElement(to).sendKeys(destinationLocation).perform();
		Thread.sleep(2000);
		WebElement toAirport = this.driver.findElement(By.xpath("//*[@id=\"searchbar\"]/div[3]/div[2]/div[2]/div[2]/div[2]/div/div[1]/div[1]"));
		action.click(toAirport).perform();
		
		action.click();
		
		WebElement date = this.driver.findElement(By.xpath("//*[@id=\"searchbar\"]/div[3]/div[2]/div[3]/div[1]"));
		action.click(date).perform();
		
		WebElement flightDate = this.driver.findElement(By.xpath("//*[@id=\"searchbar\"]/div[3]/div[2]/div[3]/div[2]/div[2]/div[1]/div[3]/span["+dateOfFlightString+"]"));
		action.click(flightDate).perform();
		
		Thread.sleep(2000);
		
		WebElement searchFlight = this.driver.findElement(By.xpath("//*[@id=\"searchbar\"]/div[4]/button"));
		searchFlight.click();
	}
}
