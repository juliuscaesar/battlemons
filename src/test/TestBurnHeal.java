package test;

import static org.junit.Assert.*;

import org.junit.Test;

import general.MonsterID;
import general.Status;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.BurnHealItem;

public class TestBurnHeal {

	BurnHealItem burnHealer = new BurnHealItem();
	Monster monster = MonsterSet.getMonster(MonsterID.Adnocana);
	
	@Test
	public void test1() {
		
		monster.setStatus(Status.Burn);
		
		burnHealer.useItem(monster);
		
		assertEquals(Status.Normal, monster.getStatus());
	}
	
	public void test2()
	{
		monster.setStatus(Status.Poison);
		burnHealer.useItem(monster);		
		assertEquals(Status.Normal, monster.getStatus());
	}

}
