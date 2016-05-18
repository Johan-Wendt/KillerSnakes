package rest;

public interface Constants {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int GAME_HERTZ = 60;

	// SubType, xPos, yPos
	public static final int INTS_SENT_PER_OBJECT = 7;

	// Codes for client actions
	public static final int PLAYER_TOKEN = 0;
	public static final int PLAYER = 1;
	public static final int BONUS = 2;
	public static final int PROJECTILE = 3;
	public static final int WEAPON_INFO = 4;
	public static final int ERROR_CODE = 5;
	public static final int QUIT_CURREN_GAME = 6;
	public static final int PLAY_SOUND = 7;
	
	
	//Game board
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;

}
