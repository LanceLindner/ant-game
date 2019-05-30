package main.entities.brain;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import main.main.Globals;

public class Neuron {
	public static final int NEURON_SIZE = 4;
	public static final int NEURON_SPACING = 1;
	public static final int NEURON_OFFSET_X = Globals.windowWidth / 100;
	public static final int NEURON_OFFSET_Y = Globals.windowHeight / 100;

	private static Texture inactiveNeuronTexture = Globals.assetManagerManager.getTexture("inactiveNeuron");
	private static Texture activeNeuronTexture = Globals.assetManagerManager.getTexture("activeNeuron");

	private int x;
	private int y;

	protected int value = 0;
	protected boolean active = true;
	protected int numberOfInputs;

	private ArrayList<int[]> outputNeuronPositions = new ArrayList<int[]>();

	/**
	 * Constructor for Neuron
	 */
	public Neuron(int x, int y) {
		this.x = x;
		this.y = y;
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
	 * @return an ArrayList of this neuron's output neuron positions in the brain
	 */
	public ArrayList<int[]> getOutputNeuronPositions() {
		return outputNeuronPositions;
	}

	/**
	 * Adds an output neuron position to the list of neurons so that the neuron can
	 * be changed when this neuron's values are changed and increments the number of
	 * inputs counter
	 *
	 * @param outputNeuronX the x position of the output neuron that should be added
	 * @param outputNeuronY the y position of the output neuron that should be added
	 */
	public void addOutputNeuronPosition(int outputNeuronX, int outputNeuronY) {
		outputNeuronPositions.add(new int[] { outputNeuronX, outputNeuronY });
		++numberOfInputs;
	}

	/**
	 * Remove an output neuron position from the list of this neurons positions and
	 * decrements the number of inputs counter
	 *
	 * @param outputNeuronX the x position of the output neuron that should be
	 *                      removed
	 * @param outputNeuronY the y position of the output neuron that should be
	 *                      removed
	 */
	public void removeOutputNeuronPosition(int outputNeuronX, int outputNeuronY) {
		outputNeuronPositions.remove(new int[] { outputNeuronX, outputNeuronY });
		--numberOfInputs;
	}

	/**
	 * If this neuron is active and there is a difference between the new and old
	 * values, this neurons value is set to the parameter's value and the difference
	 * is returned so that the brain can change all output neurons. This should only
	 * be called for input neurons
	 *
	 * @param newValue the value that this neuron's value will be set to if their is
	 *                 a difference between the two
	 * @return how much all output neurons should change
	 */
	public int setValue(int newValue) {
		if (active == true) {
			if (newValue != value) {
				int deltaValue = newValue - value;
				value = newValue;
				return deltaValue;
			}
		}
		return 0;
	}

	/**
	 * Adds the parameter to this neuron's value. This should be called only by
	 * non-input neurons
	 *
	 * @param difference the parameter to be added
	 */
	public void changeValue(int difference) {
		value += difference;
	}

	public void draw(SpriteBatch batch, int entityOffsetX, int entityOffsetY) {
		Texture texture;
		if (value > 0.5) {
			texture = activeNeuronTexture;
		} else {
			texture = inactiveNeuronTexture;
		}
		batch.draw(texture, x * (NEURON_SIZE + NEURON_SPACING) + NEURON_OFFSET_X + entityOffsetX,
				y * (NEURON_SIZE + NEURON_SPACING) + NEURON_OFFSET_Y + entityOffsetY);
	}
}
