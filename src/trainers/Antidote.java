package trainers;

import general.Attack;
import monsters.Monster;
import moves.Move;

// if battlemon is poisoned, set its status back to normal

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canUseItemOnMove(Monster battlemon, Attack attack) {
		// TODO Auto-generated method stub
		return false;
	}

	


	
}
