package trainers;

import monsters.Monster;
import moves.Move;

public interface ItemEffect {

	
	// method for using items that affect the monster (eg: health potion)
	void useItem(Monster battlemon);
	
	// method for using items that affect a monster's moves
	void useItemOnMove(Monster battlemon, Move move);
	
	/**
	 * Check if the item can be used on the target battlemon.
	 * @param battlemon is the target battlemon.
	 * @return true if the item can be used, false if not.
	 */
	boolean canUseItem(Monster battlemon);
	
	/**
	 * Check if the item can be used on the target battlemon's target move.
	 * @param battlemon is the target battlemon.
	 * @param attack is the target battlemon's target move's enum.
	 * @return true if the item can be used, false if not.
	 */
	boolean canUseItemOnMove(Monster battlemon, Attack attack);
}
