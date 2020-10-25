package Indian.State.Census.Analyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<k> {
	public Iterator<k> getCSVFileIterator(Reader reader, Class<k> csvClass) throws CSVBuilderException;
	public List<k> getCSVFileList(Reader reader, Class<k> csvClass) throws CSVBuilderException;
}
