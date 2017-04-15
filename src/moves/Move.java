package moves;

import general.Element;
import general.MoveCategory;
import general.Attack;
import general.Status;

public class Move{
    final Attack name;
     int damage;
     int acc;
    int pp;
    int maxPP;
    final Element element;
    final MoveCategory cat;
    private Status stats;
    private int statChance;
    
     public Move(Attack name, int dmg, int acc, int pp,  Element element, MoveCategory cat) {
      this.name = name;
      this.damage = dmg;
      this.acc = acc;
      this.pp = pp;
      this.element = element;
      this.cat = cat;
      this.stats = Status.Normal;

      if(acc < 0 || acc > 100) {
        throw new IllegalArgumentException(
            "Accuracy out of bounds. Valid values are only between 1 and 100. Value: " + acc);
      }
    }
     
    
    
    /**
     * Add a status to a Move.
     * 
     * @param stat is the Status.
     * @param chance is the chance to apply that Status when hitting a monster.
     */
    public void addStatus(Status stat, int chance){
    	this.stats = stat;
    	this.statChance = chance;
    }
    
    /**
     * Calculate a Move's raw damage(doesn't take into account the target monster's attributes.
     * 
     * @param offensiveAttribute is the value of either Sp.Atk or Atk, depending on the Move.
     * @return the value of the raw damage of this move.
     */
    public double rawDmg(int offensiveAttribute){
    	int value = (12 * this.damage * offensiveAttribute);
    	return ((value + 2)/50);
    }
    
    public Attack toAttack(){
    	return name;
    }
    
    public MoveCategory getCat(){
    	return this.cat;
    }
    
    public Element getElem(){
    	return element;
    }
    
    public Status getStatus(){
    	return stats;
    }
    
    public int getStatusChance(){
    	return statChance;
    }
    
    public int getPower(){
    	return damage;
    }
    public int getPP() {
        return pp;
    }
    public int getMaxPP() {
        return maxPP;
    }
    
    public int getAcc() {
    	return acc;
    }
    
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
    
    public boolean use(){
    	if(this.pp > 0){
    		this.pp--;
    		return true;
    	}
    	return false;
    }
    
    public Status getMoveCategory()
    {
    	return this.getMoveCategory();
    }
    
    public boolean isStatusMove()
    {
    	if (this.getStatus() == Status.Normal)
    		return false;
    	
    	return true;
    }
  }

