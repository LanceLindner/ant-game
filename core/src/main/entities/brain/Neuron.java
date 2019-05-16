package main.entities.brain;

import java.util.ArrayList;

public class Neuron {
	private double value = 0;
	private boolean active = false;

	private ArrayList<Integer> outputNeuronIndexes = new ArrayList<Integer>();

	private double x;
	private double y;

	public Neuron(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double getValue() {
		if (value > 1)
			return 1;
		else if (value < 0)
			return 0;
		else
			return value;
	}

	public boolean isActive() {
		return active;
	}

	public ArrayList<Integer> getOutputNeuronIndexes() {
		return outputNeuronIndexes;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
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
