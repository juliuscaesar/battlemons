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
import trainers.FreshwaterItem;
import trainers.IceHealItem;

public class FreshwaterItemTest {

	FreshwaterItem waterItem = new FreshwaterItem();
	Monster monster = MonsterSet.getRandomMonster();
	
	@Test	
	public void test()
	{		
		monster.testSetHP(10);
		waterItem.useItem(monster);
		
		assertEquals(monster.getHP(), 60);
	}

}
