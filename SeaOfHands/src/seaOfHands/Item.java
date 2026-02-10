package seaOfHands;

public abstract class Item {
	
	//vars
	protected String name;
	protected String desc;
	
	//constructor
	protected Item(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	//getters
	public String getInfo() {
		return name + " : " + desc;
	}
	
	public String getName() {
		return name;
	}

}
