package Indian.State.Census.Analyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.CSVBuilder.CSVBuilderException;
import com.CSVBuilder.CSVBuilderFactory;
import com.CSVBuilder.ICSVBuilder;
import com.google.gson.Gson;

public class StateCensusAnalyser {
	List<CSVStateCensus> censusCSVList = null;
	List<CSVStates> stateCodeCSVList = null;

	/**
	 * returns number of entries in the given csv file throws exception if wrong
	 * file path is given or wrong type of file is there
	 * 
	 * @param filePath
	 * @return
	 * @throws CensusAnalyserException
	 * @throws IOException
	 * @throws CSVBuilderException
	 */
	public int loadStateCensusData(String filePath) throws CensusAnalyserException, IOException, CSVBuilderException {
		int numOfRecords = 0;
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath)); // no such file exception
			ICSVBuilder<CSVStateCensus> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			censusCSVList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
			numOfRecords = censusCSVList.size();
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
	 * @throws CSVBuilderException
	 */
	public int loadStateCode(String filePath) throws CensusAnalyserException, IOException, CSVBuilderException {
		int numOfRecords = 0;
		try {
			Reader readers = Files.newBufferedReader(Paths.get(filePath)); // no such file exception
			ICSVBuilder<CSVStates> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			stateCodeCSVList = csvBuilder.getCSVFileList(readers, CSVStates.class);
			numOfRecords = stateCodeCSVList.size();
		} catch (NoSuchFileException exception) {
			throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.NO_FILE);
		} catch (RuntimeException exception) {
			throw new CensusAnalyserException(exception.getMessage(),
					CensusAnalyserException.ExceptionType.INCORRECT_FILE);
		}

		return numOfRecords;
	}

	public String getStateWiseSortedCensusData() throws CensusAnalyserException {
		if (censusCSVList == null || censusCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusCSVList, censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}

	private <k> void sort(List<k> list, Comparator<k> censusComparator) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size() - i - 1; j++) {
				k census1 = list.get(j);
				k census2 = list.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					list.set(j, census2);
					list.set(j + 1, census1);
				}
			}
		}
	}

	public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
		if (stateCodeCSVList == null || stateCodeCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<CSVStates> censusComparator = Comparator.comparing(census -> census.stateCode);
		this.sort(stateCodeCSVList, censusComparator);
		String sorted = new Gson().toJson(stateCodeCSVList);
		return sorted;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to indian state census analyser");
	}
}