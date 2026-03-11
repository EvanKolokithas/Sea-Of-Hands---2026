package seaOfHands;


public abstract class POI {
    // vars
    protected final String name;
    protected final String desc;
    protected final int sanityLevel;
    protected boolean canCamp;
    protected boolean lootable;
    protected boolean used = false;
    protected boolean hasCamp = false;

    
    // constructor
    protected POI(String name, String desc, boolean camp, boolean lootable, int sanityLevel) {
        this.name = name;
        this.desc = desc;
        this.canCamp = camp;
        this.lootable = lootable;
        this.sanityLevel = sanityLevel;
    }

    // Methods
    public String getName() { 
    	return name; 
    }
    
    public boolean canCamp() { 
    	return canCamp; 
    }
    
    public int getSanityLevel() { 
    	return sanityLevel; 
    }
    
    public String getInfo() { 
    	return name + " : " + desc;
    }
    
    public void setUpCamp() { 
    	hasCamp = true; 
    }
    
    public boolean getHasCamp() {
		return hasCamp;
    }
    
    public boolean isUsed() { 
    	return used; 
    }
    public void makeUsed() {
    	used = true; 
    }

    public boolean isLootable() { 
    	return lootable && !used; 
    }
    
    /** Called when player arrives; chance to set lootable or campable */
    public void onArrival() {
    	
    }

	}