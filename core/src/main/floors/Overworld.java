package main.floors;

import main.entities.TestEntity;
import main.entities.ants.Ant;

public class Overworld extends Floor {
	public Overworld() {
		super("overworld");

		entities.add(new TestEntity(this, 505, 505));
		for (int i = 0; i < 1000; ++i)
			entities.add(new Ant(this, 495, 495, (int) (Math.random() * 4)));
	}

}
