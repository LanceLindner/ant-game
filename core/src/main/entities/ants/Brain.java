package main.entities.ants;

public class Brain {

	private int[] input;
	private int[] output;

	public Brain(int input, int output) {
		this.input = new int[input];
		this.output = new int[output];
	}

	public void update(int[] input) {

	}

	protected int[] getOutput() {
		return output;
	}
}
