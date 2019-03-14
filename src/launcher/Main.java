package launcher;

import model.Distances;
import model.entities.Individual;

public class Main {

	public static void main(String[] args) {
		Distances dist=Distances.getInstance();
		
		Individual ind=new Individual(dist.length());
		ind.evaluate();
		Individual ind2=new Individual(ind);
		
		System.out.println(ind);
		System.out.println(ind2);
	}
}
