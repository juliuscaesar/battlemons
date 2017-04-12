package trainers;

import com.sun.org.apache.xerces.internal.util.Status;

import general.Attack;
import monsters.Monster;
import moves.Move;

// if battlemon is poisoned, set its status back to normal

public class Antidote implements ItemEffect{
		
	@Override
	public void useItem(Monster battlemon) {

		if (battlemon.getStatus().equals(general.Status.Poison))
			battlemon.addStatus(general.Status.Normal);
		
	
		
	}

	@Override
	public void useItemOnMove(Monster battlemon, Move move) {
		// TODO Auto-generated method stub
		
	}

	


	
}
