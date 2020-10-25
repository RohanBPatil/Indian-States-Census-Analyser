package Indian.State.Census.Analyser;

public class CSVBuilderFactory {
	public static <k> ICSVBuilder<k> createCSVBuilder() {
		return new OpenCSVBuilder<>();
	}
}
