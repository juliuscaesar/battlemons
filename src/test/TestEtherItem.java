package test;

import static org.junit.Assert.*;
import general.Battle;

import org.junit.Test;

import general.MonsterID;
import monsters.Monster;
import monsters.MonsterSet;
import moves.Move;
import moves.MoveSet;
import trainers.EtherItem;

public class TestEtherItem {

	EtherItem etherItem = new EtherItem();
    Battle b = Battle.testBattle();
    Monster monster = b.p1.getActiveMonster();
	
	@Test	
	public void test()
	{
		Move testMove = MoveSet.getMove(monster.listMoves().get(2));
		testMove.testSetPP(1);
		System.out.println(testMove.getPP());
		etherItem.useItemOnMove(monster, testMove);
		
		assertEquals(testMove.getPP(), 11);
	}

}
