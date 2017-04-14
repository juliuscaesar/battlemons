package trainers;

import general.Status;
import monsters.Monster;
import moves.Move;

// if pokemon is in freeze state, set its status to normal

public class IceHealItem implements ItemEffect{
	
	@Override
	public void useItem(Monster battlemon) {

		if (battlemon.getStatus().equals(Status.Freeze))
			battlemon.addStatus(Status.Normal);
		
	}

	@Override
	public void useItemOnMove(Monster battlemon, Move move) {
		// TODO Auto-generated method stub
		
	}

}