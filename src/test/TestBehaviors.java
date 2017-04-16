package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import DT.Behavior_HealHP;
import DT.Behavior_HealPP;
import general.Battle;
import general.Decision;
import general.Decision.UseHealHPItem;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.FreshwaterItem;
import trainers.Item;
import trainers.ItemEnum;
import trainers.Trainer;

public class TestBehaviors {

	@Test
	public void test1() {
		
		List<Monster> trainer1team = 
				new ArrayList<Monster>(
						Arrays.asList(MonsterSet.getRandomMonster(),
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster(),
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster()));
		List<Monster> trainer2team = 
				new ArrayList<Monster>(
						Arrays.asList(MonsterSet.getRandomMonster(),
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster(), 
								MonsterSet.getRandomMonster()));


		List<Item> trainer1items = new ArrayList<Item>();
		List<Item> trainer2items = new ArrayList<Item>();
		
		

		// create trainers to run this battle with
		Trainer trainer1 = new Trainer("Caesar", trainer1team, trainer1items);
		Trainer trainer2 = new Trainer("Nishant", trainer2team, trainer2items);
		
		Behavior_HealHP behaviorHealHP = new Behavior_HealHP();
		ItemEnum itemEnum = null;
		
		Item freshwater = new Item(itemEnum.FreshWater, 1);
		
		trainer1items.add(freshwater);
		trainer1.getActiveMonster().testSetHP(10);
		//UseHealHPItem useHealItem = new UseHealHPItem(freshwater, trainer1.getActiveMonster());
		Battle b = new Battle(trainer1, trainer2);
		//useHealItem.executeDecision(b, trainer1);
		Decision decision = behaviorHealHP.execute(b, trainer1);
		decision.executeDecision(b, trainer1);
		
		assertEquals(60, trainer1.getActiveMonster().getHP());
		
		
		
	}

}
