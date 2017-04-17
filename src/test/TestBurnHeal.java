package test;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Battle;
import general.Status;
import monsters.Monster;
import trainers.BurnHealItem;

public class TestBurnHeal {

	BurnHealItem burnHealer = new BurnHealItem();
    Battle b = Battle.testBattle();
    Monster monster = b.p1.getActiveMonster();
	
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
