package test;

import static org.junit.Assert.*;

import org.junit.Test;

import DT.Condition_BestAttackHasLowPP;
import DT.Condition_CanStatusHealItself;
import DT.Condition_HealthGreaterThanPercent;
import DT.Condition_HealthGreaterThanValue;
import DT.Condition_IfSomeMoveHasNoPP;
import DT.Condition_IsOpponentStatusNormal;
import DT.Condition_IsOpponentStatusNotNormal;
import DT.Condition_IsStatusNormal;
import DT.Condition_IsStatusNotNormal;
import DT.Condition_OpponentCanKillMonster;
import DT.Condition_OpponentHealthLowerThanPercent;
import DT.Condition_OpponentHealthLowerThanValue;
import DT.Condition_OtherMonsterCanSurviveOpponentAttack;
import DT.Condition_UserHasHealItem;
import general.Battle;
import trainers.Item;
import trainers.ItemEnum;

public class TestConditions {
    
    Battle b = Battle.testBattle();

	@Test
	public void test1() {		
		
		Condition_HealthGreaterThanValue conditionHealth = 
				new Condition_HealthGreaterThanValue(10);

		assertEquals(true, conditionHealth.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test2() {		
		
		Condition_BestAttackHasLowPP conditionLowPP = 
				new Condition_BestAttackHasLowPP(10);

		assertEquals(true, conditionLowPP.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test3() {		
		
		Condition_CanStatusHealItself condition = 
				new Condition_CanStatusHealItself();

		assertEquals(false, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test4() {		
		
		Condition_HealthGreaterThanPercent condition = 
				new Condition_HealthGreaterThanPercent(100);

		assertEquals(false, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test5() {		
		
		Condition_HealthGreaterThanValue condition = 
				new Condition_HealthGreaterThanValue(10);

		assertEquals(true, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test6() {		
		
		Condition_IfSomeMoveHasNoPP condition = 
				new Condition_IfSomeMoveHasNoPP();

		assertEquals(false, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test7() {		
		
		Condition_IsOpponentStatusNormal condition = 
				new Condition_IsOpponentStatusNormal();

		assertEquals(true, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test8() {		
		
		Condition_IsOpponentStatusNotNormal condition = 
				new Condition_IsOpponentStatusNotNormal();

		assertEquals(false, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test9() {		
		
		Condition_IsStatusNormal condition = 
				new Condition_IsStatusNormal();

		assertEquals(true, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test10() {		
		
		Condition_IsStatusNotNormal condition = 
				new Condition_IsStatusNotNormal();

		assertEquals(false, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test11() {		
		
		Condition_OpponentCanKillMonster condition = 
				new Condition_OpponentCanKillMonster();

		assertEquals(false, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test12() {		
		
		Condition_OpponentHealthLowerThanPercent condition = 
				new Condition_OpponentHealthLowerThanPercent(100);

		assertEquals(true, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test13() {		
		
		Condition_OpponentHealthLowerThanValue condition = 
				new Condition_OpponentHealthLowerThanValue(10);

		assertEquals(false, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test14() {		
		
		Condition_OtherMonsterCanSurviveOpponentAttack condition = 
				new Condition_OtherMonsterCanSurviveOpponentAttack();

		assertEquals(true, condition.check_condition(b, b.p1));
		
	}
	
	@Test
	public void test15() {		
		
		Item freshwater = new Item(ItemEnum.FreshWater, 1);
		
		Condition_UserHasHealItem condition = 
				new Condition_UserHasHealItem();
		
		b.p1.items.add(freshwater);

		assertEquals(true, condition.check_condition(b, b.p1));
		
	}

}
