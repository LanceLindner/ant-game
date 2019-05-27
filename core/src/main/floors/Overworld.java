package main.floors;

import main.entities.TestEntity;
import main.entities.ants.Ant;

public class Overworld extends Floor {
	public Overworld() {
		super("overworld");

		entities.add(new TestEntity(this, 255, 255));
		for (int i = 0; i < 4; i += 2) {
			for (int j = 0; j < 4; j += 2) {
				entities.add(new Ant(this, 250 + i, 250 + j, (int) (Math.random() * 4)));
			}
		}
	}
}
