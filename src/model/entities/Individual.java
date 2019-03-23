package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.misc.Distances;

public class Individual  {
	
	private List<Integer> _genes;
	private int _fitness;
	
	/**Constructor: generates an array  with a random permutation of 
	 * the numbers (0..size-1)
	 * @size: the size of the gene array to be constructed
	 */
	public Individual(int size) {
		if(size<1) {
			throw new IllegalArgumentException("Individual size must be at least 1");
		}
		_genes=new ArrayList<Integer>(size);
		List<Integer> helper=new ArrayList<Integer>();
		for(int i=0;i<size;i++) {
			helper.add(i);
		}
		
		for(int i=0;i<size;i++) {
			_genes.add(getRandomNumber(helper));
		}
	}
	
	/** Copy constructor */
	public Individual(Individual other) {
		_genes=new ArrayList<Integer>(other.length());
		for(int i=0;i<other.length();i++) {
			_genes.add(other.getGene(i));
		}
		_fitness=other.getFitness();
	}
	
	
	/**Adds distances, including from the last city to the first one*/
	public void evaluate() {
		Distances dist=Distances.getInstance();
		int result=0;
		for(int i=1;i<_genes.size();i++) {
			result+=dist.getDistance(_genes.get(i), _genes.get(i-1));
		}
		result+=dist.getDistance(_genes.size()-1, _genes.get(0));
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
		for(int i=0;i<_genes.size();i++) {
			sum+=i;
			sum-=_genes.get(i);
		}
		return sum==0;
	}
	
	public String toString() {
		String result="";
		for(int i=0;i<_genes.size();i++) {
			result+=_genes.get(i)+"||";
		}
		result+=">"+_fitness;
		return result;
	}
	
	
	//GETTERS AND SETTERS
	public int getGene(int index) {
		if(index<0|| index>=_genes.size()) {
			throw new IndexOutOfBoundsException(" gene index not in array: "+ index);
		}
		return _genes.get(index);
	}
	public void setGene(int index, int value) {
		_genes.set(index,value);
	}
	public int length() {
		return _genes.size();
	}
	public int getFitness() {
		return _fitness;
	}
	
	/** Checks if the _genes array contains a particular value */
	public boolean hasGene(int value) {
		for(int i=0;i<_genes.size();i++) {
			if(_genes.get(i)==value)
				return true;
		}
		return false;
	}

	public int getGeneIndex(int value) {
		for(int i=0;i<_genes.size();i++) {
			if(_genes.get(i)==value)
				return i;
		}
		throw new IllegalArgumentException("Gene not found");
	}
	
	public boolean hasValue(int value) {
		for(int i=0;i<_genes.size();i++) {
			if(_genes.get(i)==value) {
				return true;
			}
		}
		return false;
	}
	
	public void removeAtIndex(int index) {
		_genes.remove(index);
	}
	public void addAtIndex(int index,int value) {
		_genes.add(index, value);
	}
}
