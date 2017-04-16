package moves;

import general.Element;
import general.MoveCategory;
import general.Attack;
import general.Status;

/**
 * Move Class.
 * A Move consists of:
 * 	- Name
 * 	- 1 Element
 * 	- Damage
 * 	- Accuracy
 * 	- Category
 * 	- Power Points
 * 
 * A valid Move should have an accuracy between 1 and 100.
 * 
 * A Move can inflict a Status, but one Status only. Every time this move lands on an enemy, it will roll a random number, and based on the Status own
 * accuracy, it will inflict this Status on the enemy, or not. An enemy may be immune to your status, making it impossible to apply your Status to him.
 * 
 * Moves can belong to different categories, but one move may only belong to one category. Those categories, declared in the
 * MoveCategory enum are { Physical, Special, Status }
 * 
 * Using a Move consume PP ( Power Points ), and if you run out of PPs, your move is not usable anymore.
 * You can restore your PP through itens.
 * 
 * 
 */
public class Move{
    private final Attack name;
    private int damage;
    private int acc;
    private int pp;
    private int maxPP;
    private final Element element;
    private final MoveCategory cat;
    private Status stats;
    private int statChance;
    
    /**
     * Constructor for a Move Class.
     * If this Move should inflict any Status, it needs to call the method { Status.addStatus } otherwise it wont inflict anything.
     * 
     * @param name is the Move's Name. Listed in the Attack Enum.
     * @param dmg is the Power for this move.
     * @param acc is the Accuracy Rate for this Move. Values between 1 and 100.
     * @param pp is the Power Points for this Move, in other words, the Number of times this number can be used.
     * @param element is the Move's Element, the Element will inflict a multiplier ranging from { 0.5 to 2.0 } when calculating the damage to an opponent.
     * @param cat is this Move's Category.
     */
     public Move(Attack name, int dmg, int acc, int pp,  Element element, MoveCategory cat) {
      this.name = name;
      this.damage = dmg;
      this.acc = acc;
      this.pp = pp;
      this.maxPP = pp;
      this.element = element;
      this.cat = cat;
      this.stats = Status.Normal;
      this.statChance = 0;

      if(acc < 0 || acc > 100) {
        throw new IllegalArgumentException(
            "Accuracy out of bounds. Valid values are only between 1 and 100. Value: " + acc);
      }
    }
     
     /**
      * Copy Constructor for the Move Class.
      * This method doesn't require a posterior call to addStatus, unless for some reason, you want to change the Status.
      * 
      * @param move is another Move.
      */
     public Move(Move move){
    	 this.name = move.name;
    	 this.damage = move.damage;
    	 this.acc = move.acc;
    	 this.pp = move.pp;
    	 this.element = move.element;
    	 this.cat = move.cat;
    	 this.stats = move.stats;
    	 this.maxPP = move.maxPP;
    	 this.statChance = move.statChance;
     }
     
    
    
    /**
     * Add a status to a Move.
     * 
     * @param stat is the Status.
     * @param chance is the chance to apply that Status when hitting a monster. Value between 1 and 100
     */
    public void addStatus(Status stat, int chance){
    	this.stats = stat;
    	this.statChance = chance;
    }
    
    /**
     * Calculate a Move's raw damage(doesn't take into account the target's attributes.
     * 
     * The formula is as follow: ((12 * Move's Raw Damage * Offensive Attribute) + 2) /50).
     * 
     * The Offensive Attribute will change depending on the Move's Category.
     * If Category is Physical, Offensive Attribute should be source's Attack value.
     * If Category is Special, Offensive Attribute should be the source's Special Attack value.
     * 
     * @param offensiveAttribute is the value of either Special Attack or Attack, depending on the Move.
     * @return the value of the raw damage of this move.
     */
    public double rawDmg(int offensiveAttribute){
    	if(this.damage > 0){
	    	int value = (12 * this.damage * offensiveAttribute);
	    	return ((value + 2)/50);
    	}
    	return 0;
    }
    
    /**
     * @return the Enum for the Move's name.
     */
    public Attack toAttack(){
    	return name;
    }
    
    /**
     * @return the Move's Category
     */
    public MoveCategory getCat(){
    	return this.cat;
    }
    
    /**
     * @return the Move's Element
     */
    public Element getElem(){
    	return element;
    }
    
    /**
     * @return the Move's Status that it should apply if it hits the enemy.
     */
    public Status getStatus(){
    	return stats;
    }
    
    /**
     * @return the chance to inflict the Status.
     */
    public int getStatusChance(){
    	return statChance;
    }
    
    /**
     * @return the Power for this Move.
     */
    public int getPower(){
    	return damage;
    }
    
    /**
     * @return the current PP value for this Move.
     */
    public int getPP() {
        return pp;
    }
    
    /**
     * @return the max PP value for this Move.
     */
    public int getMaxPP() {
        return maxPP;
    }
    
    /**
     * @return the Accuracy value for this Move.
     */
    public int getAcc(){
    	return this.acc;
    }
    
    /**
     * This method will restore the Power Points(PP) for this move the {@code amount} value.
     * A move can only has its PP restored if its PP is less than the max PP value.
     * 
     * @return true if the PP increased, false if it failed to increase.
     */
    public boolean addPowerPoints(int amount)
    {
    	if(amount <= 0){
    		return false;
    	}
    	if(this.pp >= this.maxPP){
    		return false;
    	}
    	this.pp = this.pp + amount;
    	if (this.pp > this.maxPP){
    		this.pp = this.maxPP;
    	}
    	return true;
    }
    
    /**
     * This method should be called every time this move is used.
     * It will update the PP if needed.
     * 
     * @return true if the move executed, false if there were no more PP left.
     */
    public boolean use(){
    	if(this.pp > 0){
    		this.pp--;
    		return true;
    	}
    	return false;
    }
    
    /**
     * @return the Move's Category
     */
    public Status getMoveCategory()
    {
    	return this.getMoveCategory();
    }
    
    /**
     * This method will check if this Move can inflict some Status on the enemy.
     * 
     * @return true if it can inflict any Status on the opponent, false if it can't.
     */
    public boolean isStatusMove()
    {
    	if (this.getStatus() == Status.Normal)
    		return false;
    	
    	return true;
    }
    
    /**
     * This method is used for testing only, and should under no circumstances be used outside the scope of a test.
     * 
     * @param pp
     */
    public void testSetPP(int pp)
    {
    	this.pp = pp;
    }
    
    
  }

