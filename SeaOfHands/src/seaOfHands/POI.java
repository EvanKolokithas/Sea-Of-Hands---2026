package seaOfHands;


public abstract class POI {
    // vars
    protected final String name;
    protected final String desc;
    protected final int sanityLevel;
    protected boolean camp;
    protected boolean lootable;
    protected boolean used = false;
    
    // constructor
    protected POI(String name, String desc, boolean camp, boolean lootable, int sanityLevel) {
        this.name = name;
        this.desc = desc;
        this.camp = camp;
        this.lootable = lootable;
        this.sanityLevel = sanityLevel;
    }

    // Methods
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
        // 20% chance to become campable
        if (!camp && Math.random() < 0.2) camp = true;

        // 30% chance to become not lootable
        lootable = Math.random() >= 0.3;

        // mark as unused again if needed (for loot tracking)
        used = false;
    }
}