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
	private static ArrayList<Session> sessions = new ArrayList<>();
	//private Session session;
	//private RemoteEndpoint remote;
	// private GamePlan gamePlan = new GamePlan();
	private static ArrayList<SnakeMasterController> masterControllers = new ArrayList<>();


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
		int playerToken = (sessions.size() % 2) + 1;
		int [] intMessage = {Constants.PLAYER_TOKEN, playerToken, -1};
		byte[] byteMessage = SnakeMasterController.integersToBytes(intMessage);
		ByteBuffer buf = ByteBuffer.wrap(byteMessage);
		try {
			session.getRemote().sendBytes(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//this.session = session;
		sessions.add(session);
		System.out.println("Connect: " + sessions.get(sessions.size() - 1).getRemoteAddress().getAddress());
		// remote = session.getRemote();

		if (sessions.size() % 2 == 0) {
			masterControllers.add(new SnakeMasterController(this));
		}

	}

	@OnWebSocketMessage
	public void onMessage(Reader reader) throws IOException {
		char[] input = new char[3];
		reader.read(input, 0, 3);
		int[] realResult = numberify(input);

		masterControllers.get(0).handleInput(realResult);
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
			for(Session session: sessions) {
				ByteBuffer bufCopy = buf.duplicate();
				session.getRemote().sendBytes(bufCopy);
			}
			
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}

	}
	



}