package rest;

public interface InteractorDetails {
	//Let's the client know the object kind, classified into a broader
	//category, e.g. players, bonuses etc.
	public int type ();
	//Let's the client know the exact object. Only makes sense combined
	//with the type. If type says player, subType says what player.
	public int subType();
	public int width ();
	public int height ();
	//public int length();
	public Forms form();
	
	

}
