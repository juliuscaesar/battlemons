package trainers;

import general.Attack;
import general.Status;
import monsters.Monster;
import moves.Move;


/**
 * Antidote Class.
 * 
 * If a Monster has its Status set to Poison, it will cure that Status.
 *
 */
public class Antidote implements ItemEffect{
		
	@Override
	public void useItem(Monster battlemon) {

		if (battlemon.getStatus().equals(general.Status.Poison))
			battlemon.setStatus(general.Status.Normal);
		
	
		
	}

	@Override
	public void useItemOnMove(Monster battlemon, Move move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canUseItem(Monster battlemon) {
		return (battlemon.getStatus() == Status.Poison);
	}

	@Override
	public boolean canUseItemOnMove(Monster battlemon, Attack attack) {
		// TODO Auto-generated method stub
		return false;
	}

	


	
}
