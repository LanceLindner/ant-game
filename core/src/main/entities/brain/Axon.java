package main.entities.brain;

public class Axon {
	private Neuron inputNeuron;
	private Neuron outputNeuron;

	public Axon(Neuron inputNeuron, Neuron outputNeuron) {
		this.inputNeuron = inputNeuron;
		this.outputNeuron = outputNeuron;
	}

	public void delete() {
		inputNeuron.removeOutputAxon(this);
		outputNeuron.removeInputAxon(this);
	}

	public void setOutputNeuronValue(double value) {
		outputNeuron.changeValue(value);
	}
}
