package seaOfHands;

public class Tool extends Item implements Usable{
	
	//Tools are items used to save energy when doing a specific action
	//consumed after use
	
	
	//vars
	private String action;
	

	//constructor
	public Tool(String name, String desc, String action) {
		// TODO Auto-generated constructor stub
		super(name, desc);
		
		this.action = action;
	}
	
	//Methods
	
	public String getAction() {
		return action;
	}
	
	@Override
	public void use(Player player) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canUse(Player player) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUseDescription() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isConsumable() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
