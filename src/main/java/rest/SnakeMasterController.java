package rest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import com.google.common.primitives.Bytes;

public class SnakeMasterController implements MasterController{
	// private GameBoardController gameBoardController = new
	// GameBoardController();
	// private BonusController bonusController = new BonusController();
	// private WeaponController weaponController;
	private PlayerController playerController;
	private GameLoop gameLoop;
	private HumanTouch socket;
	private ArrayList<TypeController> controllers = new  ArrayList<>();

	public SnakeMasterController(HumanTouch socket) {

		this.socket = socket;

		playerController = new PlayerController(Types.PLAYER);
		// weaponController = new WeaponController(this);

		// Just contemporary
		playerController.createPlayer();
		playerController.createPlayerAI();
		gameLoop = new GameLoop(this);
		gameLoop.runGameLoop();
		
		controllers.add(playerController);
	}

	public void gameRound() {
		// weaponController.act(this);
		playerController.controllerRound(this);
		sendOutPut();
		// bonusController.act(this);
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
			// snakeController.turnPlayer(input[0], (byte) input[2]);
		}
		if (input[1] == 2) {
			gameLoop.pause();
		}
		if (input[1] == 3) {
			// snakeController.shoot(weaponController, (byte) input[0]);
		}
		if (input[1] == 4) {
			// snakeController.changeWeapon((byte) input[0]);
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

	private void sendMessage(int[] message)  {
		byte[] byteMessage = integersToBytes(message);

		ByteBuffer buf = ByteBuffer.wrap(byteMessage);
		socket.updatePlayer(buf);

	}
	public int[] getAllPositionsSend() {
		int[] result = new int[0];
		for(TypeController controller: controllers) {
			result = HelperMethods.intConcatenator(result, controller.getAllPositionsSend());
		}
		return result;
	}

	@Override
	public Interactor craschCheck(int xPos, int yPos, Interactor interactor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void takeInput(byte[] input) {
		// TODO Auto-generated method stub
		
	}

	

}