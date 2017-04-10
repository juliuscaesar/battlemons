package trainers;

import general.Attack;
import general.Status;
import monsters.Monster;
import moves.Move;

// if pokemon is in burn state, set its status to normal

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

}