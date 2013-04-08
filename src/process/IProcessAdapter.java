/**
 * 
 */
package process;

/**
 * Interface which introduce a generically a way to call a process regardless it's type
 * @author Corentin Legros
 *
 */
public interface IProcessAdapter {
	
	public final static int TYPE_PROCESS_THREAD = 0;
	public final static int TYPE_PROCESS_WORKERS = 1;
	
	public void execute();
}
