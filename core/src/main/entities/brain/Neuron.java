package main.entities.brain;

import java.util.ArrayList;

public class Neuron {
	private int value = 0;
	private boolean active = true;

	private boolean conjunctive = false;

	private ArrayList<int[]> outputNeuronPositions = new ArrayList<int[]>();

	/**
	 * Constructor for Neuron
	 *
	 * @param conjunctive boolean that controls whether this neuron calculates
	 *                    conjunctively (with multiplication) or disjunctively (with
	 *                    addition)
	 */
	public Neuron(boolean conjunctive) {
		this.conjunctive = conjunctive;
	}

	/**
	 * Returns this neuron's value, adjusted to fit within the bounds of 0 and 1.
	 * These bounds are present because the actual value needs a larger range.
	 *
	 * @return this neuron's value, adjusted to fit within the bounds of 0 and 1
	 */
	public double getValue() {
		if (value > 1)
			return 1;
		else if (value < 0)
			return 0;
		else
			return value;
	}

	/**
	 * Returns this neuron's ArrayList of output neuron indexes. This should be
	 * called by the brain when this neuron's setValue() method returns a value that
	 * indicated the output neurons need to be updated
	 *
	 * @return an ArrayList of this neuron's output neuron positions in the brain
	 */
	public ArrayList<int[]> getOutputNeuronPositions() {
		return outputNeuronPositions;
	}

	/**
	 * Adds an output neuron position to the list of neurons to change when this
	 * neuron's values are changed
	 *
	 * @param outputNeuronX the x position of the output neuron that should be added
	 * @param outputNeuronY the y position of the output neuron that should be added
	 */
	public void addOutputNeuronPosition(int outputNeuronX, int outputNeuronY) {
		outputNeuronPositions.add(new int[] { outputNeuronX, outputNeuronY });
	}

	/**
	 * Remove the index of a given output neuron from the list of neurons to change
	 * when this neuron's values are changed
	 *
	 * @param outputNeuronX the x position of the output neuron that should be
	 *                      removed
	 * @param outputNeuronY the y position of the output neuron that should be
	 *                      removed
	 */
	public void removeOutputNeuronPosition(int outputNeuronX, int outputNeuronY) {
		outputNeuronPositions.remove(new int[] { outputNeuronX, outputNeuronY });
	}

	/**
	 * If this neuron is active and the difference between the two is greater than
	 * 10%, this neurons value is set to the parameter's value and the difference is
	 * returned so that the brain can change all output neurons
	 *
	 * @param newValue the value that this neuron's value will be set to if the
	 *                 difference between the two is greater than 10%
	 * @return how much all output neurons should change
	 */
	public int setValue(int newValue) {
		if (active == true) {
			if (newValue != value) {
				int deltaValue = newValue - value;
				value = newValue;
				return deltaValue;
			}
		}
		return 0;
	}
}
