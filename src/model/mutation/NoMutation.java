package model.mutation;

import model.entities.Population;

public class NoMutation implements Mutation {

	//TODO DOC
	@Override
	public Population apply(Population origin, double chance) {
		return origin;
	}

}
