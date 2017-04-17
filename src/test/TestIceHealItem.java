package test;

import static org.junit.Assert.*;

import org.junit.Test;

import general.MonsterID;
import general.Status;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.IceHealItem;

public class TestIceHealItem {

	IceHealItem iceHealItem = new IceHealItem();
	Monster monster = MonsterSet.getMonster(MonsterID.Adnocana);
	
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
