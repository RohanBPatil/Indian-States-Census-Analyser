package Indian.State.Census.Analyser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StateCensusAnalyserTest {
	private static final String INDIA_CENSUS_FILE_PATH = "C:\\Users\\abc\\eclipse-workspace\\Indian State Census Analyser\\IndiaStateCensusData.csv";

	/**
	 * checking file is correct or not by checking number of entries
	 */
	@Test
	public void givenStateCensusCSVFileReturnsCorrectNumberOfRecords() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		assertEquals(29, stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_FILE_PATH));
	}
}
