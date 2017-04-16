package test;

import static org.junit.Assert.*;

import org.junit.Test;

import monsters.Monster;
import monsters.MonsterSet;
import trainers.FreshwaterItem;

public class TestFreshwaterItem {

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
