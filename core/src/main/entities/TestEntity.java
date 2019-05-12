package main.entities;

public class TestEntity extends Entity {
	public TestEntity() {
		super();
	}

	@Override
	public void update() {
		x += (int) (Math.random() * 20.0 - 10.0);
		y += (int) (Math.random() * 20.0 - 10.0);
	}
}
