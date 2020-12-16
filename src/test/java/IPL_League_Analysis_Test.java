import com.google.gson.Gson;
import com.jarfile.CSVBuilderException;
import org.junit.Assert;
import org.junit.Test;

public class IPL_League_Analysis_Test {

    private static final String IPL_RUNS_CSV_FILE_PATH = "C:\\Users\\mural\\IdeaProjects\\IPL_League_Analysis\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String IPL_WICKETS_CSV_FILE_PATH = "C:\\Users\\mural\\IdeaProjects\\IPL_League_Analysis\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";


    @Test
    public void givenIPLRunsData_ShouldReturn_TopBattingAverages_ofCricketers() {
        try{
            IPL_League ipl_league = new IPL_League();
            ipl_league.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH);
            String sortedIPLRunsData = ipl_league.getTopBattingAverageData();
            RunsFactSheet[] runsCSV = new Gson().fromJson(sortedIPLRunsData, RunsFactSheet[].class);
            System.out.println("Answer found");
            Assert.assertEquals(83.2, runsCSV[0].average,0.0);
        }catch (IPL_League_Exception | CSVBuilderException e){ }
    }

    @Test
    public void givenIPLRunsData_ShouldReturn_TopStrikeRates_ofCricketers() {
        try{
            IPL_League ipl_league = new IPL_League();
            ipl_league.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH);
            String sortedIPLRunsData = ipl_league.getTopStrikeRateData();
            RunsFactSheet[] runsCSV = new Gson().fromJson(sortedIPLRunsData, RunsFactSheet[].class);
            System.out.println("Answer found");
            Assert.assertEquals(204.81, runsCSV[0].strikeRate,0.0);
        }catch (IPL_League_Exception | CSVBuilderException e){ }
    }

    @Test
    public void givenIPLRunsData_ShouldReturn_TopSixes_ofCricketers() {
        try{
            IPL_League ipl_league = new IPL_League();
            ipl_league.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH);
            String sortedIPLRunsData = ipl_league.getTopSixes();
            RunsFactSheet[] runsCSV = new Gson().fromJson(sortedIPLRunsData, RunsFactSheet[].class);
            System.out.println("Answer found");
            Assert.assertEquals(52, runsCSV[0].sixes);
        }catch (IPL_League_Exception | CSVBuilderException e){ }
    }

    @Test
    public void givenIPLRunsData_ShouldReturn_TopFours_ofCricketers() {
        try{
            IPL_League ipl_league = new IPL_League();
            ipl_league.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH);
            String sortedIPLRunsData = ipl_league.getTopFours();
            RunsFactSheet[] runsCSV = new Gson().fromJson(sortedIPLRunsData, RunsFactSheet[].class);
            System.out.println("Answer found");
            Assert.assertEquals(64, runsCSV[0].fours);
        }catch (IPL_League_Exception | CSVBuilderException e){ }
    }

    @Test
    public void givenIPLRunsData_ShouldReturn_CricketerWith_BestStrikeRate() {
        try{
            IPL_League ipl_league = new IPL_League();
            ipl_league.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH);
            String sortedIPLRunsData = ipl_league.getTopStrikeRateData();
            RunsFactSheet[] runsCSV = new Gson().fromJson(sortedIPLRunsData, RunsFactSheet[].class);
            System.out.println("Answer found");
            Assert.assertEquals("Andre Russell", runsCSV[0].name);
        }catch (IPL_League_Exception | CSVBuilderException e){ }
    }

    @Test
    public void givenIPLRunsData_ShouldReturn_CricketerWith_BestAverage() {
        try{
            IPL_League ipl_league = new IPL_League();
            ipl_league.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH);
            String sortedIPLRunsData = ipl_league.getTopBattingAverageData();
            RunsFactSheet[] runsCSV = new Gson().fromJson(sortedIPLRunsData, RunsFactSheet[].class);
            System.out.println("Answer found");
            Assert.assertEquals("MS Dhoni", runsCSV[0].name);
        }catch (IPL_League_Exception | CSVBuilderException e){ }
    }

    @Test
    public void givenIPLRunsData_ShouldReturn_CricketersWith_MaximumRuns() {
        try{
            IPL_League ipl_league = new IPL_League();
            ipl_league.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH);
            String sortedIPLRunsData = ipl_league.getMaximumRuns();
            RunsFactSheet[] runsCSV = new Gson().fromJson(sortedIPLRunsData, RunsFactSheet[].class);
            System.out.println("Answer found");
            Assert.assertEquals("David Warner", runsCSV[0].name);
        }catch (IPL_League_Exception | CSVBuilderException e){ }
    }

    @Test
    public void givenIPLRunsData_ShouldReturn_TopBowlingAverages_ofCricketers() {
        try{
            IPL_League ipl_league = new IPL_League();
            ipl_league.loadIPLWicketsData(IPL_WICKETS_CSV_FILE_PATH);
            String sortedIPLWicketsData = ipl_league.getTopBowlingAverageData();
            WicketsFactSheet[] wicketsCSV = new Gson().fromJson(sortedIPLWicketsData, WicketsFactSheet[].class);
            System.out.println("Answer found");
            Assert.assertEquals(166, wicketsCSV[0].average,0.0);
        }catch (IPL_League_Exception e){ }
    }

    @Test
    public void givenIPLRunsData_ShouldReturn_TopBowlingStrikeRates_ofCricketers() {
        try{
            IPL_League ipl_league = new IPL_League();
            ipl_league.loadIPLWicketsData(IPL_WICKETS_CSV_FILE_PATH);
            String sortedIPLWicketsData = ipl_league.getTopBowlingStrikeRateData();
            WicketsFactSheet[] wicketsCSV = new Gson().fromJson(sortedIPLWicketsData, WicketsFactSheet[].class);
            System.out.println("Answer found");
            Assert.assertEquals(120, wicketsCSV[0].strikeRate,0.0);
        }catch (IPL_League_Exception e){ }
    }

}
