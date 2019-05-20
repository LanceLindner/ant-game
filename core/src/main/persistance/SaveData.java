package main.persistance;

import java.util.ArrayList;

import main.entities.ants.Ant;
import main.main.Globals;

public class SaveData {
	private ArrayList<boolean[]> unlockedNeurons = new ArrayList<boolean[]>();
	private ArrayList<Ant> savedAnts = new ArrayList<Ant>();

	public SaveData() {
		unlockedNeurons.add(new boolean[Globals.NUMBER_OF_INPUT_NEURONS]);
		unlockedNeurons.add(new boolean[Globals.NUMBER_OF_HIDDEN_NEURONS]);
		unlockedNeurons.add(new boolean[Globals.NUMBER_OF_OUTPUT_NEURONS]);

		for (int i = 0; i < unlockedNeurons.size(); ++i) {
			for (int j = 0; j < unlockedNeurons.get(i).length; ++j) {
				unlockedNeurons.get(i)[j] = false;
			}
		}

		savedAnts = new ArrayList<Ant>();
	}

	public void unlockNeuron(int x, int y) {
		unlockedNeurons.get(y)[x] = true;
	}

	public void saveAnt(Ant ant) {
		savedAnts.add(ant);
	}

	public void deleteAnt(Ant ant) {
		savedAnts.remove(ant);
	}
}
