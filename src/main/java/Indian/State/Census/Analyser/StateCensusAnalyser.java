package Indian.State.Census.Analyser;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvFormat;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParserSettings;

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
	public int loadStateCensusData(String filePath) throws CensusAnalyserException {
		int numOfRecords = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));

			CsvParserSettings settings = new CsvParserSettings();
			settings.detectFormatAutomatically();
			CsvParser parser = new CsvParser(settings);
			parser.parseAll(new File(filePath));
			CsvFormat format = parser.getDetectedFormat();
			if (format.getDelimiter() != ',')
				throw new CensusAnalyserException("Incorrect delimiter",
						CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER);

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
