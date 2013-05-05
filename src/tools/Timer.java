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
	private HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>> threadData;
	
	/**
	 * Data relative to fork/join processes type
	 */
	private HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>> fjData;
	
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
	 * Simple reference to establish a shortcut in the code (this reference always
	 * points on the current test (both thread/forkJoin)
	 */
	private ArrayList<ArrayList<ArrayList<Integer>>> currentTestReference;
	
	/**
	 * Counter which indicates the current test number for the chosen file
	 * using the thread processes
	 */
	private HashMap<String, Integer> currentThreadTest;
	
	/**
	 * Counter which indicates the current test number for chosen file
	 * using the fork join processes
	 */
	private HashMap<String, Integer> currentForkJoinTest;
	
	/**
	 * Store the current filename
	 */
	private String currentFilename;
	
	/**
	 * Indicates the last used process type
	 */
	private int currentProcessType;
	
	public final String CSV_SEPARATOR = ";";
	
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
		init();
	}
	
	/**
	 * This method is generically able to store a new time value in the internal data.
	 * 
	 * @param Integer part_num
	 * @param int percent
	 */
	public void addData(Integer part_num) {
		
		HashMap<String, Integer> currentFileTestIndex = currentProcessType == IProcessAdapter.PROCESS_TYPE_THREAD ? currentThreadTest : currentForkJoinTest;
		
		if (currentTestReference.get(currentFileTestIndex.get(currentFilename)).get(part_num).size() < 100 / range) {
			currentTestReference.get(currentFileTestIndex.get(currentFilename)).get(part_num).add(Integer.valueOf((int)(System.nanoTime() - timestart)));
		}
	}
	
	/*
	 * This method allow to generically initialize internal data containers
	 * @param HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>> data
	 */
	private void init() {
		fjData = new HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>>();
		threadData = new HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>>();
		
		timestart = 0;
		timeend = 0;
		range = 15;
		
		currentThreadTest = new HashMap<String, Integer>();
		currentForkJoinTest = new HashMap<String, Integer>();
	}
	
	/*
	 * Get a new data container relative to a test (start launch) data
	 */
	private ArrayList<ArrayList<ArrayList<Integer>>> getNewTest() {
		return new ArrayList<ArrayList<ArrayList<Integer>>>();
	}
	
	/*
	 * Get a new data container relative to a part (thread/worker) data
	 * 
	 * @param int nbPart number threads/workers to create
	 */
	private ArrayList<ArrayList<Integer>> getNewPart(int nbPart) {
		nbPart = nbPart <= 0 ? nbPart = 20 : nbPart;
		return new ArrayList<ArrayList<Integer>>(nbPart);
	}
	
	/*
	 * Get a new data container relative to a percent progress
	 */
	private ArrayList<Integer> getNewData() {
		return new ArrayList<Integer>();
	}
	
	/**
	 * Initialize and start timer
	 * 
	 * @param int dataType type of processes whose results have to be stored
	 * @param String file name of the file which is treated
	 * @param int nbPart number of the thread/workers used in the current test
	 */
	public void start(int dataType, String file, int nbPart) {
		
		currentFilename = file;
		currentProcessType = dataType;
		HashMap<String, Integer> currentFileTestIndex = currentProcessType == IProcessAdapter.PROCESS_TYPE_THREAD ? currentThreadTest : currentForkJoinTest;
		HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>> data;
		if (currentProcessType == IProcessAdapter.PROCESS_TYPE_THREAD) {
			data = threadData;
		} else {
			data = fjData;
		}
		
		// if we had to create an entire new data structure
		if (!currentFileTestIndex.containsKey(currentFilename)) {
			currentFileTestIndex.put(currentFilename, 0);
			currentTestReference = getNewTest();
			data.put(currentFilename, currentTestReference);
		} else {
			int tmpIndex = currentFileTestIndex.get(currentFilename);
			currentFileTestIndex.put(currentFilename, ++tmpIndex);
			currentTestReference = data.get(currentFilename);
		}
		
		currentTestReference.add(getNewPart(nbPart));
		
		for (int i = 0 ; i < nbPart ; ++i) {
			currentTestReference.get(currentFileTestIndex.get(currentFilename)).add(getNewData());
		}
		
		timestart = System.nanoTime();
	}
	
	/**
	 * Stop timer
	 */
	public void stop() {
		if (timestart != 0) {
			timeend = System.nanoTime();
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
		
		init();
		init();
	}
	
	/*
	 * Clear all specified internal values (manually, because I think the JVM's GC sucks)
	 * @param HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>> data The specified data
	 */
	private void clearInternalData(HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>> data) {
		
		Set<Map.Entry<String, ArrayList<ArrayList<ArrayList<Integer>>>>> dataSet = data.entrySet();
		Iterator<Map.Entry<String, ArrayList<ArrayList<ArrayList<Integer>>>>> itDataSet = dataSet.iterator();
		
		ArrayList<ArrayList<ArrayList<Integer>>> tmpTestAL;
		Iterator<ArrayList<ArrayList<Integer>>> itTestAL;
		
		ArrayList<ArrayList<Integer>> tmpPartAL;
		Iterator<ArrayList<Integer>> itPartAL;
		
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
	public HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>> getThreadProcessData() {
		return threadData;
	}
	
	/**
	 * Get all internal data relative to the forkjoin processes
	 * @return
	 */
	public HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>> getForkJoinProcessData() {
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
	
	/**
	 * This method format specified internal data to csv string format used to 
	 * generate statistics into other software like Calc or Excel.
	 * 
	 * @param int typeData
	 * @param String filename
	 * @return
	 */
	public String getDataAsString(int typeData, String filename) {
		
		String processTypeName = typeData == IProcessAdapter.PROCESS_TYPE_THREAD ? "Thread" : "ForkJoin";
		HashMap<String, ArrayList<ArrayList<ArrayList<Integer>>>> data;
		if (typeData == IProcessAdapter.PROCESS_TYPE_THREAD) {
			data = threadData;
		} else {
			data = fjData;
		}
		
		StringBuilder sbContent = new StringBuilder();
		StringBuilder sbDataTitle = new StringBuilder();
		StringBuilder sbDataHeader = new StringBuilder();
		
		if (data.containsKey(filename)) {
			
			ArrayList<ArrayList<Integer>> testTmp;
			Iterator<ArrayList<ArrayList<Integer>>> testIterator = data.get(filename).iterator();
			
			Iterator<ArrayList<Integer>> entitiesIterator;
			
			ArrayList<Integer> dataTmp;
			
			Iterator<Integer> progressIterator;
			
			int testCounter = 1;
			int entityCounter = 1;
			int percentsCounter = 0;
			
			sbDataHeader.append("Percent progress").append("#");
			int nbValues = 100 / range;
			for (int i = 0 ; i < nbValues ; ++i) {
				sbDataHeader.append(CSV_SEPARATOR).append(percentsCounter + range);
				percentsCounter += range;
			}
			
			// for each test, rendering of all test data specifications
			while (testIterator.hasNext()) {
				
				testTmp = testIterator.next();
				entitiesIterator = testTmp.iterator();
				
				sbDataTitle.setLength(0);
				sbDataTitle.append("\n\nTest #").append(testCounter).append(" -- ").append(processTypeName);
				
				sbContent.append(sbDataTitle).append("\n\n");
				sbContent.append(sbDataHeader).append("\n");
				
				entityCounter = 1;
				
				// for all test entities
				while (entitiesIterator.hasNext()) {
					
					dataTmp = entitiesIterator.next();
					progressIterator = dataTmp.iterator();
					sbContent.append(entityCounter);
					
					while (progressIterator.hasNext()) {
						sbContent.append(CSV_SEPARATOR).append(progressIterator.next());
					}
					sbContent.append("\n");
					++entityCounter;
				}

				++testCounter;
			}
		} else {
			System.out.println("A problem occured with this data (no luck!) :(");
		}
		
		return sbContent.toString();
	}
}
