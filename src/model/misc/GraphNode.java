package model.misc;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {

	private int _value;
	private List<GraphNode> _neighbours;
	
	public GraphNode(int value) {
		_value=value;
		_neighbours=new ArrayList<GraphNode>();
	}

	public int getValue() {
		return _value;
	}

	public void setValue(int _value) {
		this._value = _value;
	}
	
	public List<GraphNode> getNeighbours() {
		return _neighbours;
	}
	
	public void addNeighbour(GraphNode n) {
		for(GraphNode g : _neighbours) {
			if(n.equals(g)) {
				return;
			}
		}
		_neighbours.add(n);
	}
	
	public void removeNeighbour(GraphNode n) {
		_neighbours.remove(n);
	}
	
	public String toString() {
		String result= ""+_value+"->";
		for(GraphNode g : _neighbours) {
			result +=g.getValue()+" ";
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GraphNode other = (GraphNode) obj;
		if (_value != other._value)
			return false;
		return true;
	}
	
}
