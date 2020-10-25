package Indian.State.Census.Analyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

	/**
	 * returns number of entries in the given csv file throws exception if wrong
	 * file path is given or wrong type of file is there
	 * 
	 * @param filePath
	 * @return
	 * @throws CensusAnalyserException
	 * @throws IOException
	 */
	public int loadStateCensusData(String filePath) throws CensusAnalyserException, IOException {
		int numOfRecords = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath)); // no such file exception
			Iterator<CSVStateCensus> censusCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader,
					CSVStateCensus.class);
			numOfRecords = getNumOfRecords(censusCSVIterator);
		} catch (NoSuchFileException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		} catch (RuntimeException exception) {
			throw new CensusAnalyserException(exception.getMessage(),
					CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}

		return numOfRecords;
	}

	/**
	 * returns number of entries in the given csv file throws exception if wrong
	 * file path is given or wrong type of file is there
	 * 
	 * @param filePath
	 * @return
	 * @throws CensusAnalyserException
	 * @throws IOException
	 */
	public int loadStateCode(String filePath) throws CensusAnalyserException, IOException {
		int numOfRecords = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath)); // no such file exception
			Iterator<CSVStates> censusCSVIterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStates.class);
			numOfRecords = getNumOfRecords(censusCSVIterator);
		} catch (NoSuchFileException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		} catch (RuntimeException exception) {
			throw new CensusAnalyserException(exception.getMessage(),
					CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}

		return numOfRecords;
	}

	/**
	 * Refactor 2 : to ensure single responsibility principle and delegation
	 * principle
	 * 
	 * @param <k>
	 * @param iterator
	 * @return
	 */
	private <k> int getNumOfRecords(Iterator<k> iterator) {
		Iterable<k> csvIterable = () -> iterator;
		int countOfRecords = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return countOfRecords;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to indian state census analyser");
	}
}