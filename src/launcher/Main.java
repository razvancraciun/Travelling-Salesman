package launcher;

import controller.Controller;
import model.Distances;
import model.cross.Cross;
import model.cross.NoCross;
import model.cross.PMX;
import model.entities.GeneticAlgorithm;
import model.entities.Individual;
import model.entities.Population;
import model.mutation.Mutation;
import model.mutation.NoMutation;
import model.selection.NoSelection;
import model.selection.Roulette;
import model.selection.Selection;

public class Main {

	public static void main(String[] args) {
		Distances dist=Distances.getInstance();
		
		Population pop=new Population(10);
		Mutation mutation= new NoMutation();
		Selection selection= new Roulette();
		Cross cross=new NoCross();
		double crossChance=1;
		GeneticAlgorithm ga= new GeneticAlgorithm(pop, mutation,selection,cross,crossChance);
		Controller ctrl=new Controller(ga);
		ctrl.run(10);
	}
}
