package moves;

import general.Element;
import general.MoveCategory;
import general.Attack;
import general.Status;

import monsters.Attributes;

public class Move{
	private final Attack name;
	private final int damage;
	private final int acc;
	private final int pp;
	private int current_pp;
	private final Element element;
	private final MoveCategory cat;
    private Status stats;
    private int statChance;
    
     public Move(Attack name, int dmg, int acc, int pp,  Element element, MoveCategory cat) {
      this.name = name;
      this.damage = dmg;
      this.acc = acc;
      this.pp = pp;
      current_pp = pp;
      this.element = element;
      this.cat = cat;
      this.stats = Status.Normal;

      if(acc < 1 || acc > 100) {
        throw new IllegalArgumentException(
            "Accuracy out of bounds. Valid values are only between 1 and 100");
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
    
    public boolean useMove(){
    	if(this.current_pp == 0){
    		return false;
    	}
    	this.current_pp--;
    	return true;
    }
    
    public boolean increasePP(int amount){
    	if(this.current_pp == this.pp){
    		return false;
    	}
    	this.current_pp += amount;
    	this.current_pp %= this.pp;
    	return true;
    }
  }