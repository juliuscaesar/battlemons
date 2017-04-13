package trainers;

import general.Status;
import monsters.Monster;
import moves.Move;

// if pokemon is in sleep state, set its status to normal

public class AwakeningItem implements ItemEffect{
	
	@Override
	public void useItem(Monster battlemon) {

		if (battlemon.getStatus().equals(Status.Sleep))
			battlemon.setStatus(Status.Normal);
		
	}

	@Override
	public void useItemOnMove(Monster battlemon, Move move) {
		// TODO Auto-generated method stub
		
	}

}
