package test;

import static org.junit.Assert.*;

import org.junit.Test;

import general.Attack;
import general.Element;
import general.MoveCategory;
import general.Status;
import moves.*;

public class TestMove {
	MoveSet set = new MoveSet();
	
	@Test
	public void test_move_1(){
		Attack atk = Attack.Acid;
		Move move = new Move(atk, 0, 50, 0, Element.Ground, MoveCategory.Physical);
		assertEquals(atk, move.toAttack());
		assertEquals(Element.Ground, move.getElem());
		assertEquals(Status.Normal, move.getStatus());
		assertEquals(move.getPP(), move.getMaxPP());
	}
	
	@Test
	public void test_move_2(){
		for(Attack atk : Attack.values()){
			Move move = new Move(atk, 0, 50, 0, Element.Ground, MoveCategory.Physical);
			assertEquals(Status.Normal, move.getStatus());
			assertEquals(move.getPP(), move.getMaxPP());
			for(Status s : Status.values()){
				move.addStatus(s,50);
				assertEquals(move.getStatus(), s);
				assertEquals(move.getStatusChance(), 50);
			}
		}
	}
	
	@Test
	public void test_move_3(){
		for(Attack atk : Attack.values()){
			Move move = new Move(atk, 0, 50, 10, Element.Ground, MoveCategory.Physical);
			assertEquals(Status.Normal, move.getStatus());
			assertEquals(move.getPP(), move.getMaxPP());
			int pp = move.getMaxPP();
			while(move.use()){
				assertNotEquals(move.getPP(), pp);
				pp = move.getPP();
			}
		}
	}
	
	@Test
	public void test_move_4(){
		for(Attack atk : Attack.values()){
			Move move = MoveSet.getMove(atk);
			move.use();
			Move other = MoveSet.getMove(atk);
			assertNotEquals(move.getPP(), other.getPP());
		}
	}
	
	@Test
	public void test_move_5(){
		for(Attack atk : Attack.values()){
			Move move = MoveSet.getMove(atk);
			while(move.use());
		}
		for(Attack atk : Attack.values()){
			Move move = MoveSet.getMove(atk);
			assertEquals(move.getPP(), move.getMaxPP());
			assertNotEquals(move.getPP(), 0);
		}
	}
	
	
}
