package model.selection;

import model.entities.Individual;
import model.entities.Population;

public class RouletteSelection implements Selection {

	//TODO DOC
	@Override
	public Population apply(Population source) {
		Population result=new Population(source);
		
		double[] relativeFitness=new double[source.length()];
		int max=source.getMaximumFitness();
		int sum=0;
		
		//Calculating relative fitness values
		for(int i=0;i<source.length();i++) {
			relativeFitness[i]=max-source.getIndividual(i).getFitness();
			sum+=relativeFitness[i];
		}
		
		//if all the values are equal there is no point in the selection
		if(sum==0) {
			return source;
		}
		
		//Clamps values between 0 and 1
		for(int i=0;i<relativeFitness.length;i++) {
			relativeFitness[i]/=sum;
		}
		
		//filling in the resulting population
		for(int i=0;i<result.length();i++) {
			result.setIndividual(i,new Individual(pickOne(source,relativeFitness)));
		}
		return result;
	}

	//TODO DOC
	private Individual pickOne(Population source, double[] relativeFitness) {
		double prob=Math.random();
		double sum=0.0;
		for(int i=0;i<source.length();i++) {
			sum+=relativeFitness[i];
			if(prob<sum) {
				return source.getIndividual(i);
			}
		}
		throw new IllegalArgumentException("Relative fitnesses do not add up to 1");
	}

	@Override
	public String toString() {
		return "Roulette";
	}
}
