package main.persistance;

import java.util.ArrayList;

import main.entities.brain.Brain;
import main.main.Globals;

public class SaveData {
	private ArrayList<boolean[]> unlockedNeurons = new ArrayList<boolean[]>();
	private ArrayList<Brain> savedBrains = new ArrayList<Brain>();

	public SaveData() {
		unlockedNeurons.add(new boolean[Globals.NUMBER_OF_INPUT_NEURONS]);
		unlockedNeurons.add(new boolean[Globals.NUMBER_OF_HIDDEN_NEURONS]);
		unlockedNeurons.add(new boolean[Globals.NUMBER_OF_OUTPUT_NEURONS]);

		for (int i = 0; i < unlockedNeurons.size(); ++i) {
			for (int j = 0; j < unlockedNeurons.get(i).length; ++j) {
				unlockedNeurons.get(i)[j] = false;
			}
		}
		savedBrains = new ArrayList<Brain>();
	}

	public void unlockNeuron(int x, int y) {
		unlockedNeurons.get(y)[x] = true;
	}

	public void saveBrain(Brain Brain) {
		savedBrains.add(Brain);
	}

	public void deleteBrain(Brain Brain) {
		savedBrains.remove(Brain);
	}
}
