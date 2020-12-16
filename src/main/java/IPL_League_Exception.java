public class IPL_League_Exception extends Exception{
    public IPL_League_Exception(String message, String name) {
    }

    enum ExceptionType {
        IPL_RUNS_FILE_PROBLEM, UNABLE_TO_PARSE, IPL_WICKETS_FILE_PROBLEM, NO_IPL_RUNS_DATA, NO_IPL_WICKETS_DATA, RUN_TIME_EXCEPTION
    }

    ExceptionType type;

    public IPL_League_Exception(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public IPL_League_Exception(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

}
