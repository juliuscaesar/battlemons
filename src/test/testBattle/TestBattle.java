package test.testBattle;

import static org.junit.Assert.*;

import org.junit.Test;

import general.MonsterID;
import monsters.Monster;
import monsters.MonsterSet;
import moves.Move;
import general.Attack;
import general.Element;
import moves.MoveSet;
import damage.Damage;

public class TestBattle {
	MonsterSet set = new MonsterSet();
	MoveSet moves = new MoveSet();
	Monster a = set.getMonster(MonsterID.Adnocana);
	Monster b = set.getMonster(MonsterID.Emberfly);
	Damage d = new Damage();
	
	@Test
	public void test1(){
		for(Attack atk : a.listMoves()){
			int dmg = d.getDamage(atk, a,b);
			assertNotEquals(dmg,0);
			int hp = b.getHP();
			b.receiveAttack(dmg);
			assertNotEquals(hp, b.getHP());
			return;
		}
	}
}
