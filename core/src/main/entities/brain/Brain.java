package main.entities.brain;

import java.util.ArrayList;

public class Brain {
	/**
	 * An arrayList of arrays containing all of the neurons in this brain. Note that
	 * the first array, index 0, contains input neurons. The second, index 1,
	 * contains hidden neurons. The third, index 2, contains output neurons
	 */
	private ArrayList<Neuron[]> neurons = new ArrayList<Neuron[]>();

	/**
	 * Constructor for the brain.
	 *
	 * @param numberOfInputNeurons  the number of input neurons that should be
	 *                              created
	 * @param numberOfHiddenNeurons the number of hidden neurons that should be
	 *                              created
	 * @param numberOfOutputNeurons the number of output neurons that should be
	 *                              created
	 */
	public Brain(int numberOfInputNeurons, int numberOfHiddenNeurons, int numberOfOutputNeurons) {
		neurons.add(new Neuron[numberOfInputNeurons]);
		neurons.add(new Neuron[numberOfHiddenNeurons]);
		neurons.add(new Neuron[numberOfOutputNeurons]);

		for (int i = 0; i < neurons.size(); ++i) {
			for (int j = 0; j < neurons.get(i).length; ++j) {
				boolean conjunctive;
				if (j == 1)
					neurons.get(i)[j] = new ConjunctiveNeuron();
				else
					neurons.get(i)[j] = new Neuron();

			}
		}
	}

	/**
	 * Connects two random neurons that fit the required conditions a number of
	 * times specified by the parameter
	 * 
	 * @param numberOfAxons the number of times to connect two random neurons
	 */
	public void addRandomAxons(int numberOfAxons) {
		for (int i = 0; i < numberOfAxons; ++i)
			addRandomAxon();
	}

	/**
	 * Connects two random neurons that fit the required conditions
	 */
	public void addRandomAxon() {
		int inputNeuronY = (int) (Math.random() * 2);
		int outputNeuronY = (int) (Math.random() * (2 - inputNeuronY) + 1 + inputNeuronY);

		int inputNeuronX = (int) (Math.random() * neurons.get(inputNeuronY).length);
		int outputNeuronX = (int) (Math.random() * neurons.get(outputNeuronY).length);

		addAxon(inputNeuronX, inputNeuronY, outputNeuronX, outputNeuronY);
	}

	/**
	 * Connects an output neuron to an input neuron by x and y coordinates in the
	 * brain
	 *
	 * @param inputNeuronX    the input neuron's x position
	 * @param inputNeuronYthe input neuron's y position
	 * @param outputNeuronX   the output neuron's x position
	 * @param outputNeuronY   the output neuron's y position
	 */
	public void addAxon(int inputNeuronX, int inputNeuronY, int outputNeuronX, int outputNeuronY) {
		neurons.get(inputNeuronY)[inputNeuronX].addOutputNeuronPosition(outputNeuronX, outputNeuronY);
	}

	/**
	 * Removes a connection between an output neuron and an input neuron by their x
	 * and y coordinates in the brain
	 *
	 * @param inputNeuronX  the input neuron's x position
	 * @param inputNeuronY  the input neuron's y position
	 * @param outputNeuronX the output neuron's x position
	 * @param outputNeuronY the output neuron's y position
	 */
	public void removeAxon(int inputNeuronX, int inputNeuronY, int outputNeuronX, int outputNeuronY) {
		neurons.get(inputNeuronY)[inputNeuronX].removeOutputNeuronPosition(outputNeuronX, outputNeuronY);
	}

	/**
	 * Sets input neuron values and updates output accordingly
	 *
	 * @param inputValues an array of input values that overwrites old input neuron
	 *                    values
	 */
	public void update(int[] inputValues) {
		for (int i = 0; i < neurons.get(0).length; ++i) {
			int deltaValue = neurons.get(0)[i].setValue(inputValues[i]);
			if (deltaValue != 0) {
				update(neurons.get(0)[i].getOutputNeuronPositions(), deltaValue);
			}
		}
	}

	private void update(ArrayList<int[]> outputNeuronPositions, int deltaValue) {
		for (int i = 0; i < outputNeuronPositions.size(); ++i) {
			neurons.get(outputNeuronPositions.get(i)[1])[outputNeuronPositions.get(i)[0]].changeValue(deltaValue);
			update(neurons.get(outputNeuronPositions.get(i)[1])[outputNeuronPositions.get(i)[0]]
					.getOutputNeuronPositions(), deltaValue);
		}
	}

	/**
	 * Returns the values of the output neurons
	 *
	 * @return an array of the values contained by all of the output neurons
	 */
	public int[] getOutput() {
		int[] outputValues = new int[neurons.get(2).length];
		for (int i = 0; i < neurons.get(2).length; ++i) {
			outputValues[i] = (int) Math.round(neurons.get(2)[i].getValue());
		}
		return outputValues;
	}

}
