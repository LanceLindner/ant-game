package main.persistance;

import java.util.ArrayList;

import main.main.Globals;

public class SaveData {
	private ArrayList<boolean[]> unlockedNeurons = new ArrayList<boolean[]>();

	public SaveData() {
		unlockedNeurons.add(new boolean[Globals.NUMBER_OF_INPUT_NEURONS]);
		unlockedNeurons.add(new boolean[Globals.NUMBER_OF_HIDDEN_NEURONS]);
		unlockedNeurons.add(new boolean[Globals.NUMBER_OF_OUTPUT_NEURONS]);

		for (int i = 0; i < unlockedNeurons.size(); ++i) {
			for (int j = 0; j < unlockedNeurons.get(i).length; ++j) {
				unlockedNeurons.get(i)[j] = false;
			}
		}
	}
}
