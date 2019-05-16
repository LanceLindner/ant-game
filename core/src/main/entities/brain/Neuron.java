package main.entities.brain;

import java.util.ArrayList;

public class Neuron {
	private double value = 0;
	private boolean active = false;

	private ArrayList<Integer> outputNeuronIndexes = new ArrayList<Integer>();

	public Neuron() {
	}

	public double getValue() {
		if (value > 1)
			return 1;
		else if (value < 0)
			return 0;
		else
			return value;
	}

	public ArrayList<Integer> getOutputNeuronIndexes() {
		return outputNeuronIndexes;
	}

	public void addOutputNeuronIndex(int axonIndex) {
		outputNeuronIndexes.add(axonIndex);
	}

	public void removeOutputNeuronIndex(int axonIndex) {
		outputNeuronIndexes.remove(axonIndex);
	}

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
