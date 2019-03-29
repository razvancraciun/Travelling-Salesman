package launcher;

import javax.swing.SwingUtilities;

import controller.Controller;
import model.cross.MyCross;
import model.entities.GeneticAlgorithm;
import model.mutation.HeuristicMutation;
import model.selection.TruncationSelection;
import view.MainWindow;

public class Main {

	private static Controller _ctrl;
	
	
	public static void init() {
		//Default values
		GeneticAlgorithm alg = new GeneticAlgorithm(
				100,new HeuristicMutation(), new TruncationSelection(),
				new MyCross(), 0.6,0.05,0.0);
		_ctrl=new Controller(alg);
	}
	
	public static void main(String[] args) {
		init();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainWindow app=new MainWindow(_ctrl);
				app.setSize(1000,500);
				app.setVisible(true);
			}			
		});
		
	}
}
