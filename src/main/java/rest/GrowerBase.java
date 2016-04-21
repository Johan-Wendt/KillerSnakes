package rest;

public abstract class GrowerBase extends TurnerBase implements Grower {
	private GrowerBase tail;
	
	public GrowerBase(InteractorDetails interactor, boolean head) {
		super(interactor);
		// TODO Auto-generated constructor stub
	}
	public GrowerBase(InteractorDetails interactor, Forms form,int xPos,int yPos, Directions direction) {
		super(interactor, form, xPos, yPos, direction);
	}
	
	@Override
	public int[] getPositionsSend() {
		int[] filler = new int[getLength() * Constants.INTS_SENT_PER_OBJECT];
		int pointer = 0;
		
		//int[] result = { interactor.subType(), xPos, yPos, width, height, rotation / 30, form.sendValue() };
		return getPositionsAllSend(filler, pointer);
	}

	
	public int getLength() {
		int current = 1;
		return (tail == null) ? current : tail.getLength(current);
		
		
	}
	private int getLength(int current) {
		current ++;
		return (tail == null) ? current : tail.getLength(current); 
		
	}
	public void setLength(int newLength) {
		if(newLength > 1) {
			if(tail == null) {
				tail = new (super.getInteractor(), false);
			}
			else {
				tail.setLength(newLength - 1);
			}
		}
		else {
			removeAllTails();
		}
	}
	public GrowerBase create() {
	    try {
	        return (Animal) getClass().newInstance();
	    } catch (Exception ex) {
	        // TODO Log me
	        return null;
	    }
	}
	LOOK AT THIS TO FIX IT!II
	
	public void removeAllTails() {
		if(tail != null) {
		    tail.removeAllTails();
		    tail = null;
		}
	}
	public int[] getPositionsAllSend(int[] filler, int pointer) {
		filler[pointer] = super.getInteractor().subType();
		filler[pointer + 1] = super.getxPos();
		filler[pointer + 2] = super.getyPos();
		filler[pointer + 3] = super.getWidth();
		filler[pointer + 4] = super.getHeight();
		filler[pointer + 5] = super.getRotation() / 30;
		filler[pointer + 6] = super.getInteractor().form().sendValue();
		pointer += 7;
		return (tail == null) ? filler : tail.getPositionsAllSend(filler, pointer);
		
	}
	
}
