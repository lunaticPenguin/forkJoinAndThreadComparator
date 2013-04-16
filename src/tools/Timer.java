package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import process.IProcessAdapter;

/**
 * This class is able to store multiple results from several tests.
 * It gains interests when it is used with another tool able to draw out these data (,
 * like a statistics generator :) ).
 * 
 * For cause of simplicity of use, the implementation is made with the singleton pattern.
 * 
 * @author Corentin Legros
 */
final public class Timer {

	/**
	 * Data relative to thread processes type
	 */
	private HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> threadData;
	
	/**
	 * Data relative to fork/join processes type
	 */
	private HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> fjData;
	
	/**
	 * Instance of timer (singleton inside!)
	 */
	private static Timer instance;
	
	/**
	 * Timestamp when the timer start
	 */
	private long timestart;
	
	/**
	 * Timestamp when the timer takes end
	 */
	private long timeend;
	
	/**
	 * Percent range to pick up data during processes
	 */
	private int range;
	
	
	/**
	 * Get global timer instance
	 * @return
	 */
	public static Timer getInstance() {
		if (instance == null) {
			instance = new Timer();
		}
		return instance;
	}
	
	
	private Timer() {
		init(threadData);
	}
	
	/**
	 * This method is generically able to store a new pair key-value in the internal data.
	 * 
	 * @param HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> data
	 * @param String file
	 * @param Integer test_num
	 * @param Integer part_num
	 * @param int percent
	 */
	public void addData(int dataType, String file, Integer test_num, Integer part_num, int percent) {
		
		HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> data;
		if (dataType == IProcessAdapter.PROCESS_TYPE_THREAD) {
			data = threadData;
		} else {
			data = fjData;
		}
		
		if (!data.containsKey(file)) {
			data.put(file, getNewTest());
		}
		
		if (data.get(file).isEmpty() || data.get(file).size() + 1 < test_num) {
			data.get(file).set(test_num, getNewPart());
		}
		
		if (data.get(file).get(test_num).isEmpty() || data.get(file).get(test_num).size() + 1 < part_num) {
			data.get(file).get(test_num).set(part_num, getNewData());
		}
		data.get(file).get(test_num).get(part_num).put(Integer.valueOf((int)(System.currentTimeMillis() - timestart)), Integer.valueOf(percent));
	}
	
	/*
	 * This method allow to generically initialize internal data containers
	 * @param HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> data
	 */
	private void init(HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> data) {
		data = new HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>>();
		timestart = 0;
		timeend = 0;
		range = 15;
	}
	
	/*
	 * Get a new data container relative to a test (start launch) data
	 */
	private ArrayList<ArrayList<HashMap<Integer, Integer>>> getNewTest() {
		return new ArrayList<ArrayList<HashMap<Integer, Integer>>>();
	}
	
	/*
	 * Get a new data container relative to a part (thread/worker) data
	 */
	private ArrayList<HashMap<Integer, Integer>> getNewPart() {
		return new ArrayList<HashMap<Integer, Integer>>();
	}
	
	/*
	 * Get a new data container relative to a pair (timing & percent progress) data
	 */
	private HashMap<Integer, Integer> getNewData() {
		return new HashMap<Integer, Integer>();
	}
	
	/**
	 * Initialize and start timer
	 */
	public void start() {
		timestart = System.currentTimeMillis();
	}
	
	/**
	 * Stop timer
	 */
	public void stop() {
		if (timestart != 0) {
			timeend = System.currentTimeMillis();
		}
	}
	
	/**
	 * Indicates if the timer is running
	 * @return boolean
	 */
	public boolean isRunning() {
		return timestart != 0 && timeend != 0;
	}
	
	/**
	 * Clear all internal timer values, and reinitialize them
	 */
	public void clear() {
		clearInternalData(threadData);
		clearInternalData(fjData);
		
		timestart = 0;
		timeend = 0;
		
		init(threadData);
		init(fjData);
	}
	
	/*
	 * Clear all specified internal values (manually, because I think the JVM's GC sucks)
	 * @param HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> data The specified data
	 */
	private void clearInternalData(HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> data) {
		
		Set<Map.Entry<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>>> dataSet = data.entrySet();
		Iterator<Map.Entry<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>>> itDataSet = dataSet.iterator();
		
		ArrayList<ArrayList<HashMap<Integer, Integer>>> tmpTestAL;
		Iterator<ArrayList<HashMap<Integer, Integer>>> itTestAL;
		
		ArrayList<HashMap<Integer, Integer>> tmpPartAL;
		Iterator<HashMap<Integer, Integer>> itPartAL;
		
		while (itDataSet.hasNext()) {
			tmpTestAL = itDataSet.next().getValue();
			itTestAL = tmpTestAL.iterator();
			while (itTestAL.hasNext()) {
				tmpPartAL = itTestAL.next();
				itPartAL = tmpPartAL.iterator();
				while (itPartAL.hasNext()) {
					itPartAL.next().clear();
				}
				tmpPartAL.clear();
			}
			tmpTestAL.clear();
		}
		itDataSet = null;
		data.clear();
	}

	/**
	 * Get all internal data relative to the thread processes
	 * @return
	 */
	public HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> getThreadProcessData() {
		return threadData;
	}
	
	/**
	 * Get all internal data relative to the forkjoin processes
	 * @return
	 */
	public HashMap<String, ArrayList<ArrayList<HashMap<Integer, Integer>>>> getForkJoinProcessData() {
		return fjData;
	}
	
	/**
	 * Set the percent range to pick up data regularly during processes
	 * @param int range
	 */
	public void setPickUpRange(int range) {
		if (range < 0) {
			range -= range;
		} else if (range == 0) {
			range = 20;
		} else if (range > 100) {
			range = 100;
		}
		this.range = range;
	}
	
	/**
	 * Get the percent range
	 * @return int
	 */
	public int getPickUpRange() {
		return range;
	}
}
