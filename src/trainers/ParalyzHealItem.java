package trainers;

import general.Attack;
import general.Status;
import monsters.Monster;
import moves.Move;

// if pokemon is in paralysis state, set its status to normal

public class ParalyzHealItem implements ItemEffect{
	
	@Override
	public void useItem(Monster battlemon) {

		if (battlemon.getStatus().equals(Status.Paralysis))
			battlemon.setStatus(Status.Normal);		
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