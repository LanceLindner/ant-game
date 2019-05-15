package main.entities.brain;

import java.util.ArrayList;

import main.entities.ants.Neuron;

public class Brain {
	private Neuron[] input;
	private Neuron[] hidden;
	private Neuron[] output;

	public ArrayList<Neuron> allNeurons = new ArrayList<Neuron>();
	public ArrayList<Neuron> activeInput = new ArrayList<Neuron>();

	public Brain(int numberOfInputNeurons, int numberOfHiddenNeurons, int numberOfOutputNeurons) {
		initializeEmptyBrain(numberOfInputNeurons, numberOfHiddenNeurons, numberOfOutputNeurons);
	}

	public void update(int[] input) {

	}

	public void initializeEmptyBrain(int numberOfInputNeurons, int numberOfHiddenNeurons, int numberOfOutputNeurons) {
		input = new Neuron[numberOfInputNeurons];
		hidden = new Neuron[numberOfHiddenNeurons];
		output = new Neuron[numberOfOutputNeurons];

		for (Neuron neuron : input) {
			neuron = new Neuron();
			allNeurons.add(neuron);
		}
		for (Neuron neuron : hidden) {
			neuron = new Neuron();
			allNeurons.add(neuron);
		}
		for (Neuron neuron : output) {
			neuron = new Neuron();
			allNeurons.add(neuron);
		}
	}

	public int[] getOutput() {
		return null;
	}
}
