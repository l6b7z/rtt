package ReactionTime;

import ReactionTime.Controller.Controller;
import java.util.LinkedList;
import ReactionTime.Model.StoredTimeRecordable;
import ReactionTime.Model.StoredTimeRecords;
import ReactionTime.View.ApplicationView;

public class ReactionTimeApplication implements Controller {

	private StoredTimeRecordable storedTimeRecords = new StoredTimeRecords();
	private final String EMPTY_LAST_RECORD = "--";
	private final String EMPTY_AVERAGE_OF= "--";
	
	public ReactionTimeApplication() {
		new ApplicationView(this);
	}

	public static void main(String[] args) {
		new ReactionTimeApplication();
	}

	@Override
	public LinkedList<Integer> getAllRecords() {
		
		return storedTimeRecords.getAllRecords();
	}

	@Override
	public String getLastRecord() {
		if(storedTimeRecords.getCurrentRecord() == -1) {
			return EMPTY_LAST_RECORD;
		}
		else {
			return Integer.toString(storedTimeRecords.getCurrentRecord());
		}
		
	}

	@Override
	public String getAverageOfRecordsInRange(int range) {
		if(storedTimeRecords.getAverageInRange(range) == -1) {
			return EMPTY_AVERAGE_OF;
		}
		else {
			return Integer.toString(storedTimeRecords.getAverageInRange(range));
		}
	}

	@Override
	public void addRecord(int reactionTimeMS) {
		storedTimeRecords.addRecord(reactionTimeMS);

	}

	@Override
	public int getRandomDelay() {
		// for the random delay in milliseconds purpose it should give
		// 500ms base plus 0 to 3000 milliseconds or from 0,5sec to 3,5 seconds
		return 500 + (int) (Math.random() * 3000);
	}

	@Override
	public void clearResults() {
		storedTimeRecords.removeAllRecords();

	}

	@Override
	public void clearLastResult() {
		storedTimeRecords.removeLastRecord();
	}
	
	@Override
	public int getRecordsMaxSize(){
		return storedTimeRecords.getRecordsMaxSize();
	}

}
