package damage;

import monsters.Monster;

import java.util.Random;

import general.Attack;
import general.MoveCategory;

import moves.Move;
import moves.MoveSet;


public class Damage {
	private CalcSpecialDmg csd;
	private MoveSet mset;
	private Random r;
	
	Damage(){
		csd = new CalcSpecialDmg();
		mset = new MoveSet();
		r = new Random();
	}
	
	public double getDamage(Attack atk, Monster source, Monster target){
		  Move move = mset.getMove(atk);
		  double power = (double)move.getPower();
		  double A = offAtt(move, source);
		  double D = defAtt(move, target);
		  double modifier = getModifier(move, source, target);
		  return getDmg(power, A/D, modifier);
		  
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
