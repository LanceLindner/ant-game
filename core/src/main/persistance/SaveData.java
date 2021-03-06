package main.persistance;

import java.util.ArrayList;

import main.entities.brain.Brain;
import main.main.Globals;

public class SaveData {
	// private boolean[] unlockedInputTiles = new
	// boolean[Globals.NUMBER_OF_VISIBLE_TILES];
	// private boolean[] unlockedInputQualities = new
	// boolean[Globals.NUMBER_OF_VISIBLE_TILES];

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

	public Brain getBrain(int index) {
		return savedBrains.get(index);
	}

	public void unlockInputNeuronByTileAndQuality(int x, int y, int quality) {
		unlockNeuron((y * Globals.NUMBER_OF_VISIBLE_TILES_X + x) * Globals.NUMBER_OF_QUALITIES_PER_TILE + quality, 0);
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
