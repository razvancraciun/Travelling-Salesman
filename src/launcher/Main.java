package launcher;

import controller.Controller;
import model.Distances;
import model.cross.Cross;
import model.cross.NoCross;
import model.cross.PMX;
import model.entities.Elite;
import model.entities.GeneticAlgorithm;
import model.entities.Individual;
import model.entities.Population;
import model.mutation.Inversion;
import model.mutation.Mutation;
import model.mutation.NoMutation;
import model.selection.NoSelection;
import model.selection.Roulette;
import model.selection.Selection;

public class Main {

	public static void main(String[] args) {
		
		Population population=new Population(100);
		Mutation mutation= new Inversion();
		Selection selection= new Roulette();
		Cross cross=new PMX();
		double crossChance=0.6;
		double mutationChance=0.05;
		double elitism=0.00;
		GeneticAlgorithm ga= new GeneticAlgorithm(population, mutation,selection,cross,crossChance,mutationChance,elitism);
		Controller ctrl=new Controller(ga);
		ctrl.run(100); 

	}
}
