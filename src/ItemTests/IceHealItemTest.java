package ItemTests;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Status;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.IceHealItem;

public class IceHealItemTest {

	IceHealItem iceHealItem = new IceHealItem();
	Monster monster = MonsterSet.getRandomMonster();
	
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
