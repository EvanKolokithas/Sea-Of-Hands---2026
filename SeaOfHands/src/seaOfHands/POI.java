package seaOfHands;

public abstract class POI {
	
	//vars
	protected String name;
	protected String desc;
	protected boolean camp;
	
	//constructor
	protected POI(String name, String desc, boolean camp) {
		this.name = name;
		this.desc = desc;
		this.camp = camp;
	}
	
	//Methods
	
	public String getName() {
		return name;
	}
	
	public boolean canCamp() {
		return camp;
	}
	
	public String getInfo() {
		return name + " : " + desc;
	}
	
	public void setUpCamp() {
		camp = true;
	}
}
