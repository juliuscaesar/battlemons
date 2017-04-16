package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import DT.Condition_HealthGreaterThanValue;
import general.Battle;
import monsters.Monster;
import monsters.MonsterSet;
import trainers.Item;
import trainers.Trainer;

public class TestConditions {

	@Test
	public void test() {
		

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
		Battle b = new Battle(trainer1, trainer2);
		
		Condition_HealthGreaterThanValue conditionHealth = 
				new Condition_HealthGreaterThanValue(10);

		assertEquals(true, conditionHealth.check_condition(b, trainer1));
		
	}

}
