package Indian.State.Census.Analyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<k> implements ICSVBuilder<k> {
	public Iterator<k> getCSVFileIterator(Reader reader, Class<k> csvClass) throws CSVBuilderException {
		return this.getCSVBean(reader,csvClass).iterator();
	}
	
	@Override
    public List<k> getCSVFileList(Reader reader, Class<k> csvClass) throws CSVBuilderException {
       return this.getCSVBean(reader,csvClass).parse();

    }

    private CsvToBean<k> getCSVBean(Reader reader, Class<k> csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<k> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
