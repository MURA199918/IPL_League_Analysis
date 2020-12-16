import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class IPL_League_Analysis_Test {

    private static final String IPL_RUNS_CSV_FILE_PATH = "C:\\Users\\mural\\IdeaProjects\\IPL_League_Analysis\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";


    @Test
    public void givenIPLRunsData_ShouldReturn_TopBattingAverages_ofCricketers() {
        try{
            IPL_League ipl_league = new IPL_League();
            ipl_league.loadIPLRunsData(IPL_RUNS_CSV_FILE_PATH);
            String sortedIPLRunsData = ipl_league.getTopBattingAverageData();
            RunsFactSheet[] runsCSV = new Gson().fromJson(sortedIPLRunsData, RunsFactSheet[].class);
            System.out.println("Answer found");
            Assert.assertEquals(83.2, runsCSV[0].average,0.0);
        }catch (IPL_League_Exception e){ }
    }
}
