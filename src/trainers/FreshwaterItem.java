package trainers;

import general.Attack;
import monsters.Monster;
import moves.Move;

/*
 * Restores 50 HP 
 * */

public class FreshwaterItem implements ItemEffect{

	int amountToRestore = 50;
	

	@Override
	public void useItem(Monster battlemon) {		
		
		battlemon.restoreHP(amountToRestore);
			
		
	}


	@Override
	public void useItemOnMove(Monster battlemon, Move move) {
		// TODO Auto-generated method stub
		
	}
	
}
