package test;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Battle;
import general.Status;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.IceHealItem;

public class TestIceHealItem {

	IceHealItem iceHealItem = new IceHealItem();
    Battle b = Battle.testBattle();
    Monster monster = b.p1.getActiveMonster();
	
	@Test
	public void test1() {
		
		monster.setStatus(Status.Freeze);
		
		iceHealItem.useItem(monster);
		
		assertEquals(Status.Normal, monster.getStatus());
	}
	
	public void test2()
	{
		monster.setStatus(Status.Poison);
		iceHealItem.useItem(monster);		
		assertEquals(Status.Normal, monster.getStatus());
	}

}
