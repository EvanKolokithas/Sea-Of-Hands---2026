package seaOfHands;


public abstract class POI{
	
	//vars
	protected String name;
	protected String desc;
	protected boolean camp;
	private int sanityLevel;
	
	//constructor
	protected POI(String name, String desc, boolean camp, int sanityLevel) {
		this.name = name;
		this.desc = desc;
		this.camp = camp;
		this.sanityLevel = sanityLevel;
	}
	
	//Methods
	
	public String getName() {
		return name;
	}
	
	public boolean canCamp() {
		return camp;
	}
	
	public int getSanityLevel() {
		return sanityLevel;
	}
	
	public String getInfo() {
		return name + " : " + desc;
	}
	
	
	public void setUpCamp() {
		camp = true;
	}
	
	
}
