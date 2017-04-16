package ItemTests;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Status;
import monsters.Monster;
import monsters.MonsterSet;
import moves.Move;
import moves.MoveSet;
import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.EtherItem;
import trainers.IceHealItem;

public class EtherItemTest {

	EtherItem etherItem = new EtherItem();
	Monster monster = MonsterSet.getRandomMonster();
	
	@Test	
	public void test()
	{
		Move testMove = MoveSet.getMove(monster.listMoves().get(1));
		testMove.testSetPP(30);
		etherItem.useItemOnMove(monster, testMove);
		
		assertEquals(testMove.getPP(), 40);
	}

}
