package launcher;

import controller.Controller;
import model.cross.PMXCross;
import model.entities.GeneticAlgorithm;
import model.mutation.InversionMutation;
import model.selection.RouletteSelection;
import view.MainWindow;

public class Main {

	private static Controller _ctrl;
	
	
	public static void init() {
		//Default values
		GeneticAlgorithm alg = new GeneticAlgorithm(
				100,new InversionMutation(), new RouletteSelection(),
				new PMXCross(), 0.6,0.05,0.0);
		_ctrl=new Controller(alg);
	}
	
	public static void main(String[] args) {
		init();
		MainWindow app=new MainWindow(_ctrl);
		app.setSize(1000,500);
		app.setVisible(true);
	}
}
