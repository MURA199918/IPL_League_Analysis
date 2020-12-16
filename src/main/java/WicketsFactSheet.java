import com.opencsv.bean.CsvBindByName;

public class WicketsFactSheet {
    @CsvBindByName(column = "PLAYER", required = true)
    public String name;

    @CsvBindByName(column = "Mat", required = true)
    public int matches;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public float overs;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "4w", required = true)
    public int fourWicketHaul;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWicketHaul;

    @Override
    public String toString() {
        return "WicketsFactSheet{" +
                "name='" + name + '\'' +
                ", matches=" + matches +
                ", innings=" + innings +
                ", overs=" + overs +
                ", runs=" + runs +
                ", wickets=" + wickets +
                ", average=" + average +
                ", economy=" + economy +
                ", strikeRate=" + strikeRate +
                ", fourWicketHaul=" + fourWicketHaul +
                ", fiveWicketHaul=" + fiveWicketHaul +
                '}';
    }
}
