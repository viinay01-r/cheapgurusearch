package stepdefinations;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "flightSearchData")
    public Object[][] flightSearchData() {
        return new Object[][]{
            {"New York", "Los Angeles"},
            {"San Francisco", "Chicago"},
            {"Miami", "Dallas"}
        };
    }

    @DataProvider(name = "flightDates")
    public Object[][] flightDates() {
        return new Object[][]{
            {"2024-09-01", "2024-09-07"},
            {"2024-10-01", "2024-10-07"},
            {"2024-11-01", "2024-11-07"}
        };
    }

    @DataProvider(name = "cabinClassData")
    public Object[][] cabinClassData() {
        return new Object[][]{
            {"Economy"},
            {"Business"},
            {"First Class"}
        };
    }
}
