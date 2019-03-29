package model.cross;

import java.util.ArrayList;
import java.util.List;

import model.entities.Individual;
import model.entities.Pair;

/**
 * An Edge Recombination Crossover (ERX) implementation
 */
public class ERXCross implements Cross{

	private Individual parent1;
	private Individual parent2;
	
	@Override
	public Pair apply(Individual parent1, Individual parent2) {
		this.parent1=parent1;
		this.parent2=parent2;
		
		Individual child1=new Individual(parent1.length());
		Individual child2=new Individual(parent1.length());
		//init children
		initChild(child1);
		initChild(child2);
		
		//init list
		List<List<Integer>> neighbours=new ArrayList<List<Integer>>();
		for(int i=0;i<parent1.length();i++) {
			neighbours.add(new ArrayList<Integer>());
		}
		
		//adding neighbours
		addNeighbours(neighbours);
		
		
		generateChild(child1,neighbours,parent1);
		
		neighbours=new ArrayList<List<Integer>>();
		for(int i=0;i<parent1.length();i++) {
			neighbours.add(new ArrayList<Integer>());
		}
		addNeighbours(neighbours);
		generateChild(child2,neighbours,parent2);
		
		
		if(!child1.valid()||!child2.valid()) {
			throw new IllegalArgumentException("BAD");
		}

		return new Pair(child1,child2);
	}
	
	private Individual generateChild(Individual child, List<List<Integer>> neighbours, Individual parent) {
		int value=parent.getGene(0);
		child.setGene(0, value);
		removeFromAll(neighbours,value);
		for(int i=1;i<child.length();i++) {
			//if no neighbours in the list get a random value not in the child already
			if(neighbours.get(i).isEmpty()) {
				value=parent.getGene((int)(Math.random()*parent.length()));
				while(child.hasValue(value)) {
					value=parent.getGene((int)(Math.random()*parent.length()));
				}
				if(child.hasValue(value)) {
					System.out.println("fist "+value);
				}
			}
			//else get the neighbour whith the smallest list
			else {
				int chosen=-1;
				int min=Integer.MAX_VALUE;
				for(int j=0;j<neighbours.get(i).size();j++) {
					int current=neighbours.get(i).get(j);
					if(neighbours.get(current).size()<min) {
						chosen=current;
						min=neighbours.get(current).size();
					}
				}
				value=chosen;
				if(child.hasValue(value)) {
					System.out.println("second "+value);
				}
			}
			child.setGene(i, value);
			removeFromAll(neighbours,value);
		}
		return null;
	}

	private void addNeighbours(List<List<Integer>> neighbours) {
		for(int i=0;i<parent1.length();i++) {
			if(!neighbours.get(i).contains(parent1.getGene(mod(i-1,parent1.length())))) {
				neighbours.get(i).add(parent1.getGene(mod(i-1,parent1.length())));
			}
			if(!neighbours.get(i).contains(parent1.getGene(mod(i+1,parent1.length())))) {
				neighbours.get(i).add(parent1.getGene(mod(i+1,parent1.length())));
			}
		
			int index= parent2.getGeneIndex(parent1.getGene(i));
			if(!neighbours.get(i).contains(parent2.getGene(mod(index-1,parent2.length())))) {
				neighbours.get(i).add(parent2.getGene(mod(index-1,parent2.length())));
			}
			if(!neighbours.get(i).contains(parent2.getGene(mod(index+1,parent2.length())))) {
				neighbours.get(i).add(parent2.getGene(mod(index+1,parent2.length())));
			}
			
		}
		
	}

	private void removeFromAll(List<List<Integer>> neighbours, int value) {
		for(List<Integer> neigh : neighbours) {
			for(int i=0;i<neigh.size();i++) {
				if(neigh.get(i)==value) {
					neigh.remove(i);
				}
			}
		}
	}

	private int mod(int x, int y)
	{
	    int result = x % y;
	    return result < 0? result + y : result;
	}
	
	private void initChild(Individual child) {
		for(int i=0;i<child.length();i++) {
			child.setGene(i, -1);
		}
	}
	
	public String toString() {
		return "Edge Recombination";
	}
}
