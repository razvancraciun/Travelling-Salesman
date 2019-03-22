package model.selection;

import model.entities.Individual;
import model.entities.Population;


//BUGGY
public class TruncationSelection implements Selection{
	@Override
	public Population apply(Population source) {
		System.out.println(source);
		Population result=new Population(source.length());
		source.sort();
		
		for(int i=0;i<result.length();i++) {
			result.setIndividual(i, new Individual(source.getIndividual(i/2)));
		}
		shuffle(result);
		System.out.println(result);
		return result;
	}
	
	private void shuffle(Population result) {
		for(int i=0;i<result.length();i++) {
			int point1=(int)(Math.random()*result.length());
			int point2=(int)(Math.random()*result.length());
			Individual temp=result.getIndividual(point1);
			result.setIndividual(point1, result.getIndividual(point2));
			result.setIndividual(point2, temp);
		}
	}

	@Override
	public String toString() {
		return "Trucantion";
	}

}
