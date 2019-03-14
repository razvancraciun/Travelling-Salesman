package launcher;

import model.Distances;
import model.entities.Individual;
import model.entities.Population;

public class Main {

	public static void main(String[] args) {
		Distances dist=Distances.getInstance();
		//changes : Population class, GeneticAlgorithm class
		Population pop=new Population(3);
		Population pop2=new Population(pop);
		System.out.println(pop);
		System.out.println(pop2);
		
	}
}
