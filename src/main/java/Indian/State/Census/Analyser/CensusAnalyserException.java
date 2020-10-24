package Indian.State.Census.Analyser;

@SuppressWarnings("serial")
public class CensusAnalyserException extends Exception {
	public enum ExceptionType {
		NO_FILE, INCORRECT_FILE
	}

	public ExceptionType type;

	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
