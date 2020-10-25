package Indian.State.Census.Analyser;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<k> implements ICSVBuilder<k> {
	public Iterator<k> getCSVFileIterator(Reader reader, Class<k> csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<k> csvToBeanBuilder = new CsvToBeanBuilder<k>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<k> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}
