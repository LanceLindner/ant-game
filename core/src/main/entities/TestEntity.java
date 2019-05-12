package main.entities;

public class TestEntity extends Entity {
	public TestEntity(int x, int y) {
		super(x, y);
	}

	@Override
	public void move() {
		x += (int) (Math.random() * 20.0 - 10.0);
		y += (int) (Math.random() * 20.0 - 10.0);
	}
}
