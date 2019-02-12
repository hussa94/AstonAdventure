package Game;

import Screens.Startup;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.HashMap;

/**
 * The class Aston Adventure is used as the main basis for the adventure game. This class will be instantiated upon launch.
 * Any save data, character selection or found objects will be stored here.
 */
public class AstonAdventure extends Game {

	//Batch
	public SpriteBatch batch;

	//Character Data
	private String selectedCharacter;
	public HashMap<Integer, String> characters = new HashMap<Integer, String>();


	/**
	 * Used when launching the game. The game data is set and the game is begun by switching to the startup screen.
	 */
	@Override
	public void create () {

		batch = new SpriteBatch();

		//Configure available characters
		characters.put(0, "Female");
		characters.put(1, "Male");
		selectedCharacter = characters.get(1);

		//Startup screen
		this.setScreen(new Startup(this));
	}

	/**
	 * Method to switch the character to be used in the game.
	 * @param character Integer correlating to a specific character.
	 */
	public void changeCharacter(int character) {
		selectedCharacter = characters.get(character);
	}

	public String getSelectedCharacter() {
		return selectedCharacter;
	}

	//Unused
	@Override
	public void render () {
		super.render();
	}

	//Unused
	@Override
	public void dispose () {
		batch.dispose();
	}
}

