package test;

import static org.junit.Assert.*;
import general.Battle;

import org.junit.Test;

import monsters.Monster;
import trainers.FreshwaterItem;

public class TestFreshwaterItem {

	FreshwaterItem waterItem = new FreshwaterItem();
    Battle b = Battle.testBattle();
    Monster monster = b.p1.getActiveMonster();
	
	@Test	
	public void test()
	{		
		monster.testSetHP(10);
		waterItem.useItem(monster);
		
		assertEquals(monster.getHP(), 60);
	}

}
