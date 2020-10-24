package Indian.State.Census.Analyser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StateCensusAnalyserTest {
	private static final String INDIA_CENSUS_FILE_PATH = "C:\\Users\\abc\\eclipse-workspace\\Indian State Census Analyser\\IndiaStateCensusData.csv";
	private static final String NO_FILE_PATH = "C:\\Users\\abc\\eclipse-workspace\\IndianState Census Analyser\\IndiaStateCensusData.csv";
	private static final String WRONG_FILE_TYPE_PATH = "C:\\Users\\abc\\eclipse-workspace\\IndiaStateCensusData.csv";

	/**
	 * checking file is correct or not by checking number of entries
	 */
	@Test
	public void givenStateCensusCSVFileReturnsCorrectNumberOfRecords() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			assertEquals(29, stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_FILE_PATH));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * checking correct file path is given or not
	 */
	@Test
	public void givenWrongFile_shouldThrow_CensusAnalyserException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		CensusAnalyserException exception = assertThrows(CensusAnalyserException.class, () -> {
			stateCensusAnalyser.loadStateCensusData(NO_FILE_PATH);
		});
		assertTrue(exception.type == CensusAnalyserException.ExceptionType.NO_FILE);
	}

	/**
	 * checking correct file type is given or not
	 */
	@Test
	public void givenWrongTypeOfFile_shouldThrow_CensusAnalyserException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		CensusAnalyserException exception = assertThrows(CensusAnalyserException.class, () -> {
			stateCensusAnalyser.loadStateCensusData(WRONG_FILE_TYPE_PATH);
		});
		assertTrue(exception.type == CensusAnalyserException.ExceptionType.INCORRECT_FILE);
	}
}
