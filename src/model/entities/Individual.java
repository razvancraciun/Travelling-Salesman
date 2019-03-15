package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.Distances;

public class Individual  {
	
	private int[] _genes;
	private int _fitness;
	
	/**Constructor: generates an array that starts with 0, following with a random permutation of 
	 * the numbers (1..size-1)
	 * @size: the size of the gene array to be constructed
	 */
	public Individual(int size) {
		if(size<1) {
			throw new IllegalArgumentException("Individual size must be at least 1");
		}
		_genes=new int[size];
		List<Integer> helper=new ArrayList<Integer>();
		for(int i=1;i<_genes.length;i++) {
			helper.add(i);
		}
		
		_genes[0]=0;
		for(int i=1;i<_genes.length;i++) {
			_genes[i]=getRandomNumber(helper);
		}
	}
	
	/** Copy constructor */
	public Individual(Individual other) {
		_genes=new int[other.length()];
		for(int i=0;i<_genes.length;i++) {
			_genes[i]=other.getGene(i);
		}
		_fitness=other.getFitness();
	}
	
	
	/**Adds distances, including from the last city to the first one*/
	public void evaluate() {
		Distances dist=Distances.getInstance();
		int result=0;
		for(int i=1;i<_genes.length;i++) {
			result+=dist.getDistance(_genes[i], _genes[i-1]);
		}
		result+=dist.getDistance(_genes.length-1, 0);
		_fitness=result;
	}
	
	
	/**returns a random Integer form a list of Integers
	 * Note:this maybe should not be here
	 * */
	private int getRandomNumber(List<Integer> list) {
		int index=(int) (Math.random()*list.size());
		int nr=list.get(index);
		list.remove(index);
		return nr;
	}
	
	/** Checks if the Individual's genes are a permutation of numbers from 0 to _genes.length-1
	 */
	public boolean valid() {
		int sum=0;
		for(int i=0;i<_genes[i];i++) {
			sum+=i;
			sum-=_genes[i];
		}
		return sum==0;
	}
	
	public String toString() {
		String result="";
		for(int i=0;i<_genes.length;i++) {
			result+=_genes[i]+"-";
		}
		result+=">"+_fitness;
		return result;
	}
	
	
	//GETTERS AND SETTERS
	public int getGene(int index) {
		return _genes[index];
	}
	public void setGene(int index, int value) {
		_genes[index]=value;
	}
	public int length() {
		return _genes.length;
	}
	public int getFitness() {
		return _fitness;
	}
	
	/** Checks if the _genes array contains a particular value */
	public boolean hasGene(int value) {
		for(int i=0;i<_genes.length;i++) {
			if(_genes[i]==value)
				return true;
		}
		return false;
	}

	public int getGeneIndex(int value) {
		for(int i=0;i<_genes.length;i++) {
			if(_genes[i]==value)
				return i;
		}
		throw new IllegalArgumentException("Gene not found");
	}

}
