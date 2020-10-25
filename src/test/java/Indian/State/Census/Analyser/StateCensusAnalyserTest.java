package Indian.State.Census.Analyser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StateCensusAnalyserTest {
	private static final String INDIA_CENSUS_FILE_PATH = "C:\\Users\\abc\\eclipse-workspace\\Indian State Census Analyser\\IndiaStateCensusData.csv";
	private static final String NO_FILE_PATH = "C:\\Users\\abc\\eclipse-workspace\\Indiantate Census Analyser\\IndiaStateCensusData.csv";
	private static final String WRONG_FILE_TYPE_PATH = "C:\\Users\\abc\\eclipse-workspace\\check.pptx";
	private static final String WRONG_DELIMITER_PATH = "C:\\Users\\abc\\eclipse-workspace\\indiaStateCensusDelimiter.csv";
	private static final String WRONG_HEADER_FILE_PATH = "C:\\Users\\abc\\eclipse-workspace\\Indian State Census Analyser\\IndiaStateCode.csv";
	private static final String INDIA_STATE_CODE_PATH = "C:\\Users\\abc\\eclipse-workspace\\Indian State Census Analyser\\IndiaStateCode.csv";
	/**
	 * UC1, TC 1.1 : checking file is correct or not by checking number of entries
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
	 * UC1, TC 1.2 : checking correct file path is given or not
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
	 * UC1, TC 1.3 : checking correct file type(extension) is given or not
	 */
	@Test
	public void givenWrongTypeOfFile_shouldThrow_CensusAnalyserException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		CensusAnalyserException exception = assertThrows(CensusAnalyserException.class, () -> {
			stateCensusAnalyser.loadStateCensusData(WRONG_FILE_TYPE_PATH);
		});
		assertTrue(exception.type == CensusAnalyserException.ExceptionType.INCORRECT_FILE);
	}

	/**
	 * UC1, TC 1.4 : checking if throws exception for incorrect delimiter
	 */
	@Test
	public void givenWrongDelimiterFile_shouldThrow_CensusAnalyserException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		CensusAnalyserException exception = assertThrows(CensusAnalyserException.class, () -> {
			stateCensusAnalyser.loadStateCensusData(WRONG_DELIMITER_PATH);
		});
		assertTrue(exception.type == CensusAnalyserException.ExceptionType.INCORRECT_FILE);
	}

	/**
	 * UC1, TC 1.5 : checking if throws exception for incorrect headers
	 */
	@Test
	public void givenWrongHeadersInFile_shouldThrow_CensusAnalyserException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		CensusAnalyserException exception = assertThrows(CensusAnalyserException.class, () -> {
			stateCensusAnalyser.loadStateCensusData(WRONG_HEADER_FILE_PATH);
		});
		assertTrue(exception.type == CensusAnalyserException.ExceptionType.INCORRECT_FILE);
	}
	
	/**
	 * UC2, TC 1.1 : checking file is correct or not by checking number of entries
	 */
	@Test
	public void givenStateCodeCSVFile_shouldReturn_CorrectNumberOfRecords() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			assertEquals(37, stateCensusAnalyser.loadStateCode(INDIA_STATE_CODE_PATH));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * UC2, TC 1.2 : checking correct file path is given or not
	 */
	@Test
	public void givenWrongFile_shouldThrow_CensusAnalyserExceptionForStatesCode() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		CensusAnalyserException exception = assertThrows(CensusAnalyserException.class, () -> {
			stateCensusAnalyser.loadStateCode(NO_FILE_PATH);
		});
		assertTrue(exception.type == CensusAnalyserException.ExceptionType.NO_FILE);
	}
}
