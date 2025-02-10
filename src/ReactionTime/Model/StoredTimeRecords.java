package ReactionTime.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class StoredTimeRecords implements StoredTimeRecordable {

	private final int RECORDS_MAX_SIZE = 20;
	private final String FILEPATH = "resources/records.txt";

	private LinkedList<Integer> records;

	public StoredTimeRecords() {
		records = readRecordsFromAFile();
	}

	@Override
	public void addRecord(int ReactionTimeMS) {

		if (records.size() > RECORDS_MAX_SIZE) {
			records.removeLast();
			records.addFirst(ReactionTimeMS);
		} else {
			records.addFirst(ReactionTimeMS);
		}

		saveRecordsToAFile(records);
	}

	@Override
	public LinkedList<Integer> getAllRecords() {
		return records;
	}

	@Override
	public void removeAllRecords() {
		records = new LinkedList<Integer>();
		saveRecordsToAFile(records);

	}

	@Override
	public void removeLastRecord() {
		if (!records.isEmpty()) {
			records.removeFirst();
			saveRecordsToAFile(records);
		}
	}

	@Override
	public int getCurrentRecord() {
		if(records.isEmpty()) {
			return -1;
		}
		else {
			return records.get(0);
		}
	}


	@Override
	public int getAverageInRange(int range) {
		int averageInRange = 0;

		if (records.isEmpty()) {
			return -1;
		}
		else if(records.size() < range) {
			return -1;
		}
		else if(records.size() >= range) {
			for (int i = 0; i < range; i++) {
				averageInRange += records.get(i);
			}
			return averageInRange / range;
		}
		else {
			return -1;
		}
	}
	
	@Override
	// if records aren't empty will return lesser possible average if not enough records found 
	// (for example average in range of 3 if only 2 records found would return average of 2) 
	public int getAproximateAverageInRange(int range) {

		int averageInRange = 0;

		if (records.isEmpty()) {
			return -1;
		} 
		else if(records.size() >= range) {
			for (int i = 0; i < range; i++) {
				averageInRange += records.get(i);
			}
			return averageInRange / range;
		}
		else {
			for (int i = 0; i < records.size(); i++) {
				averageInRange += records.get(i);
			}
			return averageInRange / records.size();
		}

	}

	private void saveRecordsToAFile(LinkedList<Integer> records) {
		try (FileWriter fileWriter = new FileWriter(FILEPATH);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
			boolean FirstElement = true;
			for (Integer record : records) {
				if (FirstElement) {
					bufferedWriter.write(Integer.toString(record));
					FirstElement = false;
				} else {
					bufferedWriter.write("\n" + Integer.toString(record));
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private LinkedList<Integer> readRecordsFromAFile() {

		Path path = Paths.get(FILEPATH);
		if (Files.exists(path)) {

			try (FileReader fileReader = new FileReader(FILEPATH);
					BufferedReader bufferedReader = new BufferedReader(fileReader)) {

				LinkedList<Integer> records = new LinkedList<Integer>();

				String textLine;
				while ((textLine = bufferedReader.readLine()) != null) {
					if (records.size() > RECORDS_MAX_SIZE) {
						return new LinkedList<Integer>();
					}
					records.addLast(Integer.parseInt(textLine));
				}
				return records;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new LinkedList<Integer>();

	}

	public int getRecordsMaxSize() {
		return RECORDS_MAX_SIZE;
	}

}
