package main.floors;

import main.entities.TestEntity;
import main.entities.ants.Ant;

public class Overworld extends Floor {
	public Overworld() {
		super("overworld");

		entities.add(new TestEntity(this, 505, 505));
		for (int i = 0; i < 10; i += 2)
			for (int j = 0; j < 10; j += 2) {
				entities.add(new Ant(this, 490 + i, 490 + j, (int) (Math.random() * 4)));
			}
	}

}
