package main.entities.brain;

import java.util.ArrayList;

public class Neuron {
	private double value = 0;
	private boolean active = false;

	private ArrayList<Integer> outputNeuronIndexes = new ArrayList<Integer>();

	/**
	 * Constructor for the neuron
	 */
	public Neuron() {
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
	 * @return an ArraList of this neuron's output neurons indexes
	 */
	public ArrayList<Integer> getOutputNeuronIndexes() {
		return outputNeuronIndexes;
	}

	/**
	 * Adds an output neuron index to the list of neurons to change when this
	 * neuron's values are changed
	 *
	 * @param outputNeuronIndex outputNeuronIndex the index of the output neurons
	 *                          that should be added
	 */
	public void addOutputNeuronIndex(int outputNeuronIndex) {
		outputNeuronIndexes.add(outputNeuronIndex);
	}

	/**
	 * Remove the index of a given output neuron from the list of neurons to change
	 * when this neuron's values are changed
	 *
	 * @param outputNeuronIndex the index of the output neurons that should be
	 *                          removed
	 */
	public void removeOutputNeuronIndex(int outputNeuronIndex) {
		outputNeuronIndexes.remove(outputNeuronIndex);
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
	public double setValue(double newValue) {
		if (active == true) {
			newValue = (int) Math.round(newValue * Math.pow(10, 1)) / Math.pow(10, 1);
			if (Math.abs(newValue - value) > 0.05) {
				double deltaValue = newValue - value;
				value = newValue;
				return deltaValue;
			}
		}
		return 0;
	}
}
