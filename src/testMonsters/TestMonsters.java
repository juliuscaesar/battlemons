package testMonsters;

import monsters.MonsterSet;

import static org.junit.Assert.*;

import org.junit.Test;

import monsters.Monster;

import moves.Move;
import moves.MoveSet;

import general.Status;
import general.MonsterID;

public class TestMonsters{
	MonsterSet set = new MonsterSet();
	
	
	/*public TestMonsters(){
		this.used = MonsterSet.getRandomMonster();
	}
	
	public TestMonsters(Monster m){
		this.used = m;
	}*/
	
	@Test
	public void test__checkAlive__1(){
		Monster m = set.getRngMonster();
		assertEquals(m.isAlive(), true);
	}
	
	@Test
	public void test__checkAlive_2(){
		Monster m = set.getRngMonster();
		int dmg = m.getHP() * 10;
		m.receiveAttack(dmg);
		assertEquals(m.isAlive(), false);
	}
	
	@Test
	public void test__receiveAttack__1(){
		Monster m = set.getRngMonster();
		int hp = m.getHP();
		m.receiveAttack(hp/2);
		assertNotEquals(hp, m.getHP());
	}
	
	@Test
	public void test__receiveAttack__2(){
		Monster m = set.getRngMonster();
		int hp = m.getHP();
		m.receiveAttack(hp/2);
		assertEquals(m.isAlive(), true);
	}
	
	@Test
	public void test__addStatus__1(){
		Monster m = set.getRngMonster();
		assertEquals(m.getStatus(), Status.Normal);
	}
	
	@Test
	public void test__addStatus__2(){
		Monster m = set.getRngMonster();
		assertEquals(m.getStatus(), Status.Normal);
		m.setStatus(Status.Paralysis);
		assertEquals(m.getStatus(), Status.Paralysis);
	}
	
	@Test
	public void test__addStatus__3(){
		Monster m = set.getRngMonster();
		assertEquals(m.getStatus(), Status.Normal);
		m.setStatus(Status.Sleep);
		assertEquals(m.getStatus(), Status.Sleep);
	}
	
	@Test
	public void test__addStatus__4(){
		Monster m = set.getRngMonster();
		assertEquals(m.getStatus(), Status.Normal);
		m.setStatus(Status.Poison);
		assertEquals(m.getStatus(), Status.Poison);
	}
	
	@Test
	public void test__addStatus__5(){
		Monster m = set.getRngMonster();
		assertEquals(m.getStatus(), Status.Normal);
		m.setStatus(Status.Burn);
		assertEquals(m.getStatus(), Status.Burn);
	}
	
	@Test
	public void test__addStatus__6(){
		Monster m = set.getRngMonster();
		assertEquals(m.getStatus(), Status.Normal);
		m.setStatus(Status.Freeze);
		assertEquals(m.getStatus(), Status.Freeze);
		
		for(MonsterID id : MonsterID.values()){
			Monster mm = set.getMonster(id);//MonsterSet.getRandomMonster();
			assertEquals(m.getStatus(), Status.Normal);
			mm.setStatus(Status.Freeze);
			assertEquals(m.getStatus(), Status.Freeze);	
		}
	}
	
	@Test
	public void test__resetStatus(){
		for(MonsterID id : MonsterID.values()){
			Monster m = set.getMonster(id);//MonsterSet.getRandomMonster();
			System.out.println(id.toString());
			assertEquals(m.getStatus(), Status.Normal);
			m.setStatus(Status.Freeze);
			assertEquals(m.getStatus(), Status.Freeze);
			for(int i = 0; i < 10; i++){
				m.updateStats();
			}
			assertEquals(m.getStatus(), Status.Normal);		
		}
	}
	
	
	
	
}