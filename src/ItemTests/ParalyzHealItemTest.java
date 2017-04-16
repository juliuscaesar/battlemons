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
import trainers.ParalyzHealItem;

public class ParalyzHealItemTest {

	ParalyzHealItem paralyzHealItem = new ParalyzHealItem();
	Monster monster = MonsterSet.getRandomMonster();
	
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
