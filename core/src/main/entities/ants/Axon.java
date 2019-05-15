package main.entities.ants;

public class Axon {
	public Neuron inputNeuron;
	public Neuron outputNeuron;

	public double weight;

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
		inputNeuron.outputAxons.remove(this);
		outputNeuron.inputAxons.remove(this);
	}

	public void randomizeWeight() {
		weight = (int) Math.round((Math.random() * 2 - 1) * Math.pow(10, 1)) / Math.pow(10, 1);
	}

	public void setOutputNeuronValue(double value) {
		outputNeuron.changeValue(value * weight);
	}
}
