package ReactionTime.Controller;

import java.util.LinkedList;

public interface Controller {
	
	public LinkedList<Integer> getAllRecords();
	
	public String getLastRecord();
	
	public String getAverageOfRecordsInRange(int range);
	
	public void addRecord(int reactionTimeMS);

	public void clearLastResult();

	public void clearResults();

	public int getRandomDelay();
	
	public int getRecordsMaxSize();

}