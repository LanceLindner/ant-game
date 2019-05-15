package main.entities.brain;

public class Brain {

	private int[] input;
	private int[] output;

	public Brain(int input, int output) {
		this.input = new int[input];
		this.output = new int[output];
	}

	public void update(int[] input) {

	}

	public int[] getOutput() {
		return output;
	}
}
