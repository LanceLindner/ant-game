package main.entities.brain;

import java.util.ArrayList;

public class Neuron {
	private double value = 0;
	private boolean active = false;

	private ArrayList<Axon> inputAxons = new ArrayList<Axon>();
	private ArrayList<Axon> outputAxons = new ArrayList<Axon>();

	private double x;
	private double y;

	private Neuron childNeuron;// used for cloning

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

	public boolean isActive() {
		return active;
	}

	public ArrayList<Axon> getInputAxons() {
		return inputAxons;
	}

	public ArrayList<Axon> getOutputAxons() {
		return outputAxons;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Neuron getChildNeuron() {
		return childNeuron;
	}

	public void removeInputAxon(Axon axon) {
		inputAxons.remove(axon);
	}

	public void removeOutputAxon(Axon axon) {
		outputAxons.remove(axon);
	}

	private void updateOutputAxons(double deltaValue) {
		for (Axon axon : outputAxons) {
			axon.setOutputNeuronValue(deltaValue);
		}
	}

	public void setValue(double newValue) {
		if (active == true) {
			newValue = (int) Math.round(newValue * Math.pow(10, 1)) / Math.pow(10, 1);
			if (Math.abs(newValue - value) > 0.05) {
				double deltaValue = newValue - value;
				value = newValue;
				updateOutputAxons(deltaValue);
			}
		}
	}

	public void changeValue(double deltaValue) {
		value += deltaValue;
		updateOutputAxons(deltaValue);
	}
}
