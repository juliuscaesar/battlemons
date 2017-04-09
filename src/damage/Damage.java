package damage;

import monsters.Monster;

import java.util.List;
import java.util.Random;

import general.Attack;
import general.MoveCategory;

import moves.Move;
import moves.MoveSet;


public class Damage {
	private CalcSpecialDmg csd;
	private MoveSet mset;
	private Random r;
	
	/**
	 * Default Constructor for the Damage Class.
	 */
	Damage(){
		csd = new CalcSpecialDmg();
		mset = new MoveSet();
		r = new Random();
	}
	
	/**
	 * This method will calculate the damage from an attack sent from a {@code source} monster to a
	 * {@code target} monster.
	 * 
	 * @param atk is the Attack ID.
	 * @param source is attacking Monster.
	 * @param target is Monster receiving the attack.
	 * @return the damage that target should receive.
	 */
	public double getDamage(Attack atk, Monster source, Monster target){
		  Move move = mset.getMove(atk);
		  double power = (double)move.getPower();
		  double A = offAtt(move, source);
		  double D = defAtt(move, target);
		  double modifier = getModifier(move, source, target);
		  return getDmg(power, A/D, modifier);
		  
	}
	
	/**
	 * This method will calculate the highest possible damage from an attack sent from a {@code source} monster to a
	 * {@code target} monster.
	 * 
	 * @param atk is the Attack ID.
	 * @param source is attacking Monster.
	 * @param target is Monster receiving the attack.
	 * @return the damage that target should receive.
	 */
	public double highestPossibleDamage(Attack atk, Monster source, Monster target){
		Move move = mset.getMove(atk);
		  double power = (double)move.getPower();
		  double A = offAtt(move, source);
		  double D = defAtt(move, target);
		  double modifier = getModifier_noRng(1.0,move, source, target);
		  return getDmg(power, A/D, modifier);
	}
	
	/**
	 * This method will calculate the lowest possible damage from an attack sent from a {@code source} monster to a
	 * {@code target} monster.
	 * 
	 * @param atk is the Attack ID.
	 * @param source is attacking Monster.
	 * @param target is Monster receiving the attack.
	 * @return the damage that target should receive.
	 */
	public double lowestPossibleDamage(Attack atk, Monster source, Monster target){
		Move move = mset.getMove(atk);
		  double power = (double)move.getPower();
		  double A = offAtt(move, source);
		  double D = defAtt(move, target);
		  double modifier = getModifier_noRng(0.85,move, source, target);
		  return getDmg(power, A/D, modifier);
	}
	
	/**
	 * Compare all highest possible damage for every attack from source monster's move set.
	 * 
	 * @param atks is a list of the attacking Monster's attacks.
	 * @param source is attacking Monster.
	 * @param target is Monster receiving the attack.
	 * @return the best attack that the attacking monster should use against the target monster.
	 */
	public Attack getStrongestMove(List<Attack> atks, Monster source, Monster target){
		double minimum = Double.MAX_VALUE;
		Attack best = null;
		for(Attack atk : atks){
			double dmg = highestPossibleDamage(atk, source, target);
			if(dmg < minimum){
				best = atk;
				minimum = dmg;
			}
		}
		if(best == null){
			throw new IllegalArgumentException("[Damage - getStrongestMove]: Best Move not found.");
		}
		return best;
	}
	
	private double getDmg(double power, double AD, double modifier){
		return((((22.0 * power * AD)/50) + 2) * modifier);
	}
	
	private double getModifier(Move move, Monster source, Monster target){
		double multiplier = csd.getMultiplier(move.getElem(), target.getElem());
		double rng = 0.85 + (0.15) * r.nextDouble();
		double finalMulti = multiplier * rng;
		if(move.getElem() == source.getElem()){
			finalMulti *= 1.5;
		}
		return finalMulti;
	}
	
	private double getModifier_noRng(double value, Move move, Monster source, Monster target){
		double multiplier = csd.getMultiplier(move.getElem(), target.getElem());
		double finalMulti = multiplier * value;
		if(move.getElem() == source.getElem()){
			finalMulti *= 1.5;
		}
		return finalMulti;
	}
	
	private double defAtt(Move move, Monster m)
	  {
		  MoveCategory cat = move.getCat();
		  switch(cat){
			  case Physical: {
				  return (double)m.getDef();
			  }
			  case Special: {
				  return (double)m.getSpDef();
			  }
			  default: {
				  return 0.0;
			  }
		  }
	  }
		  
	private double offAtt(Move move, Monster m)
	  {
		  MoveCategory cat = move.getCat();
		  switch(cat){
			  case Physical: {
				  return (double)m.getAtk();
			  }
			  case Special: {
				  return (double)m.getSpAtk();
			  }
			  default: {
				  return 0.0;
			  }
		  }
	  }
}
