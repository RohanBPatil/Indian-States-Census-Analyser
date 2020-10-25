package Indian.State.Census.Analyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<k> {
	public Iterator<k> getCSVFileIterator(Reader reader, Class<k> csvClass) throws CensusAnalyserException;
}
