package trainers;

import general.Attack;
import monsters.Monster;
import moves.Move;

public interface ItemEffect {

	
	// method for using items that affect the monster (eg: health potion)
	void useItem(Monster battlemon);
	// method for using items that affect a monster's moves
	void useItemOnMove(Monster battlemon, Move move);
}
