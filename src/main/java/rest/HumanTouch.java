package rest;

import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

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
	private Session session2;
	private RemoteEndpoint remote;
	private RemoteEndpoint remote2;
	// private GamePlan gamePlan = new GamePlan();
	private SnakeMasterController masterController;

	private int players = 0;

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		System.out.println("Close: statusCode=" + statusCode + ", reason=" + reason);
	}

	@OnWebSocketError
	public void onError(Throwable t) {
		System.out.println("Error: " + t.getMessage());
	}

	@OnWebSocketConnect
	public void onConnect(Session sessions) {

		if (players == 0) {
			session = sessions;
			System.out.println("Connect: " + session.getRemoteAddress().getAddress());
			remote = session.getRemote();
			players++;
		}
		else if (players == 1) {
			session2 = sessions;
			System.out.println("Connect and start game: " + session.getRemoteAddress().getAddress());
			remote2 = session2.getRemote();
			masterController = new SnakeMasterController(this);
			players = 0;
		}
	}

	/**
	 * @OnWebSocketMessage public void onMessage(String message) {
	 *                     System.out.println("Message: " + message); }
	 **/
	@OnWebSocketMessage
	public void onMessage(Reader reader) throws IOException {
		char[] input = new char[3];
		reader.read(input, 0, 3);
		int[] realResult = numberify(input);

		masterController.handleInput(realResult);
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
			remote.sendBytes(buf);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}

	}

}