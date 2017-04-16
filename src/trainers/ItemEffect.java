package trainers;

import monsters.Monster;
import moves.Move;
import general.Attack;

/**
 * Interface for the ItemEffects.
 * 
 * Every Item has its Effect, and this Interface cover's them all.
 * A item may have effect for one of the following pair { useItem / canUseItem } or { useItemOn / canUseItemOn }.
 * Therefore, an item that can affect a Move, cannot have any affect on the Monster alone.
 * And an item that can affect a Monster, cannot individually affect a Move.
 * 
 * On the latest version of this project, all the items implemented, may only be used on monsters that are alive.
 *
 */
public interface ItemEffect {

	
	/**
	 * This method will be implemented for Items that have effect on the Monster itself.
	 * Example: Status Remover, Health Restoring, etc.
	 * @param battlemon is the item's target monster.
	 */
	void useItem(Monster battlemon);
	
	// method for using items that affect a monster's moves
	
	/**
	 * This method will be implemented for Items that have effect on the Monster's move, and maybe on the Monster itself.
	 * @param battlemon is the item's target monster.
	 * @param move is the target monster's target move.
	 */
	void useItemOnMove(Monster battlemon, Move move);
	
	/**
	 * Check if the item can be used on the target battlemon.
	 * 
	 * @param battlemon is the item's target monster.
	 * @return true if the item can be used, false if not.
	 */
	boolean canUseItem(Monster battlemon);
	
	/**
	 * Check if the item can be used on the target battlemon's target move.
	 * 
	 * @param battlemon is the item's target monster.
	 * @param move is the target monster's target move.
	 * @return true if the item can be used, false if not.
	 */
	boolean canUseItemOnMove(Monster battlemon, Attack attack);
}
