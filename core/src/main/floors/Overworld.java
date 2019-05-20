package main.floors;

import main.entities.TestEntity;
import main.entities.ants.Ant;

public class Overworld extends Floor {
	public Overworld() {
		super("overworld");

		entities.add(new TestEntity(this, 505, 505));
		for (int i = 0; i < 20; i += 2)
			for (int j = 0; j < 20; j += 2) {
				entities.add(new Ant(this, 495 + i, 495 + j, 3));
			}
	}

}
