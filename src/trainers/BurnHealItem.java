package trainers;

import general.Attack;
import general.Status;
import monsters.Monster;
import moves.Move;


/**
 * BurnHealItem Class.
 * 
 * If a Monster has its Status set to Burn, it will cure that Status.
 *
 */
public class BurnHealItem implements ItemEffect{
	
	@Override
	public void useItem(Monster battlemon) {

		if (battlemon.getStatus().equals(Status.Burn))
			battlemon.setStatus(Status.Normal);
		
	}

	@Override
	public void useItemOnMove(Monster battlemon, Move move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canUseItem(Monster battlemon) {
		return (battlemon.getStatus() == Status.Burn);
	}

	@Override
	public boolean canUseItemOnMove(Monster battlemon, Attack attack) {
		// TODO Auto-generated method stub
		return false;
	}

}