import java.util.List;

import com.google.gson.Gson;
import com.jarfile.CSVBuilderException;
import com.jarfile.CSVBuilderFactory;
import com.jarfile.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class IPL_League {
    List<RunsFactSheet> runsIPLList = null;
    List<WicketsFactSheet> wicketsIPLList = null;

    public int loadIPLRunsData(String csvFilePath) throws IPL_League_Exception {
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


}
