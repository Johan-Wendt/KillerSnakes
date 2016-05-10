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
	// private static ArrayList<Session> sessions = new ArrayList<>();
	private Session session;
	private GameSession gameSession;
	private static final ArrayList<HumanTouch> allPlayers = new ArrayList<>();
	// private RemoteEndpoint remote;
	// private GamePlan gamePlan = new GamePlan();
	// private static ArrayList<SnakeMasterController> masterControllers = new
	// ArrayList<>();
	private boolean wantsToJoin = false;
	private boolean isInGame = false;
	private String name = "";

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
		if (isInGame) {
			gameSession.handleInput(realResult);
		} else {
			if (realResult[1] == 9) {
				if (realResult[2] == 1) {
					wantsToJoin = true;
				} else if (realResult[2] == 2) {
					quitGame();
				}

			} else if (realResult[1] == 8) {
				Timer timer = new Timer();
				HumanTouch creator = this;

				timer.schedule(new TimerTask() {
					public void run() {

						for (HumanTouch player : allPlayers) {
							if (!isInGame && player.isWantsToJoin() && !player.isInGame) {
								gameSession = new GameSession(creator, player);
								player.setWantsToJoin(false);
								player.setInGame(true);
								isInGame = true;
								wantsToJoin = false;
								timer.cancel();
							}
						}
					}
				}, 0, 1000);

			} 
			else if (realResult[1] == 7) {
				if (realResult[2] == 1) {
					char[] message = new char[40];
					reader.read(message);

					name = new String(message);
					pushAllNames();
				}
				if (realResult[2] == 2) {
					char[] message = new char[40];
					reader.read(message);

					name = new String(message);
					GameRoom gameRoom = new GameRoom(name, 2);
					pushAllGameNames();
				}
			}
		}

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
			// }

		} catch (IOException e) {
			e.printStackTrace(System.err);
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

	public boolean isInGame() {
		return isInGame;
	}

	public void setInGame(boolean isInGame) {
		this.isInGame = isInGame;
	}

	private void pushAllNames() {
		for (HumanTouch player : allPlayers) {
			String result = "";
			for (HumanTouch sender : allPlayers) {
				result = result + sender.getName() + "<br>";
			}
			player.sendStringMessage(result, StringMessageTypes.PLAYER_NAME);
		}
	}
	private void pushAllGameNames() {
		for (HumanTouch player : allPlayers) {
			player.sendStringMessage(GameRoom.getAllRoomNames(), StringMessageTypes.GAMEROOM_NAME);
		}
		
	}
	private void sendStringMessage(String message, StringMessageTypes messageType) {

		String sendMessage = messageType.prefix() + message;
		try {

			if (session.isOpen()) {
				session.getRemote().sendString(sendMessage);
			}

		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		
	}

	public String getName() {
		return name;
	}

	public void quitGame() {
		try {
			session.close();
			session.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		allPlayers.remove(this);
		if (gameSession != null) {
			gameSession.quitGame(this);
		}
	}

}