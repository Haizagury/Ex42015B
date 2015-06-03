package view;

// TODO: Auto-generated Javadoc
/**
 * The Class CLIRunnable implements Runnable.
 * @author haizagury and livna haim.
 * @version 1.0.
 * @since 17.05.15 .
 */
public class CLIRunnable implements Runnable  {
		
		/** The cl i. */
		NewCli clI;

		/**
		 * Instantiates a new CLI runnable.
		 *
		 * @param clI the cl i
		 */
		public CLIRunnable(NewCli clI) {
		
			super();
			this.clI = clI;
		
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
		
			clI.start();
			
		}

	}
