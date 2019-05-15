package main.entities.ants;

public class Axon {
	private Neuron inputNeuron;
	private Neuron outputNeuron;

	private double weight;

	public Axon(Neuron inputNeuron, Neuron outputNeuron) {
		this.inputNeuron = inputNeuron;
		this.outputNeuron = outputNeuron;
		randomizeWeight();
	}

	public Axon(Neuron inputNeuron, Neuron outputNeuron, double weight) {
		this.inputNeuron = inputNeuron;
		this.outputNeuron = outputNeuron;
		this.weight = weight;
	}

	public void delete() {
		inputNeuron.removeOutputAxon(this);
		outputNeuron.removeInputAxon(this);
	}

	public void randomizeWeight() {
		weight = (int) Math.round((Math.random() * 2 - 1) * Math.pow(10, 1)) / Math.pow(10, 1);
	}

	public void setOutputNeuronValue(double value) {
		outputNeuron.changeValue(value * weight);
	}
}
