package rest;

import java.util.ArrayList;
import java.util.HashMap;

public class GameRoom {
	private ArrayList<HumanTouch> players = new ArrayList<>();
	private static final HashMap<String, GameRoom> gameRooms = new HashMap<>();
	private String name;
	private int maxNumberOfPlayers;
	
	public GameRoom(String name, int maxNumberOfPlayers) {
		this.name = name;
		this.maxNumberOfPlayers = maxNumberOfPlayers;
		gameRooms.put(name, this);
	}
	
	public void addPlayer(HumanTouch player) {
		players.add(player);
	}
	public void removePlayer(HumanTouch player) {
		players.remove(this);
	}
	public void removeGameRoom() {
		gameRooms.remove(this);
	}
	public static String getAllRoomNames() {
		String result = "";
		ArrayList<String> names = new ArrayList (gameRooms.keySet());
		for(String name: names) {
			result = result + name + ",";
		}
		return result;
	}

}
