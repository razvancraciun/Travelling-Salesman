package model.cross;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.entities.Individual;
import model.entities.Pair;


/**
 * This is a implementation of Ordinal Coding crossover operator
 */
public class OrdinalCodingCross implements Cross{

	@Override
	public Pair apply(Individual parent1, Individual parent2) {
		
		Individual child1=new Individual(parent1.length());		//Switching
		Individual child2=new Individual(parent1.length());
		
		List<Integer> helper=getHelper(parent1.length());
		
		//Filling in coding of parent1
		List<Integer> parent1Coding= new ArrayList<Integer>();
		for(int i=0;i<parent1.length();i++) {
			parent1Coding.add(helper.indexOf(parent1.getGene(i)));
			helper.remove(helper.indexOf(parent1.getGene(i)));
		}
		
		//Filling in coding of parent2
		helper=getHelper(parent2.length());
		List<Integer> parent2Coding= new ArrayList<Integer>();
		for(int i=0;i<parent2.length();i++) {
			parent2Coding.add(helper.indexOf(parent2.getGene(i)));
			helper.remove(helper.indexOf(parent2.getGene(i)));
		}
		
		
		//Generating coding for children
		List<Integer> child1Coding=new ArrayList<Integer>();
		List<Integer> child2Coding=new ArrayList<Integer>();
		for(int i=0;i<parent1Coding.size();i++) {
			if(i<parent1Coding.size()/2) {
				child1Coding.add(parent1Coding.get(i));
				child2Coding.add(parent2Coding.get(i));
			}
			else {
				child1Coding.add(parent2Coding.get(i));
				child2Coding.add(parent1Coding.get(i));
			}
		}
		
		//Creating children
		helper=getHelper(child1.length());
		for(int i=0;i<child1.length();i++) {
			child1.setGene(i, helper.get(child1Coding.get(i)));
			helper.remove( (int)child1Coding.get(i));
		}
		helper=getHelper(child2.length());
		for(int i=0;i<child2.length();i++) {
			child2.setGene(i, helper.get(child2Coding.get(i)));
			helper.remove( (int)child2Coding.get(i));
		}
		
		return new Pair(child1,child2);
	}
	
	/**
	 * Creating and populating the helper list
	 * @param length the length of the list
	 * @return a list containing integers from 0 to length in order
	 */
	private List<Integer> getHelper(int length) {
		List<Integer> helper = new LinkedList<Integer>();
		for(int i=0;i<length;i++) {
			helper.add(i);
		}
		return helper;
	}
	
	
	public String toString() {
		return "Ordinal Coding";
	}
}
