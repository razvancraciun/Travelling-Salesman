package model.entities;

public class Pair {
	private Individual _first;
	private Individual _second;
	
	//TODO DOC
	public Pair(Individual first, Individual second) {
		if(first==null || second==null) {
			throw new NullPointerException("Trying to make pair with null Individual");
		}
		_first=first;
		_second=second;
	}
	

	//GETTERS AND SETTERS
	public Individual getFirst() {
		return _first;
	}
	public Individual getSecond() {
		return _second;
	}
}
