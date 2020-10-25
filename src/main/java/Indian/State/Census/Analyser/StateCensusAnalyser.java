package Indian.State.Census.Analyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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
			Iterator<CSVStateCensus> censusCSVIterator = loadCSVData(reader, CSVStateCensus.class);
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
			Iterator<CSVStates> censusCSVIterator = loadCSVData(reader, CSVStates.class);
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
	 * Refactor 1A : to avoid DRY for extracting data
	 * 
	 * @param <k>
	 * @param reader
	 * @param csvClass
	 * @return
	 * @throws CensusAnalyserException
	 */
	private static <k> Iterator<k> loadCSVData(Reader reader, Class<k> csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<k> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<k> csvToBean = csvToBeanBuilder.build();
			Iterator<k> CSVIterator = csvToBean.iterator(); // wrong type of file extension,
			return CSVIterator; // delimiter or headers
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}

	}

	/**
	 * Refactor 1B : to avoid DRY for counting number of records
	 * 
	 * @param <k>
	 * @param iterator
	 * @return
	 */
	private static <k> int getNumOfRecords(Iterator<k> iterator) {
		int countOfRecord = 0;
		while (iterator.hasNext()) {
			countOfRecord++;
			iterator.next();
		}
		return countOfRecord;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to indian state census analyser");
	}
}