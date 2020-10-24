package Indian.State.Census.Analyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	/**
	 * returns number of entries in the given csv file throws exception if wrong
	 * file path is given
	 * 
	 * @param filePath
	 * @return
	 * @throws CensusAnalyserException
	 */
	public int loadStateCensusData(String filePath) throws CensusAnalyserException {
		int numOfRecords = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(CSVStateCensus.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStateCensus> censusCSVIterator = csvToBean.iterator();
			while (censusCSVIterator.hasNext()) {
				numOfRecords++;
				censusCSVIterator.next();
			}
		} catch (IOException exception) {
			throw new CensusAnalyserException(exception.getMessage(),
					CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return numOfRecords;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to indian state census analyser");
	}
}
