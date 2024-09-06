package stepdefinations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SearchPom;


public class cheapguruFlightSearchstep {

	WebDriver driver;
	WebDriverWait wait;
	SearchPom flightSearchPage;

	@Before
	public void setup() {
		
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.manage().window().maximize();

	}

	@After
	public void teardown() {
		driver.close();
	}
	
	
	@Given("I am on the cheapguru flight search page")
	
	public void i_am_on_the_expedia_flight_search_page() {
		driver.get("https://www.cheapfareguru.com/");
		flightSearchPage = new SearchPom(driver);
		flightSearchPage.clickFlightsTab();
	}

	@When("I search for a round-trip flight from {string} to {string}")
	
	public void i_search_for_a_round_trip_flight_from_to(String departure, String destination) {
		flightSearchPage.enterLeavingFrom(departure);
		flightSearchPage.enterGoingTo(destination);
		flightSearchPage.clickSearchButton();

	}

	@When("I choose the {string} and {string}")
	
	public void i_choose_the_startDate_and_endDate(String startdate, String destdate) {
		flightSearchPage.depdate(startdate);
		flightSearchPage.retdate(destdate);

	}

	@When("I choose {string}")
	
	public void i_choose_cabinclass(String option) {
		flightSearchPage.cabinclass(option);

	}

	@When("I click on the search button")
	
	public void i_click_on_search_button() {
		flightSearchPage.clickSearchButton();

	}

	@Then("I should see all the flights from departure to destination")
	
	public void i_should_see_all_the_flights_from_departure_to_destination() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement results=	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"changeSearch\"]")));
		Assert.assertTrue(results.isDisplayed(), "Search results are not displayed");
	}

	@Then("return top five flights with lowest price for round trip route {string} and {string}")
	
	public void return_top_five_flights_with_lowest_price(String dep, String dest) {
		List<WebElement> flightsList = driver.findElements(By.cssSelector(".col-sm-2.bsFar"));
		List<String> flightPrices = new ArrayList<>();
		// Print the flight prices
		for (WebElement priceElement : flightsList) {
			String priceText = priceElement.getText();
			flightPrices.add(priceText);
		}
		for (int i = 0; i < flightPrices.size(); i++) {
			String priceText = flightPrices.get(i);

			// Replace "USD" with "EUR" in the text
			String updatedText = priceText.replace("Final Total Price", "");

			// Update the list with the new text
			flightPrices.set(i, updatedText);
		}
		Collections.sort(flightPrices);
		// Optional: Print the updated list to verify
		Assert.assertTrue(flightPrices.size() >= 5, "Fewer than 5 flights are available");
		System.out.println("The cheap 5 flights for route: " + dep + " to " + dest);

		for (int i = 0; i < Math.min(5, flightPrices.size()); i++) {
			System.out.println(flightPrices.get(i));
		}

	}

	@Then("return the top five flights with the longest duration for the route {string} to {string}")
	
	public void return_the_top_five_with_the_longest_duration_for_the_route(String dep, String dest) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Find all the duration elements on the page
		List<WebElement> durationElements = driver.findElements(By.xpath("//span[contains(text(), 'Total Time')]"));

		// Initialize two lists to store the alternating values
		List<String> depTime = new ArrayList<>();
		List<String> retTime = new ArrayList<>();

		// Loop through the duration elements and alternate between the two lists
		for (int i = 0; i < durationElements.size(); i++) {
		    String timeText = durationElements.get(i).getText().replace("Total Time:", "").trim();

		    // Alternate between adding to list1 and list2
		    if (i % 2 == 0) {
		    	depTime.add(timeText);  // Add to list1 if index is even
		    } else {
		    	retTime.add(timeText);  // Add to list2 if index is odd
		    }
		}

		// Sort both lists in ascending order
//		Collections.sort(depTime);
//		Collections.sort(retTime);

		// Initialize a new list to store the sum of corresponding values
		List<String> summedList = new ArrayList<>();

		// Add the corresponding values from both lists
		int minSize = Math.min(depTime.size(), retTime.size());
		for (int i = 0; i < minSize; i++) {
		    String time1 = depTime.get(i);
		    String time2 = retTime.get(i);

		    // Convert time1 and time2 to durations and add them
		    Duration duration1 = parseDuration(time1);
		    Duration duration2 = parseDuration(time2);
		    Duration totalDuration = duration1.plus(duration2);

		    // Add the sum of both durations to the summedList
		    summedList.add(formatDuration(totalDuration));
		}

		// Sort the summedList in ascending order if needed
		Collections.sort(summedList, Collections.reverseOrder());
		Assert.assertTrue(summedList.size() >= 5, "Fewer than 5 durations are available");
		// Print the top 5 summed durations (if available)
		System.out.println("Top 5 longest durations: from: " + dep + " to " + dest);
		for (int i = 0; i < Math.min(5, summedList.size()); i++) {
		    System.out.println(summedList.get(i));
		}
	}

		// Helper method to parse the duration string into a Duration object
		private Duration parseDuration(String time) {
		    String[] parts = time.split(" ");
		    int hours = Integer.parseInt(parts[0].replace("hr", ""));
		    int minutes = Integer.parseInt(parts[1].replace("min", ""));
		    return Duration.ofHours(hours).plusMinutes(minutes);
		}

		// Helper method to format the Duration object back to a string
		private String formatDuration(Duration duration) {
		    long hours = duration.toHours();
		    long minutes = duration.minusHours(hours).toMinutes();
		    return hours + "hr" + minutes + "min";
		}

	}

