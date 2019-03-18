package model.mutation;

import model.entities.Individual;

public interface Mutation {
	public Individual apply(Individual origin);
	public String toString();
}
