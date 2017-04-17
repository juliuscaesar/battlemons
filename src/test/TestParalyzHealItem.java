package test;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Battle;
import general.Status;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.ParalyzHealItem;

public class TestParalyzHealItem {

	ParalyzHealItem paralyzHealItem = new ParalyzHealItem();
    Battle b = Battle.testBattle();
    Monster monster = b.p1.getActiveMonster();
	
	@Test
	public void test1() {
		
		monster.setStatus(Status.Paralysis);
		
		paralyzHealItem.useItem(monster);
		
		assertEquals(Status.Normal, monster.getStatus());
	}
	
	public void test2()
	{
		monster.setStatus(Status.Poison);
		paralyzHealItem.useItem(monster);		
		assertEquals(Status.Normal, monster.getStatus());
	}

}
