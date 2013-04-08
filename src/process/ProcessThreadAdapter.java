package process;

import java.awt.image.BufferedImage;

import algorithms.AbstractAlgorithm;

/**
 * This class play a role of threads manager in addition to be an adapter instance.
 * @author Corentin Legros
 *
 */
public class ProcessThreadAdapter extends AbstractProcessAdapter<BufferedImage[][]> {
	
	public ProcessThreadAdapter(AbstractAlgorithm algorithm, BufferedImage[][] data) {
		super(algorithm, data);
	}

	public void execute() {
		
	}
}
