package main.entities.brain;

public class ConjunctiveNeuron extends Neuron {

	public ConjunctiveNeuron(int x, int y) {
		super(x, y);
	}

	/**
	 * Returns 1 if all of the input neurons are excited
	 */
	@Override
	public double getValue() {
		if (value == numberOfInputs)
			return 1;
		else
			return 0;
	}
}
