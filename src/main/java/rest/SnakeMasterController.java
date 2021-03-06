package rest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class SnakeMasterController implements MasterController {

	private PlayerController playerController;
	private ProjectileController projectileController;
	private GameLoop gameLoop;
	private GameSession socket;
	private boolean hasSoundToPlay = false;

	private ArrayList<TypeController> controllers = new ArrayList<>();

	public SnakeMasterController(GameSession socket, int humanPlayers, int ComputerPlayers) {

		this.socket = socket;

		playerController = new PlayerController(Types.PLAYER);
		projectileController = new ProjectileController(Types.PROJECTILE);
		int n = 0;
		while(n < humanPlayers) {
			playerController.createPlayer();
			n++;
		}
		int k = 0;
		while(k < ComputerPlayers) {
			playerController.createPlayerAI();
			k++;
		}


		controllers.add(new HappeningController(Types.HAPPENING));
		controllers.add(playerController);

		controllers.add(projectileController);

		gameLoop = new GameLoop(this);
		gameLoop.runGameLoop();
		
	}

	public void gameRound() {
		crashCheck();
		controllerRound();
		disposeOfRemovables();
		sendToClient();

	}

	public void controllerRound() {
		for (TypeController controller : controllers) {
			controller.controllerRound();
		}

	}

	private void crashCheck() {
		testCrashInto(getCrashers());
	}

	public void disposeOfRemovables() {
		for (TypeController controller : controllers) {
			controller.disposeOfRemovables();
		}
	}

	public void handleInput(int[] input) {
		// First byte is player number
		// Second byte is type of action
		// 1 is turn
		// 2 is pause
		// 3 is shoot
		// 4 is Change weapon
		// Third byte is additional info (like turn direction)

		if (input[1] == 1) {

			playerController.turnPlayer(Players.getPlayer(input[0]), Directions.getClientDirection(input[2]));
		} else if (input[1] == 2) {
			gameLoop.pause();
		} else if (input[1] == 3) {
			playerController.shoot(projectileController, Players.getPlayer(input[0]));
		} else if (input[1] == 4) {
			playerController.changeWeapon(Players.getPlayer(input[0]));
		} else if (input[1] == 5) {
			socket.quitGame(input[0]);
		}
	}

	@Override
	public void sendOutPut() {
		int[] positions = getAllPositionsSend();
		sendMessage(positions);

	}

	public static byte[] integersToBytes(int[] values) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		for (int i = 0; i < values.length; ++i) {
			try {
				dos.writeInt(values[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return baos.toByteArray();
	}

	private void sendMessage(int[] message) {
		if (socket != null) {
			byte[] byteMessage = integersToBytes(message);

			ByteBuffer buf = ByteBuffer.wrap(byteMessage);
			socket.updatePlayer(buf);
		}
	}

	public int[] getAllPositionsSend() {
		int resultSize = 0;
		for (TypeController controller : controllers) {
			resultSize += controller.getSendInfoSize();
		}
		for (TypeController controller : controllers) {
			if(controller.hasSoundToPlay()) {
				hasSoundToPlay = true;
				controller.setHasSoundToPlay(false);
			}
		}
		if(hasSoundToPlay) {
			//The Constant SOUND and the ending -1 is not added so we have to compensate
			resultSize += 2;
		}
		int[] result = new int[resultSize];
		int pointer = 0;
		for (TypeController controller : controllers) {
			pointer = controller.appendAllPositionsSend(result, pointer);
		}
		
		
		if(hasSoundToPlay) {
			result[pointer] = Constants.PLAY_SOUND;
			pointer ++;
			for (TypeController controller : controllers) {
				pointer = controller.appendAllSoundsSend(result, pointer);
			}
			hasSoundToPlay = false;
			result[pointer] = -1;
			pointer ++;
		}
		playerController.appendWeaponInfoSend(result, pointer);
		
		return result;
	}

	@Override
	public ArrayList<Interactor> getCrashers() {
		ArrayList<Interactor> result = new ArrayList<>();
		for (TypeController controller : controllers) {
			ArrayList<Interactor> partResult = controller.getCrashers();
			int n = 0;
			while (n < partResult.size()) {
				result.add(partResult.get(n));
				n++;
			}
		}
		return result;
	}

	public void testCrashInto(ArrayList<Interactor> violaters) {

		for (TypeController controller : controllers) {
			controller.testCrashInto(violaters);
		}
	}

	private void sendToClient() {
		sendOutPut();

	}

	public void setSocket(GameSession socket) {
		this.socket = socket;
	}

}