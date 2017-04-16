package ItemTests;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Status;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;

public class AwakeningItemTest {

	AwakeningItem awakeItem = new AwakeningItem();
	Monster monster = MonsterSet.getRandomMonster();
	
	@Test
	public void test1() {
		
		monster.setStatus(Status.Sleep);
		
		awakeItem.useItem(monster);
		
		assertEquals(Status.Normal, monster.getStatus());
	}
	
	public void test2()
	{
		monster.setStatus(Status.Poison);
		awakeItem.useItem(monster);		
		assertEquals(Status.Normal, monster.getStatus());
	}

}
