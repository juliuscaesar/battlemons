package damage;

import java.util.Random;

import general.Attack;
import monsters.Monster;
import moves.Move;
import moves.MoveSet;

public class Hit {
	private MoveSet set;
	private Random rnd;
	
	public Hit(){
		set = new MoveSet();
		rnd = new Random();
	}
	
	/**
	 * This method will return a value of 0 to 100 of the chance that this move have
	 * to hit the target monster.
	 * @param atk
	 * @param source
	 * @param target
	 * @return
	 */
	public int getHitChance(Attack atk, Monster source, Monster target){
		Move move = set.move(atk);
		double moveAcc = move.getAcc();
		//double sourceAcc = source.getAcc();
		
		return (int)moveAcc;
	}
	
	/**
	 * This method will execute a hit, if it hits, it will try to apply the move's status to the target pokemon.
	 * It will return true if the attack landed, false if it missed.
	 * 
	 * @param atk
	 * @param source
	 * @param target
	 * @param dmg
	 * @return
	 */
	public boolean hit(Attack atk, Monster source, Monster target, int dmg){
		int chance = getHitChance(atk, source, target);
		int value = Math.abs(rnd.nextInt(100));
		boolean gotHit = (value < chance);
		if(gotHit){
			target.receiveAttack(dmg);
			int valueStat = Math.abs(rnd.nextInt(100));
			Move m = set.move(atk);
			if(valueStat < m.getStatChance()){
				target.setStatus(m.getStatus());
			}
		}		
		return gotHit;
	}
}
