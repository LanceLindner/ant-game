package main.entities.brain;

public class ConjunctiveNeuron extends Neuron {
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
