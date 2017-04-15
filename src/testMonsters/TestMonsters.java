package testMonsters;

import monsters.MonsterSet;

import static org.junit.Assert.*;

import org.junit.Test;

import monsters.Attributes;
import monsters.Monster;

public class TestMonsters{
	
	
	/*public TestMonsters(){
		this.used = MonsterSet.getRandomMonster();
	}
	
	public TestMonsters(Monster m){
		this.used = m;
	}*/
	
	@Test
	public void test__checkAlive_1(){
		Monster m = MonsterSet.getRandomMonster();
		assertEquals(m.isAlive(), true);
	}
	
	@Test
	public void testCheck__receiveAttack(){
		Monster m = MonsterSet.getRandomMonster();
		int hp = m.getHP();
		m.receiveAttack(hp/2);
		assertNotEquals(hp, m.getHP());
	}
	
	@Test
	public void testCheck__receiveAttack__2(){
		Monster m = MonsterSet.getRandomMonster();
		int hp = m.getHP();
		m.receiveAttack(hp/2);
		assertEquals(m.isAlive(), true);
	}
	
	@Test
	public void test__checkAlive_2(){
		Monster m = MonsterSet.getRandomMonster();
		int dmg = m.getHP() * 10;
		m.receiveAttack(dmg);
		assertEquals(m.isAlive(), false);
	}
	
}