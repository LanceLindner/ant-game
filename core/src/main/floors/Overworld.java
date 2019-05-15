package main.floors;

import main.entities.TestEntity;
import main.entities.ants.Ant;

public class Overworld extends Floor {
	public Overworld() {
		super("overworld");

		entities.add(new TestEntity(this, 505, 505));
		entities.add(new Ant(this, 495, 495));
	}

}
