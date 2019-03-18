package model.mutation;

import model.entities.Individual;

public class NoMutation implements Mutation {

	//TODO DOC
	@Override
	public Individual apply(Individual origin) {
		return origin;
	}

}
