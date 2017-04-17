package test;

import static org.junit.Assert.*;

import org.junit.Test;

import monsters.Monster;
import monsters.MonsterSet;
import moves.Move;
import moves.MoveSet;
import trainers.EtherItem;

public class TestEtherItem {

	EtherItem etherItem = new EtherItem();
	Monster monster = MonsterSet.getRandomMonster();
	
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
