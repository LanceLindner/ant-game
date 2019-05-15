package main.entities.ants;

import java.util.ArrayList;

public class Neuron {
	public double value = 0;
	public boolean active = false;

	public ArrayList<Axon> inputAxons = new ArrayList<Axon>();
	public ArrayList<Axon> outputAxons = new ArrayList<Axon>();

	public double x;
	public double y;

	public Neuron childNeuron;// used for cloning

	public Neuron() {
	}

	public void delete() {
		for (Axon axon : inputAxons) {
			axon.delete();
		}
		for (Axon axon : outputAxons) {
			axon.delete();
		}
	}

	public double getValue() {
		if (value > 1)
			return 1;
		else if (value < 0)
			return 0;
		else
			return value;

	}

	private void updateOutputAxons(double deltaValue) {
		for (Axon axon : outputAxons) {
			axon.setOutputNeuronValue(deltaValue);
		}
	}

	public void setValue(double newValue) {
		newValue = (int) Math.round(newValue * Math.pow(10, 1)) / Math.pow(10, 1);
		if (Math.abs(newValue - value) > 0.05) {
			double deltaValue = newValue - value;
			value = newValue;
			updateOutputAxons(deltaValue);
		}
	}

	public void changeValue(double deltaValue) {
		value += deltaValue;
		updateOutputAxons(deltaValue);
	}
}
