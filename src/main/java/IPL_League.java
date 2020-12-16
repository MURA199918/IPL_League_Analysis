import com.google.gson.Gson;
import com.jarfile.CSVBuilderException;
import com.jarfile.CSVBuilderFactory;
import com.jarfile.ICSVBuilder;

import java.util.List;


import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class IPL_League {
    List<RunsFactSheet> runsIPLList = null;
    List<WicketsFactSheet> wicketsIPLList = null;

    public int loadIPLRunsData(String csvFilePath) throws IPL_League_Exception, CSVBuilderException {
        try( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            runsIPLList = csvBuilder.getCSVFileList(reader, RunsFactSheet.class);
            return runsIPLList.size();
        } catch (IOException e) {
            throw new IPL_League_Exception(e.getMessage(),
                    IPL_League_Exception.ExceptionType.IPL_RUNS_FILE_PROBLEM);
        }catch (RuntimeException e){
            throw new IPL_League_Exception(e.getMessage(),
                    IPL_League_Exception.ExceptionType.RUN_TIME_EXCEPTION);
        }catch (CSVBuilderException e){
            throw new IPL_League_Exception(e.getMessage(),e.type.name());
        }
    }

    public int loadIPLWicketsData(String csvFilePath) throws IPL_League_Exception{
        try( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            wicketsIPLList = csvBuilder.getCSVFileList(reader, WicketsFactSheet.class);
            return wicketsIPLList.size();
        } catch (IOException e) {
            throw new IPL_League_Exception(e.getMessage(),
                    IPL_League_Exception.ExceptionType.IPL_WICKETS_FILE_PROBLEM);
        }catch (RuntimeException e){
            throw new IPL_League_Exception(e.getMessage(),
                    IPL_League_Exception.ExceptionType.RUN_TIME_EXCEPTION);
        }catch (CSVBuilderException e){
            throw new IPL_League_Exception(e.getMessage(),e.type.name());
        }
    }

    private <E> int getCount(Iterator<E> iterator){
        Iterable<E> csvIterable = () -> iterator;
        int numofEntries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
        return numofEntries;
    }

    public String getTopBattingAverageData() throws IPL_League_Exception {
        if(runsIPLList == null || runsIPLList.size() == 0){
            throw new IPL_League_Exception("No Runs Data", IPL_League_Exception.ExceptionType.NO_IPL_RUNS_DATA);
        }
        Comparator<RunsFactSheet> runsComparator = Comparator.comparing(runs->runs.average);
        this.sortIPLRuns(runsComparator);
        String sortedBattingAverageRunsDataJson = new Gson().toJson(runsIPLList);
        return sortedBattingAverageRunsDataJson;
    }

    public String getTopStrikeRateData() throws IPL_League_Exception {
        if(runsIPLList == null || runsIPLList.size() == 0){
            throw new IPL_League_Exception("No Runs Data", IPL_League_Exception.ExceptionType.NO_IPL_RUNS_DATA);
        }
        Comparator<RunsFactSheet> runsComparator = Comparator.comparing(runs->runs.strikeRate);
        this.sortIPLRuns(runsComparator);
        String sortedStrikeRateRunsDataJson = new Gson().toJson(runsIPLList);
        return sortedStrikeRateRunsDataJson;
    }

    public String getTopSixes() throws IPL_League_Exception {
        if(runsIPLList == null || runsIPLList.size() == 0){
            throw new IPL_League_Exception("No Runs Data", IPL_League_Exception.ExceptionType.NO_IPL_RUNS_DATA);
        }
        Comparator<RunsFactSheet> runsComparator = Comparator.comparing(runs->runs.sixes);
        this.sortIPLRuns(runsComparator);
        String sortedSixesDataJson = new Gson().toJson(runsIPLList);
        return sortedSixesDataJson;
    }

    public String getTopFours() throws IPL_League_Exception {
        if(runsIPLList == null || runsIPLList.size() == 0){
            throw new IPL_League_Exception("No Runs Data", IPL_League_Exception.ExceptionType.NO_IPL_RUNS_DATA);
        }
        Comparator<RunsFactSheet> runsComparator = Comparator.comparing(runs->runs.fours);
        this.sortIPLRuns(runsComparator);
        String sortedFoursDataJson = new Gson().toJson(runsIPLList);
        return sortedFoursDataJson;
    }

    public String getMaximumRuns() throws IPL_League_Exception {
        if(runsIPLList == null || runsIPLList.size() == 0){
            throw new IPL_League_Exception("No Runs Data", IPL_League_Exception.ExceptionType.NO_IPL_RUNS_DATA);
        }
        Comparator<RunsFactSheet> runsComparator = Comparator.comparing(runs->runs.runs);
        this.sortIPLRuns(runsComparator);
        String sortedMaximumRunsDataJson = new Gson().toJson(runsIPLList);
        return sortedMaximumRunsDataJson;
    }

    public String getTopBowlingAverageData() throws IPL_League_Exception {
        if(wicketsIPLList == null || wicketsIPLList.size() == 0){
            throw new IPL_League_Exception("No Wickets Data", IPL_League_Exception.ExceptionType.NO_IPL_WICKETS_DATA);
        }
        Comparator<WicketsFactSheet> runsComparator = Comparator.comparing(runs->runs.average);
        this.sortIPLWickets(runsComparator);
        String sortedBowlingAverageDataJson = new Gson().toJson(wicketsIPLList);
        return sortedBowlingAverageDataJson;
    }

    public String getTopBowlingStrikeRateData() throws IPL_League_Exception {
        if(wicketsIPLList == null || wicketsIPLList.size() == 0){
            throw new IPL_League_Exception("No Wickets Data", IPL_League_Exception.ExceptionType.NO_IPL_WICKETS_DATA);
        }
        Comparator<WicketsFactSheet> runsComparator = Comparator.comparing(runs->runs.strikeRate);
        this.sortIPLWickets(runsComparator);
        String sortedStrikeRateWicketsDataJson = new Gson().toJson(wicketsIPLList);
        return sortedStrikeRateWicketsDataJson;
    }

    public String getTopBowlingEconomyData() throws IPL_League_Exception {
        if(wicketsIPLList == null || wicketsIPLList.size() == 0){
            throw new IPL_League_Exception("No Wickets Data", IPL_League_Exception.ExceptionType.NO_IPL_WICKETS_DATA);
        }
        Comparator<WicketsFactSheet> runsComparator = Comparator.comparing(runs->runs.economy);
        this.sortIPLWickets(runsComparator);
        String sortedBowlingEconomyDataJson = new Gson().toJson(wicketsIPLList);
        return sortedBowlingEconomyDataJson;
    }

    public String getTopBowlingStrikeRateData_WithFoursAndSixes() throws IPL_League_Exception {
        if(wicketsIPLList == null || wicketsIPLList.size() == 0){
            throw new IPL_League_Exception("No Wickets Data", IPL_League_Exception.ExceptionType.NO_IPL_WICKETS_DATA);
        }
        Comparator<WicketsFactSheet> runsComparator = Comparator.comparing(runs->runs.fourWicketHaul);
        this.sortIPLWicketsDES(runsComparator);
        String sortedStrikeRateWicketsDataJson = new Gson().toJson(wicketsIPLList);
        return sortedStrikeRateWicketsDataJson;
    }

    public String getMaximumWicketsData_OfBowling() throws IPL_League_Exception {
        if(wicketsIPLList == null || wicketsIPLList.size() == 0){
            throw new IPL_League_Exception("No Wickets Data", IPL_League_Exception.ExceptionType.NO_IPL_WICKETS_DATA);
        }
        Comparator<WicketsFactSheet> runsComparator = Comparator.comparing(runs->runs.wickets);
        this.sortIPLWicketsDES(runsComparator);
        String sortedMaximumWicketsDataJson = new Gson().toJson(wicketsIPLList);
        return sortedMaximumWicketsDataJson;
    }

    public String getTopBattingAndBowlingAverages_ofCricketers() throws IPL_League_Exception {
        if(runsIPLList == null || runsIPLList.size() == 0){
            throw new IPL_League_Exception("No Runs Data", IPL_League_Exception.ExceptionType.NO_IPL_RUNS_DATA);
        }
        else if(wicketsIPLList == null || wicketsIPLList.size() == 0){
            throw new IPL_League_Exception("No Wickets Data", IPL_League_Exception.ExceptionType.NO_IPL_WICKETS_DATA);
        }
        Comparator<RunsFactSheet> runsComparator = Comparator.comparing(runs->runs.average);
        List<RunsFactSheet> list = this.sortIPLRunsandReturn(runsComparator);
        return null;
    }

    private List sortIPLRunsandReturn(Comparator<RunsFactSheet> runsComparator) {
        for(int i=0;i<runsIPLList.size()-1;i++){
            for(int j=0; j<runsIPLList.size()-1;j++){
                RunsFactSheet runsData1 = runsIPLList.get(j);
                RunsFactSheet runsData2 = runsIPLList.get(j+1);
                if(runsComparator.compare(runsData1,runsData2)<0){
                    runsIPLList.set(j, runsData2);
                    runsIPLList.set(j+1, runsData1);
                }
            }
        }
        return runsIPLList;
    }

    private void sortIPLRuns(Comparator<RunsFactSheet> runsComparator) {
        for(int i=0;i<runsIPLList.size()-1;i++){
            for(int j=0; j<runsIPLList.size()-1;j++){
                RunsFactSheet runsData1 = runsIPLList.get(j);
                RunsFactSheet runsData2 = runsIPLList.get(j+1);
                if(runsComparator.compare(runsData1,runsData2)<0){
                    runsIPLList.set(j, runsData2);
                    runsIPLList.set(j+1, runsData1);
                }
            }
        }
    }

    private void sortIPLWickets(Comparator<WicketsFactSheet> runsComparator) {
        for(int i=0;i<wicketsIPLList.size()-1;i++){
            for(int j=0; j<wicketsIPLList.size()-1;j++){
                WicketsFactSheet wicketsData1 = wicketsIPLList.get(j);
                WicketsFactSheet wicketsData2 = wicketsIPLList.get(j+1);
                if(runsComparator.compare(wicketsData1,wicketsData2)>0){
                    wicketsIPLList.set(j, wicketsData2);
                    wicketsIPLList.set(j+1, wicketsData1);
                }
            }
        }
    }

    private void sortIPLWicketsDES(Comparator<WicketsFactSheet> runsComparator) {
        for(int i=0;i<wicketsIPLList.size()-1;i++){
            for(int j=0; j<wicketsIPLList.size()-1;j++){
                WicketsFactSheet wicketsData1 = wicketsIPLList.get(j);
                WicketsFactSheet wicketsData2 = wicketsIPLList.get(j+1);
                if(runsComparator.compare(wicketsData1,wicketsData2)<0){
                    wicketsIPLList.set(j, wicketsData2);
                    wicketsIPLList.set(j+1, wicketsData1);
                }
            }
        }
    }



}
