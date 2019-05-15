package main.entities;

public class TestEntity extends Entity {
	public TestEntity(int x, int y) {
		super(x, y);
	}

	@Override
	public void update() {
		x += Math.random() * 1.0 - 0.5;
		y += Math.random() * 1.0 - 0.5;
	}
}
