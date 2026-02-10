package seaOfHands;

public interface Usable {
	//4 methods in this interface (Some are likly temporary)
	
	void use (Player player) throws Exception;
	
	boolean canUse (Player player) throws Exception;
	
	String getUseDescription() throws Exception;
	
	boolean isConsumable() throws Exception;
}
