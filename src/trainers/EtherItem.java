package trainers;

import general.Attack;
import monsters.Monster;
import moves.Move;

// if pokemon is in burn state, set its status to normal

public class EtherItem implements ItemEffect{
	
	int boostAmount = 10;
	
	@Override
	public void useItem(Monster battlemon) {
		
	}

	@Override
	public void useItemOnMove(Monster battlemon, Move move) {
		move.increasePP(boostAmount);
	}

	@Override
	public boolean canUseItem(Monster battlemon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canUseItemOnMove(Monster battlemon, Attack attack) {
		return battlemon.canIncreasePP(attack);
	}

}