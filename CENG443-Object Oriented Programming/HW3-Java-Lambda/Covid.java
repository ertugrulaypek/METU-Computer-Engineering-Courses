import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.nio.file.Paths;
import java.util.Arrays;
import java.nio.file.Files;
import java.io.IOException;
import java.text.DecimalFormat;

public class Covid
{

	// Format of a row of data: ["Country Code", "Location", "Date", "Total Cases", "New Cases", "TotalDeaths", "New Deaths"]
	
	private String getCountryCode(List<String> row) {
		return row.get(0);
	}
	
	private String getLocation(List<String> row) {
		return row.get(1);
	}
	
	private String getDate(List<String> row) {
		return row.get(2);
	}
	
	private int getTotalCases(List<String> row) {
		return Integer.parseInt(row.get(3));
	}
	
	private int getNewCases(List<String> row) {
		return Integer.parseInt(row.get(4));
	}
	
	private int getTotalDeaths(List<String> row) {
		return Integer.parseInt(row.get(5));
	}
	
	private int getNewDeaths(List<String> row) {
		return Integer.parseInt(row.get(6));
	}
	
	private String getAbbrevationOfLocation(String location) {
		List<String> abbrevationRow = this.rows.stream()
				.filter(row -> this.getLocation(row).equals(location))
				.findFirst()
				.get();
		return this.getCountryCode(abbrevationRow);
	}
	
  // You must not change between them.
  private List<List<String>> rows;

  public Covid()
  {
    try
    {
      this.rows = Files
  				.lines(Paths.get("covid19.csv"))
  				.map(row -> Arrays.asList(row.split(",")))
  				.collect(Collectors.toList());
    }
    catch (IOException e)
    {
			e.printStackTrace();
		}
  }
  // You must not change between them.

  public void printOnlyCases(String location, String date)
  {
	  List<String> resultingRow = 
			  this.rows.stream().filter(row -> this.getLocation(row).equals(location))
	  					.filter(row -> this.getDate(row).equals(date))
	  					.findFirst()
	  					.get();
	  					
	  System.out.printf("Result: " + (this.getTotalCases(resultingRow)-this.getTotalDeaths(resultingRow)) );
  }

  public long getDateCount(String location)
  {
    long toReturn = 0;
    toReturn = this.rows.stream()
    		.filter(row -> this.getLocation(row).equals(location))
    		.count();
    return toReturn;
  }

  public int getCaseSum(String date)
  {
    int toReturn = 0;
    toReturn = this.rows.stream()
    		.filter(row -> this.getDate(row).equals(date))
    		.mapToInt(row -> this.getNewCases(row))
    		.sum();
    return toReturn;
  }

  public long getZeroRowsCount(String location)
  {
    long toReturn = 0;
    toReturn = this.rows.stream()
    		.filter(row -> this.getLocation(row).equals(location))
    		.filter(row -> this.getNewCases(row) == 0)
    		.filter(row -> this.getNewDeaths(row) == 0)
    		.filter(row -> this.getTotalDeaths(row) == 0)
    		.filter(row -> this.getTotalCases(row) == 0)
    		.count();
    return toReturn;
  }

  public double getAverageDeath(String location)
  {
    double toReturn = 0;
    Locale.setDefault(Locale.US);
    toReturn = this.rows.stream()
    		.filter(row -> this.getLocation(row).equals(location))
    		.mapToDouble(row -> this.getNewDeaths(row))
    		.average()
    		.getAsDouble();
    toReturn = Double.parseDouble(new DecimalFormat("##.00").format(toReturn));
    return toReturn;
  }

  public String getFirstDeathDayInFirstTenRows(String location)
  {
    String toReturn = null;
    toReturn = this.rows.stream().filter(row -> this.getLocation(row).equals(location))
    		.limit(10)
    		.filter(row -> this.getTotalDeaths(row) != 0 && this.getNewDeaths(row) == this.getTotalDeaths(row) )
    		.findFirst()
    		.map(row -> this.getDate(row))
    		.orElse("Not Found");
    return toReturn;
  }

  public String[] getDateCountOfAllLocations()
  {
    String[] toReturn = null;
    
    toReturn = this.rows.stream()
    		.map(row -> this.getLocation(row))
    		.distinct()
    		.map(location -> this.getAbbrevationOfLocation(location) + ": " + this.getDateCount(location))
    		.toArray(String[]::new);
    
//    toReturn = this.rows.stream()
//    		.collect(Collectors.groupingBy(row -> this.getCountryCode(row)))
//    		.entrySet()
//    		.stream()
//    		.map(entry -> entry.getKey() + ": " + Integer.toString(entry.getValue().size()))
//    		.sorted()
//    		.toArray(String[]::new);
    
    return toReturn;
  }

  public List<String> getLocationsFirstDeathDay()
  {
    List<String> toReturn = null;
    toReturn = this.rows.stream()
    		.filter(row -> this.getNewDeaths(row) != 0 && this.getNewDeaths(row) == this.getTotalDeaths(row))
    		.map(row -> this.getLocation(row) + ": " + this.getDate(row))
    		.collect(Collectors.toList());
    return toReturn;
  }

  public String trimAndGetMax(String location, int trimCount)
  {
    String toReturn = null;
    long countGivenLocation = this.getDateCount(location);
    List<String> resultingRow = this.rows.stream()
    		.filter(row -> this.getLocation(row).equals(location))
    		.sorted((row1, row2) -> this.getNewCases(row1) - this.getNewCases(row2))
    		.skip(trimCount)
    		.limit(countGivenLocation - (2*trimCount) )
    		.max((row1,row2) -> this.getNewDeaths(row1) - this.getNewDeaths(row2))
    		.get();
    toReturn = this.getDate(resultingRow) + ": " + this.getNewDeaths(resultingRow);
    return toReturn;
  }

  public List<List<String>> getOnlyCaseUpDays(String location)
  {
    List<List<String>> toReturn = null;
    toReturn = this.rows.stream()
    		.filter(row -> this.getLocation(row).equals(location))
    		.filter(row -> this.getNewCases(row) != 0)
    		.collect(Collectors.toList());
    System.out.printf("Result: " + toReturn.size());
    return toReturn;
  }

  public static void main(String[] args)
  {
	  Covid covid = new Covid();

	  System.out.println("3.1 ---------------------------");
	  covid.printOnlyCases("Turkey", "2020-03-20");
	  System.out.println();
	  
	  System.out.println("3.2 ---------------------------");
	  System.out.println(covid.getDateCount("Turkey"));
  
	  System.out.println("3.3 ---------------------------");
	  System.out.println(covid.getCaseSum("2020-03-05"));
	  
	  System.out.println("3.4 ---------------------------");
	  System.out.println(covid.getZeroRowsCount("Australia"));
	  
	  System.out.println("3.5 ---------------------------");
	  System.out.println(covid.getAverageDeath("Turkey"));
	  
	  System.out.println("3.6 ---------------------------");
	  System.out.println(covid.getFirstDeathDayInFirstTenRows("Turkey"));
	  
	  System.out.println("3.7 ---------------------------");
	  System.out.println(Arrays.toString(covid.getDateCountOfAllLocations()));
	  
	  System.out.println("3.8 ---------------------------");
	  System.out.println(Arrays.toString(covid.getLocationsFirstDeathDay().toArray()));
	  
	  System.out.println("3.9 ---------------------------");
	  System.out.println(covid.trimAndGetMax("Turkey", 5));
	  
	  System.out.println("3.10 ---------------------------");
	  System.out.println(covid.getOnlyCaseUpDays("Aruba"));
  }
}
