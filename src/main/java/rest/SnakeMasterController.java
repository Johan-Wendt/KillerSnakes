package rest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import com.google.common.primitives.Bytes;

public class SnakeMasterController implements MasterController {

	private PlayerController playerController;
	private ProjectileController projectileController;
	private GameLoop gameLoop;
	private HumanTouch socket;
	private ArrayList<TypeController> controllers = new ArrayList<>();

	public SnakeMasterController(HumanTouch socket) {

		this.socket = socket;

		playerController = new PlayerController(Types.PLAYER);
		projectileController = new ProjectileController(Types.PROJECTILE);
		// weaponController = new WeaponController(this);

		// Just contemporary
		playerController.createPlayer();
		// playerController.createPlayerAI();

		controllers.add(playerController);
		controllers.add(new HappeningController(Types.HAPPENING));
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
		}
	}

	@Override
	public void sendOutPutPositions() {
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
		byte[] byteMessage = integersToBytes(message);

		ByteBuffer buf = ByteBuffer.wrap(byteMessage);
		socket.updatePlayer(buf);

	}

	public int[] getAllPositionsSend() {
		int resultSize = 0;
		for (TypeController controller : controllers) {
			resultSize += controller.getLength() * Constants.INTS_SENT_PER_OBJECT + 2;
		}
		int[] result = new int[resultSize];
		int pointer = 0;
		for (TypeController controller : controllers) {
			int[] tempResult = controller.getAllPositionsSend();
			int n = 0;
			while (n < tempResult.length) {
				result[pointer] = tempResult[n];
				n++;
				pointer++;
			}
		}
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
		sendOutPutPositions();
		sendOutPutWeaponInfo();
		
	}

	private void sendOutPutWeaponInfo() {
		int[] positions = getWeaponInfoSend();
		sendMessage(positions);
		
	}

	private int[] getWeaponInfoSend() {
			return playerController.getWeaponInfoSend();
	}

}