package rest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import org.eclipse.jetty.websocket.api.Session;

public class GameSession {
	private ArrayList<HumanTouch> players = new ArrayList<>();
	//private HumanTouch player1;
	//private HumanTouch player2;
	private SnakeMasterController masterController;

	public GameSession(HumanTouch player1, HumanTouch player2) {
		//this.player1 = player1;
		//this.player2 = player2;
		sendPlayerToken(player1, 1);
		sendPlayerToken(player2, 2);
		
		players.add(player1);
		players.add(player2);
		
		player2.setGameSession(this);
		player1.setGameSession(this);

		masterController = new SnakeMasterController(this);

	}

	public void updatePlayer(ByteBuffer buf) {
		for (HumanTouch player : players) {
			ByteBuffer bufCopy = buf.duplicate();
			player.updatePlayer(bufCopy);
		}

	}
	
	public void handleInput(int[] input) {
		masterController.handleInput(input);
	
	}
	
	public void sendPlayerToken(HumanTouch player, int playerNumber) {
		int playerToken = playerNumber;
		int[] intMessage = { Constants.PLAYER_TOKEN, playerToken, -1 };
		byte[] byteMessage = SnakeMasterController.integersToBytes(intMessage);
		ByteBuffer buf = ByteBuffer.wrap(byteMessage);
			player.updatePlayer(buf);

	}
	public void quitGame(HumanTouch player) {
		players.remove(player);
	}

}
