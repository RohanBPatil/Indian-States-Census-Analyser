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

			CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(CSVStateCensus.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStateCensus> censusCSVIterator = csvToBean.iterator(); // wrong type of file extension,
																				// delimiter or headers

			while (censusCSVIterator.hasNext()) {
				numOfRecords++;
				censusCSVIterator.next();
			}
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

			CsvToBeanBuilder<CSVStates> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(CSVStates.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStates> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStates> censusCSVIterator = csvToBean.iterator(); // wrong type of file extension,
																			// delimiter or headers

			while (censusCSVIterator.hasNext()) {
				numOfRecords++;
				censusCSVIterator.next();
			}
		} catch (NoSuchFileException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		} catch (RuntimeException exception) {
			throw new CensusAnalyserException(exception.getMessage(),
					CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}

		return numOfRecords;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to indian state census analyser");
	}
}