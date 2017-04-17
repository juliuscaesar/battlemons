package test;

import static org.junit.Assert.*;

import org.junit.Test;

import general.MonsterID;
import general.Status;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.Antidote;

public class TestAntidote {

	Antidote antidote = new Antidote();
	Monster monster = MonsterSet.getMonster(MonsterID.Adnocana);
	
	@Test
	public void test1() {
		
		monster.setStatus(Status.Poison);
		
		antidote.useItem(monster);
		
		assertEquals(Status.Normal, monster.getStatus());
	}
	
	public void test2()
	{
		monster.setStatus(Status.Burn);
		antidote.useItem(monster);		
		assertEquals(Status.Normal, monster.getStatus());
	}

}
