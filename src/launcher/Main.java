package launcher;

import controller.Controller;
import model.cross.Cross;
import model.cross.NoCross;
import model.cross.PMXCross;
import model.entities.Elite;
import model.entities.GeneticAlgorithm;
import model.entities.Individual;
import model.entities.Population;
import model.misc.Distances;
import model.mutation.ExchangeMutation;
import model.mutation.InversionMutation;
import model.mutation.Mutation;
import model.mutation.NoMutation;
import model.selection.NoSelection;
import model.selection.RouletteSelection;
import model.selection.Selection;
import view.MainWindow;

public class Main {

	private static Controller _ctrl;
	
	
	public static void init() {
		//Default values
		Population population= new Population(100);
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
