package rest;

import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class HumanTouch {
	private Session session;
	private GameSession gameSession;
	private static final ArrayList<HumanTouch> allPlayers = new ArrayList<>();
	private boolean wantsToJoin = false;
//	private boolean isInGame = false;
	private String name = "";
	private GameRoom currentGameroom;

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
	}

	@OnWebSocketError
	public void onError(Throwable t) {
		System.out.println("Error: " + t.getMessage());
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {

		this.session = session;
		allPlayers.add(this);
	}

	@OnWebSocketMessage
	public void onMessage(Reader reader) throws IOException {
		char[] input = new char[3];
		reader.read(input, 0, 3);
		int[] realResult = numberify(input);
		if (gameSession != null) {
			gameSession.handleInput(realResult);
		} else {
			if (realResult[1] == 9) {
				if (realResult[2] == 1) {
					wantsToJoin = true;
				} else if (realResult[2] == 2) {
					quitKillerSnakes();
				}

			} 
			else if (realResult[1] == 7) {
				char[] message = new char[40];
				reader.read(message);
				String[] splitMessages = StringifyMessage(message);
			
				if (realResult[2] == 1) {
					name = splitMessages[0];
					pushAllNames();
					sendStringMessage(GameRoom.getAllRoomNames(), StringMessageTypes.GAMEROOM_NAME);
					sendStringMessage(name, StringMessageTypes.PLAYER_NAME);
				}
				else if (realResult[2] == 2) {
					int numberOfHumanPlayers = java.lang.Character.getNumericValue(splitMessages[1].charAt(0));
					int numberOfComputerPlayers = java.lang.Character.getNumericValue(splitMessages[2].charAt(0));

					GameRoom gameRoom = new GameRoom(splitMessages[0], numberOfHumanPlayers, numberOfComputerPlayers);
					boolean nameOk = gameRoom.registerGameRoom();
					if(!nameOk) {
						sendErrorCode(ErrorCodes.NAME_NOT_UNIQUE);
					}
					pushAllGameNames();
				}
				else if (realResult[2] == 3) {
					String roomName = splitMessages[0];
					GameRoom added = GameRoom.addPlayer(roomName, this);
					leaveGameRoom();
					currentGameroom = added;
				sendStringMessage(added.getName(), StringMessageTypes.CURRENT_GAMEROOM_NAME);
					
				}
				else if (realResult[2] == 4) {
					String roomName = splitMessages[0];
					GameRoom.startGame(roomName);
					
				}
			}
		}

	}
	private void sendErrorCode(ErrorCodes errorCode) {
		sendStringMessage(errorCode.errorCode(), StringMessageTypes.ERROR_CODE);

		
	}

	public static String[] StringifyMessage(char[] mess) {
		int n = 0;
		int size = 1;
		while(n < mess.length) {
			if(mess[n] == ',') {
				size++;
			}
			n++;
		}
		String[] result = new String[size];
		String partResult = "";
		int pointer = 0;
		int arrayPointer = 0;
		boolean searching = true;
		while(searching && pointer < mess.length) {
			if(mess[pointer] != ',') {
			partResult = partResult + mess[pointer];
			}
			pointer ++;
			if(mess[pointer] == ';') {
				result[arrayPointer] = new String(partResult);
				searching = false;
			}
			else if(mess[pointer] == ',') {
				result[arrayPointer] = new String(partResult);
				partResult = "";
				arrayPointer ++;
			}
			
		}
		return result;
		
	}

	public static int[] numberify(char[] arr) {
		int[] result = new int[arr.length];
		int n = 0;
		while (n < arr.length) {
			result[n] = java.lang.Character.getNumericValue(arr[n]);
			n++;
		}
		return result;
	}

	public void updatePlayer(ByteBuffer buf) {
		try {
			// for(Session session: sessions) {
			// ByteBuffer bufCopy = buf.duplicate();
			if (session.isOpen()) {
				session.getRemote().sendBytes(buf);
			}
			else {
				System.out.println("quit through IOexception");
				quitKillerSnakes();
			}
			// }

		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.out.println("quit through IOexception");
			quitKillerSnakes();
		}

	}

	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}

	public boolean isWantsToJoin() {
		return wantsToJoin;
	}

	public void setWantsToJoin(boolean wantsToJoin) {
		this.wantsToJoin = wantsToJoin;
	}
/**
	public boolean isInGame() {
		return isInGame;
	}

	public void setInGame(boolean isInGame) {
		this.isInGame = isInGame;
	}
**/
	private void pushAllNames() {
		for (HumanTouch player : allPlayers) {
			String result = "";
			for (HumanTouch sender : allPlayers) {
				result = result + sender.getName() + ",";
			}
			player.sendStringMessage(result, StringMessageTypes.ALL_PLAYER_NAMES);
		}
	}
	public static void pushAllGameNames() {
		for (HumanTouch player : allPlayers) {
			player.sendStringMessage(GameRoom.getAllRoomNames(), StringMessageTypes.GAMEROOM_NAME);
		}
		
	}
	public void sendStringMessage(String message, StringMessageTypes messageType) {

		String sendMessage = messageType.prefix() + message;
		try {

			if (session.isOpen()) {
				session.getRemote().sendString(sendMessage);
			}

		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.out.println("quit through IOexception");
			quitKillerSnakes();
		}
		
	}

	public String getName() {
		return name;
	}

	public void quitKillerSnakes() {
		try {
			session.close();
			session.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		if(currentGameroom != null) {
			currentGameroom.removePlayer(this);
		}
		allPlayers.remove(this);
		if (gameSession != null) {
			gameSession.quitGame(this);
		}
		pushAllNames();
		
	}

	public GameRoom getCurrentGameroom() {
		return currentGameroom;
	}

	public void setCurrentGameroom(GameRoom currentGameroom) {
		this.currentGameroom = currentGameroom;
	}
	public void leaveGameRoom() {
		if(currentGameroom != null) {
			currentGameroom.removePlayer(this);
			currentGameroom = null;
		}
	}
	public static ByteBuffer createSendBuffer(int[] message) {
		byte[] byteMessage = SnakeMasterController.integersToBytes(message);
		ByteBuffer buf = ByteBuffer.wrap(byteMessage);
		return buf;
	}

}