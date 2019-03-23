package model.mutation;

import java.util.ArrayList;
import java.util.List;

import model.entities.Individual;

public class HeuristicMutation implements Mutation{

	@Override
	public Individual apply(Individual origin) {
		
		List<Integer> points = generatePoints(origin.length(),origin.length()/6);
		List<Integer> values = new ArrayList<Integer>(); 
		for(int i=0;i<points.size();i++) {
			values.add(origin.getGene(points.get(i)));
		}
		List<ArrayList<Integer>> permutations = permute(values);
		
		Individual current=new Individual(origin);
		Individual best=current;
		for(ArrayList<Integer> perm : permutations) {
			for(int i=0;i<points.size();i++) {
				current.setGene(points.get(i), perm.get(i));
			}
			current.evaluate();
			if(current.getFitness()<best.getFitness()) {
				best=new Individual(current);
			}
		}
		
		if(!best.valid()) {
			throw new IllegalArgumentException("BUGGY");
		}
		
		return best;
	}
	
	private List<Integer> generatePoints(int max, int number) {
		List<Integer> points=new ArrayList<Integer>();
		for(int i=0; i<number;i++) {
			int point= (int) (Math.random()*max);
			while(points.contains(point)) {
				point= (int) (Math.random()*max);
			}
			points.add(point);
		}
		return points;
	}
	
	public ArrayList<ArrayList<Integer>> permute(List<Integer> num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	 
		//start from an empty list
		result.add(new ArrayList<Integer>());
	 
		for (int i = 0; i < num.size(); i++) {
			//list of list in current iteration of the array num
			ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
	 
			for (ArrayList<Integer> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size()+1; j++) {
					// + add num[i] to different locations
					l.add(j, num.get(i));
	 
					ArrayList<Integer> temp = new ArrayList<Integer>(l);
					current.add(temp);
	 
					//System.out.println(temp);
	 
					// - remove num[i] add
					l.remove(j);
				}
			}
	 
			result = new ArrayList<ArrayList<Integer>>(current);
		}
	 
		return result;
	}

	public String toString() {
		return "Heuristic";
	}
}
