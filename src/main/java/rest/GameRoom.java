package rest;

import java.util.ArrayList;
import java.util.HashMap;

public class GameRoom {
	private ArrayList<HumanTouch> players = new ArrayList<>();
	private static final HashMap<String, GameRoom> gameRooms = new HashMap<>();
	private String name;
	private int maxNumberOfHumamnPlayers;
	private int numberOfComputerPlayer;
	
	public GameRoom(String name, int maxNumberOfPlayers, int numberOfComputerPlayer) {
		
		this.name = name;
		this.maxNumberOfHumamnPlayers = maxNumberOfPlayers;
		this.numberOfComputerPlayer = numberOfComputerPlayer;
		
	}
	
	
	public void addPlayer(HumanTouch player) {
		players.add(player);
		sendRoomInfo();
	}
	public void removePlayer(HumanTouch player) {
		players.remove(player);
		sendRoomInfo();
	}
	public boolean registerGameRoom() {
		if(gameRooms.containsKey(this.getName())) {
			return false;
		}
		gameRooms.put(this.getName(), this);
		return true;
		// gameRooms.put(name, this);
	}
	
	public void removeGameRoom() {
		gameRooms.remove(name);
	}
	//N.B, only returns non full game rooms
	public static String getAllRoomNames() {
		String result = "";
		ArrayList<String> names = new ArrayList (gameRooms.keySet());
		for(String roomName: names) {
			if(!getRoom(roomName).isFull())
			result = result + roomName + ",";
		}
		return result;
	}
	public static GameRoom addPlayer(String gameRoomName, HumanTouch player) {
		GameRoom added = gameRooms.get(gameRoomName);
		added.addPlayer(player);
		return added;
	}
	public static void removePlayer(String gameRoomName, HumanTouch player) {
		
		gameRooms.get(gameRoomName).removePlayer(player);
	}
	public void sendRoomInfo() {
		for(HumanTouch player: players) {
			player.sendStringMessage(getRoomInfo(), StringMessageTypes.GAMEROOM_MEMBERS);
		}
	}
	public String getRoomInfo() {
		String result = "";
		for(HumanTouch player: players) {
			result = result + "," +  player.getName();
		}
		return result;
	}
	public void startGame() {
		GameSession gameSession = new GameSession(players, numberOfComputerPlayer);
		
		for(HumanTouch player: players) {
			player.sendStringMessage("", StringMessageTypes.CURRENT_GAMEROOM_NAME);
			player.sendStringMessage("", StringMessageTypes.GAMEROOM_MEMBERS);
			player.sendStringMessage("", StringMessageTypes.CURRENT_GAMEROOM_NAME);
			player.setCurrentGameroom(null);
		}
		removeGameRoom(); 
		HumanTouch.pushAllGameNames();
	}
	public static void startGame(String gameRoomName) {
		GameRoom gameRoom = gameRooms.get(gameRoomName);
		gameRoom.startGame();
	}


	public String getName() {
		return name;
	}

	public static GameRoom getRoom(String name) {
		return gameRooms.get(name);
	}
	public boolean isFull() {
		return maxNumberOfHumamnPlayers == players.size();
	}
}
