package rest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.websocket.api.Session;

public class GameSession {
	private HashMap<Integer, HumanTouch> players = new HashMap<>();
	private SnakeMasterController masterController;
/**
	public GameSession(HumanTouch player1, HumanTouch player2) {
		sendPlayerToken(player1, 1);
		sendPlayerToken(player2, 2);
		
		players.put(Players.PLAYER1.token(), player1);
		players.put(Players.PLAYER2.token(), player2);
		
		player2.setGameSession(this);
		player1.setGameSession(this);
		
	//	player2.setInGame(true);
	//	player1.setInGame(true);

		masterController = new SnakeMasterController(this);

	}
	public GameSession(ArrayList<HumanTouch> playerArray) {
		int n = 0;
		while(n < playerArray.size()) {
			players.put(Players.getPlayer(n + 1).token(), playerArray.get(n));
			n++;
		}
		for (Map.Entry<Integer, HumanTouch> entry : players.entrySet()) {
			Integer token = entry.getKey();
		    HumanTouch player = entry.getValue();
		    sendPlayerToken(player, token);
		//    player.setInGame(true);
			player.setGameSession(this);
		}
		masterController = new SnakeMasterController(this);
	}
	**/
	public GameSession(ArrayList<HumanTouch> playerArray, int computerPlayers) {
		int n = 0;
		while(n < playerArray.size()) {
			players.put(Players.getPlayer(n + 1).token(), playerArray.get(n));
			n++;
		}
		for (Map.Entry<Integer, HumanTouch> entry : players.entrySet()) {
			Integer token = entry.getKey();
		    HumanTouch player = entry.getValue();
		    sendPlayerToken(player, token);
		//    player.setInGame(true);
			player.setGameSession(this);
		}
		masterController = new SnakeMasterController(this, playerArray.size(), computerPlayers);
	}

	public void updatePlayer(ByteBuffer buf) {
	//	for (HumanTouch player : players) {
	//		ByteBuffer bufCopy = buf.duplicate();
	//		player.updatePlayer(bufCopy);
	//	}
		for (HumanTouch player : players.values()) {
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
		player.setGameSession(null);
		players.values().remove(player);
		if(players.size() == 0) {
			destroyGame();
		}
	}
	public void quitGame(int player) {
		players.get(player).setGameSession(null);
		int[] intMessage = { Constants.QUIT_CURREN_GAME, -1 };
		ByteBuffer buf = HumanTouch.createSendBuffer(intMessage);
		players.get(player).updatePlayer(buf);
		
		players.remove(player);
		if(players.size() == 0) {
			destroyGame();
		}
	}
	public void destroyGame() {

		masterController.setSocket(null);
		masterController = null;
	}

}
