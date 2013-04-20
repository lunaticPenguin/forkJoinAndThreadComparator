/**
 * 
 */
package process;

/**
 * Interface which introduce a generically a way to call a process regardless it's type
 * @author Corentin Legros
 *
 */
public interface IProcessAdapter<T> {
	
	public final static int PROCESS_TYPE_UNSELECTED = 0;
	public final static int PROCESS_TYPE_THREAD = 1;
	public final static int PROCESS_TYPE_FORKJOIN = 2;
	
	/**
	 * This method is able to launch the divided process
	 */
	public void execute();
}
