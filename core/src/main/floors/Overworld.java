package main.floors;

import main.entities.TestEntity;
import main.entities.ants.Ant;

public class Overworld extends Floor {
	public Overworld() {
		super("overworld");

		entities.add(new TestEntity(this, 255, 255));
		for (int i = 0; i < 10; i += 3)
			for (int j = 0; j < 10; j += 3) {
				entities.add(new Ant(this, 245 + i, 245 + j, (int) (Math.random() * 4)));
			}
	}
}
