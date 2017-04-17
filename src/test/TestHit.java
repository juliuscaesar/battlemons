package test;

import monsters.*;

import org.junit.Test;

import damage.*;
import general.*;


public class TestHit {
	private Hit hit = new Hit();
	
	
	@Test
	public void test01(){
		Monster m = MonsterSet.getMonster(MonsterID.Adnocana);
		Monster n = MonsterSet.getMonster(MonsterID.Emberfly);
		
		System.out.println(hit.getHitChance(Attack.FirePunch, m ,n));
		
	}
}
