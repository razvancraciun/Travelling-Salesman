package model.mutation;

import model.entities.Population;

public interface Mutation {
	public Population apply(Population origin,double chance);
}
