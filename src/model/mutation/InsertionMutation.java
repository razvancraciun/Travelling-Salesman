package model.mutation;

import model.entities.Individual;

public class InsertionMutation implements Mutation{

	@Override
	public Individual apply(Individual origin) {
		return origin;
	}

}
