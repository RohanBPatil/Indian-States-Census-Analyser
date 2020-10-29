package com.rohan.indianstatecensusanalyser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.CSVBuilder.CSVBuilderException;
import com.google.gson.Gson;

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
	 * UC2, TC 2.1 : checking file is correct or not by checking number of entries
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
	 * UC2, TC 2.2 : checking correct file path is given or not
	 */
	@Test
	public void givenWrongFile_shouldThrow_CensusAnalyserExceptionForStatesCode() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		CensusAnalyserException exception = assertThrows(CensusAnalyserException.class, () -> {
			stateCensusAnalyser.loadStateCode(NO_FILE_PATH);
		});
		assertTrue(exception.type == CensusAnalyserException.ExceptionType.NO_FILE);
	}

	/**
	 * UC2, TC 2.3 : checking correct file type(extension) is given or not
	 */
	@Test
	public void givenWrongTypeOfFile_shouldThrow_CensusAnalyserExceptionForStatesCode() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		CensusAnalyserException exception = assertThrows(CensusAnalyserException.class, () -> {
			stateCensusAnalyser.loadStateCode(WRONG_FILE_TYPE_PATH);
		});
		assertTrue(exception.type == CensusAnalyserException.ExceptionType.INCORRECT_FILE);
	}

	/**
	 * UC2, TC 2.4 : checking if throws exception for incorrect delimiter
	 */
	@Test
	public void givenWrongDelimiterFile_shouldThrow_CensusAnalyserExceptionForStatesCode() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		CensusAnalyserException exception = assertThrows(CensusAnalyserException.class, () -> {
			stateCensusAnalyser.loadStateCode(WRONG_DELIMITER_PATH);
		});
		assertTrue(exception.type == CensusAnalyserException.ExceptionType.INCORRECT_FILE);
	}

	/**
	 * UC2, TC 2.5 : checking if throws exception for incorrect headers
	 */
	@Test
	public void givenWrongHeadersInFile_shouldThrow_CensusAnalyserExceptionForStatesCode() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		CensusAnalyserException exception = assertThrows(CensusAnalyserException.class, () -> {
			stateCensusAnalyser.loadStateCode(INDIA_CENSUS_FILE_PATH);
		});
		assertTrue(exception.type == CensusAnalyserException.ExceptionType.INCORRECT_FILE);
	}

	/**
	 * UC 3 : sorting CSV file data
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult()
			throws CensusAnalyserException, IOException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_FILE_PATH);
		String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Andhra Pradesh", censusCSV[0].state);
	}

	/**
	 * UC 3 : sorting CSV file data
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResultForLastState()
			throws CensusAnalyserException, IOException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_FILE_PATH);
		String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
	}

	/**
	 * UC 4 : Sorting StateCode CSV File data
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadStateCode(INDIA_STATE_CODE_PATH);
		String sortedCensusData = stateCensusAnalyser.getStateCodeWiseSortedCensusData();
		CSVStates[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStates[].class);
		assertEquals("Andhra Pradesh New", censusCSV[0].state);
	}

	/**
	 * UC 4 : Sorting StateCode CSV File data
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStateCode_ShouldReturnSortedResultForLastState()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadStateCode(INDIA_STATE_CODE_PATH);
		String sortedCensusData = stateCensusAnalyser.getStateCodeWiseSortedCensusData();
		CSVStates[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStates[].class);
		assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
	}

	/**
	 * UC 5 : sorting data based on population in descending order
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_FILE_PATH);
		String sortedCensusData = stateCensusAnalyser.getPopulationWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Uttar Pradesh", censusCSV[0].state);
	}

	/**
	 * UC 5 : sorting data based on population in descending order
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnStatePopulation_ShouldReturnSortedResultForLast()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_FILE_PATH);
		String sortedCensusData = stateCensusAnalyser.getPopulationWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Sikkim", censusCSV[censusCSV.length - 1].state);
	}

	/**
	 * UC 6 : sorting data based on population density in descending order
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_FILE_PATH);
		String sortedCensusData = stateCensusAnalyser.getPopulationDensityWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Bihar", censusCSV[0].state);
	}

	/**
	 * UC 6 : sorting data based on population density in descending order
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResultForLast()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_FILE_PATH);
		String sortedCensusData = stateCensusAnalyser.getPopulationDensityWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Arunachal Pradesh", censusCSV[censusCSV.length - 1].state);
	}
	
	/**
	 * UC 7 : sorting data based on area in descending order
	 * 
	 * @throws IOException
	 * @throws CensusAnalyserException
	 * @throws CSVBuilderException
	 */
	@Test
	public void givenIndianCensusData_WhenSortedOnArea_ShouldReturnSortedResult()
			throws IOException, CensusAnalyserException, CSVBuilderException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		stateCensusAnalyser.loadStateCensusData(INDIA_CENSUS_FILE_PATH);
		String sortedCensusData = stateCensusAnalyser.getAreaWiseSortedCensusData();
		CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		assertEquals("Rajasthan", censusCSV[0].state);
	}
}
