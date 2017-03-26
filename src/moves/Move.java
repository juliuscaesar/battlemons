package moves;

import general.Element;
import general.MoveCategory;
import general.Attack;
import general.Status;

public class Move{
    final Attack name;
    final int damage;
    final int acc;
    final int pp;
    final Element element;
    final MoveCategory cat;
    final Status stats;
    
    Move(Attack name, int dmg, int acc, int pp,  Element element, MoveCategory cat, Status cause) {
      this.name = name;
      this.damage = dmg;
      this.acc = acc;
      this.pp = pp;
      this.element = element;
      this.cat = cat;
      this.stats = cause;

      if(acc < 1 || acc > 100) {
        throw new IllegalArgumentException(
            "Accuracy out of bounds. Valid values are only between 1 and 100");
      }
    }
    
    public int rawDmg(int offensiveAttribute){
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
  }