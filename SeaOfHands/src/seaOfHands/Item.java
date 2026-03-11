package seaOfHands;

public abstract class Item {
	
	//vars
	protected String name;
	protected String desc;
	protected int sanity;
	
	//constructor
	protected Item(String name, String desc, int sanity) {
		this.name = name;
		this.desc = desc;
		this.sanity = sanity;
	}
	
	//getters
	public String getInfo() {
		return name + " : " + desc;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSanity() {
		return sanity;
	}

}
