package rest;

import java.util.ArrayList;

public interface TypeController extends Controller {
	 public  void addToActingObjects(Actor actor);
	 public void disposeOfRemovables();
	 public ArrayList<Actor> getActingObjects();

}
