package model.cross;

import java.util.Stack;

import model.entities.Individual;
import model.entities.Pair;
import model.misc.GraphNode;
/**
 * A personal implementation of a crossover operator based on ERX.
 * Builds a graph where the nodes are the different cities. 
 * Vertexes are present between the nodes if the nodes are neighbours in the parents.
 * Children are built doing a Depth First Search starting from two random nodes of the graph.
 * @author razvan
 *
 */
public class MyCross implements Cross{

	private int _length;
	
	@Override
	public Pair apply(Individual parent1, Individual parent2) {
		
		_length=parent1.length();
		
		//creating initial graph
		GraphNode[] nodes= new GraphNode[_length];
		for(int i=0; i<_length;i++) {
			nodes[i] = new GraphNode(i);
		}
		
		//linking
		for(int i=0;i<parent1.length();i++) {
			nodes[parent1.getGene(i)].addNeighbour(nodes[parent1.getGene(mod(i-1,_length))]);
			nodes[parent1.getGene(i)].addNeighbour(nodes[parent1.getGene(mod(i+1,_length))]);
			nodes[parent2.getGene(i)].addNeighbour(nodes[parent2.getGene(mod(i-1,_length))]);
			nodes[parent2.getGene(i)].addNeighbour(nodes[parent2.getGene(mod(i+1,_length))]);

		}
		
		//Children are formed doing a DFS from 2 random nodes on this graph
		int index1= (int) (Math.random() * _length);
		int index2= (int) (Math.random() * _length);
		Individual child1=DFS(nodes[index1]);
		Individual child2=DFS(nodes[index2]);
		
		if(!child1.valid()||!child2.valid()) {
			throw new IllegalArgumentException("BAD");
		}
		
		return new Pair(child1,child2);
	}



	/**
	 * Builds a child Individual doing a DFS on our graph
	 * @param graphNode starting node
	 * @return the resulting child
	 */
	private Individual DFS(GraphNode graphNode) {
		Individual result = new Individual(_length);
		int index=0;
		Stack<GraphNode> stack = new Stack<GraphNode>();
		boolean[] visited= new boolean[_length];
		for(int i= 0 ; i< visited.length;i++) {
			visited[i]=false;
		}
		stack.push(graphNode);
		while(!stack.empty()) {
			GraphNode current=stack.pop();
			if(!visited[current.getValue()]) {
				visited[current.getValue()]=true;
				//add in child
				result.setGene(index, current.getValue());
				index++;
				
				
				for(GraphNode neigh : current.getNeighbours()) {
					stack.push(neigh);
				}
			}
			
		}
		return result;
	}

	public String toString() {
		return "My Cross";
	}


	private int mod(int x, int y)
	{
	    int result = x % y;
	    return result < 0? result + y : result;
	}
}
