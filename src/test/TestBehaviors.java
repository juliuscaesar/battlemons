package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import DT.Behavior_ChangeToMonsterWithHighSurvivability;
import DT.Behavior_HealHP;
import DT.Behavior_HealPP;
import DT.Behavior_HealStatus;
import DT.Behavior_InflictStatusEffect;
import DT.Behavior_SwitchToMonsterWithBestAttack;
import DT.Behavior_SwitchToMonsterWithHighestHP;
import DT.Behavior_SwitchToMonsterWithLowestHP;
import DT.Behavior_SwitchToMonsterWithStrongType;
import DT.Behavior_SwitchToMonsterWithWeakType;
import DT.Behavior_UseHighestAccuracyMove;
import DT.Behavior_UseHighestDamageMove;
import DT.Behavior_UseLowestAccuracyMove;
import general.Attack;
import general.Battle;
import general.Decision;
import general.MonsterID;
import general.Status;

import monsters.Monster;
import monsters.MonsterSet;
import moves.Move;
import moves.MoveSet;

import trainers.Item;
import trainers.ItemEnum;
import trainers.Trainer;

public class TestBehaviors {	

	// create trainers to run this battle with
	Battle b = Battle.testBattle();
	
	
	ItemEnum itemEnum = null;
	
	Attack attack;
	
	@Test
	public void test1() {		
		
		Item freshwater = new Item(itemEnum.FreshWater, 1);
		
		b.p1.items.add(freshwater);
		b.p1.getActiveMonster().testSetHP(10);	
		Behavior_HealHP behaviorHealHP = new Behavior_HealHP();
		Decision decision = behaviorHealHP.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		
		assertEquals(60, b.p1.getActiveMonster().getHP());	
		
		
	}
	
	@Test
	public void test2()
	{
		Item ether = new Item(itemEnum.Ether, 1);
		
		b.p1.items.add(ether);
		
		Attack attackToTest = b.p1.getActiveMonster().listMoves().get(2);
		Move moveToTest = MoveSet.getMove(attackToTest);
		moveToTest.testSetPP(0);
		System.out.println(moveToTest);
		Behavior_HealPP behaviorHealPP = new Behavior_HealPP();
		Decision decision = behaviorHealPP.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		
		assertEquals(10, moveToTest.getPP());
	}
	
	@Test
	public void test3()
	{
		Monster currentActiveMonster = b.p1.getActiveMonster();
		Behavior_ChangeToMonsterWithHighSurvivability behaviorSurvivability =
				new Behavior_ChangeToMonsterWithHighSurvivability();
		Decision decision = behaviorSurvivability.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		Monster changedMonster = b.p1.getActiveMonster();
		
		assertEquals(currentActiveMonster.getID() == changedMonster.getID(), false);
	}
	
	@Test
	public void test4() {		
		
		Item cureBurn = new Item(itemEnum.BurnHeal, 1);
		
		b.p1.items.add(cureBurn);
		b.p1.getActiveMonster().setStatus(Status.Burn);	
		Behavior_HealStatus behaviorHealStatus = new Behavior_HealStatus();
		Decision decision = behaviorHealStatus.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		
		assertEquals(Status.Normal, b.p1.getActiveMonster().getStatus());	
		
		
	}
	
	@Test
	public void test5() {		
		
		Item cureBurn = new Item(itemEnum.Antidote, 1);
		
		b.p1.items.add(cureBurn);
		b.p1.getActiveMonster().setStatus(Status.Poison);	
		Behavior_HealStatus behaviorHealStatus = new Behavior_HealStatus();
		Decision decision = behaviorHealStatus.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		
		assertEquals(Status.Normal, b.p1.getActiveMonster().getStatus());	
		
		
	}
	
	@Test
	public void test6() {		
		
		Item cureBurn = new Item(itemEnum.Awakening, 1);
		
		b.p1.items.add(cureBurn);
		b.p1.getActiveMonster().setStatus(Status.Sleep);	
		Behavior_HealStatus behaviorHealStatus = new Behavior_HealStatus();
		Decision decision = behaviorHealStatus.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		
		assertEquals(Status.Normal, b.p1.getActiveMonster().getStatus());	
		
		
	}
	
	@Test
	public void test7() {		
		
		Item cureBurn = new Item(itemEnum.IceHeal, 1);
		b.p1.items.add(cureBurn);
		b.p1.getActiveMonster().setStatus(Status.Freeze);	
		Behavior_HealStatus behaviorHealStatus = new Behavior_HealStatus();
		Decision decision = behaviorHealStatus.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		
		assertEquals(Status.Normal, b.p1.getActiveMonster().getStatus());	
		
		
	}
	
	@Test
	public void test8() {		
		
		Item cureBurn = new Item(itemEnum.ParalyzHeal, 1);
		
		b.p1.items.add(cureBurn);
		b.p1.getActiveMonster().setStatus(Status.Paralysis);	
		Behavior_HealStatus behaviorHealStatus = new Behavior_HealStatus();
		Decision decision = behaviorHealStatus.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		
		assertEquals(Status.Normal, b.p1.getActiveMonster().getStatus());	
		
		
	}
	
	@Test
	public void test9()
	{
			
		Behavior_InflictStatusEffect behaviorStatusEffect =
				new Behavior_InflictStatusEffect();
		Decision decision = behaviorStatusEffect.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		
		assertEquals(b.p1.getActiveMonster().getStatus().equals(Status.Normal), false);
	}
	
	@Test
	public void test10()
	{		
		Monster originalMonster = b.p1.getActiveMonster();
		Behavior_SwitchToMonsterWithBestAttack behaviorSwitchMonster =
				new Behavior_SwitchToMonsterWithBestAttack();
		Decision decision = behaviorSwitchMonster.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		Monster newMonster = b.p1.getActiveMonster();
		
		assertEquals(originalMonster.getID() == newMonster.getID(), false);
	}
	
	@Test
	public void test11()
	{		
		Monster originalMonster = b.p1.getActiveMonster();
		Behavior_SwitchToMonsterWithHighestHP behaviorSwitchMonster =
				new Behavior_SwitchToMonsterWithHighestHP();
		Decision decision = behaviorSwitchMonster.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		Monster newMonster = b.p1.getActiveMonster();
		
		assertEquals(originalMonster.getID() == newMonster.getID(), false);
	}
	
	@Test
	public void test12()
	{		
		Monster originalMonster = b.p1.getActiveMonster();
		Behavior_SwitchToMonsterWithLowestHP behaviorSwitchMonster =
				new Behavior_SwitchToMonsterWithLowestHP();
		Decision decision = behaviorSwitchMonster.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		Monster newMonster = b.p1.getActiveMonster();
		newMonster.testSetHP(1);
		
		assertEquals(originalMonster.getID() == newMonster.getID(), false);
	}
	
	@Test
	public void test13()
	{		
		Monster originalMonster = b.p1.getActiveMonster();
		Behavior_SwitchToMonsterWithStrongType behaviorSwitchMonster =
				new Behavior_SwitchToMonsterWithStrongType();
		Decision decision = behaviorSwitchMonster.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		Monster newMonster = b.p1.getActiveMonster();
		
		
		assertEquals(originalMonster.getID() == newMonster.getID(), false);
	}
	
	@Test
	public void test14()
	{		
		Monster originalMonster = b.p1.getActiveMonster();
		Behavior_SwitchToMonsterWithWeakType behaviorSwitchMonster =
				new Behavior_SwitchToMonsterWithWeakType();
		Decision decision = behaviorSwitchMonster.execute(b, b.p1);
		decision.executeDecision(b, b.p1);
		Monster newMonster = b.p1.getActiveMonster();
		
		
		assertEquals(originalMonster.getID() == newMonster.getID(), false);
	}
	
	@Test
	public void test15()
	{		
		int opponentOriginalHP = b.p2.getActiveMonster().getHP();
		Behavior_UseHighestAccuracyMove behaviorAccuracyMove =
				new Behavior_UseHighestAccuracyMove();
		Decision decision = behaviorAccuracyMove.execute(b, b.p1);
		decision.executeDecision(b, b.p1);	
		
		assertEquals(b.getOpponentsMonster(b.p2).getHP() == opponentOriginalHP, false);
	}
	
	@Test
	public void test16()
	{		
		int opponentOriginalHP = b.p2.getActiveMonster().getHP();
		Behavior_UseHighestDamageMove behaviorDamageMove =
				new Behavior_UseHighestDamageMove();
		Decision decision = behaviorDamageMove.execute(b, b.p1);
		decision.executeDecision(b, b.p1);	
		
		assertEquals(b.getOpponentsMonster(b.p2).getHP() == opponentOriginalHP, false);
	}
	
	@Test
	public void test17()
	{		
		int opponentOriginalHP = b.p2.getActiveMonster().getHP();
		Behavior_UseLowestAccuracyMove behaviorAccuracyMove =
				new Behavior_UseLowestAccuracyMove();
		Decision decision = behaviorAccuracyMove.execute(b, b.p1);
		decision.executeDecision(b, b.p1);	
		
		assertEquals(b.getOpponentsMonster(b.p2).getHP() == opponentOriginalHP, false);
	}
	
	

}
