package ReactionTime.Model;

import java.util.LinkedList;

public interface StoredTimeRecordable  {
	
	public LinkedList<Integer> getAllRecords();
	
	public int getAproximateAverageInRange(int range);
	
	public int getAverageInRange(int Range);
	
	public void addRecord(int ReactionTimeMS);
	
	public void removeLastRecord();
	
	public void removeAllRecords();
	
	public int getCurrentRecord();

	public int getRecordsMaxSize();
	
}
