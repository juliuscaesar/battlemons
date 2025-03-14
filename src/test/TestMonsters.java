package test;

import monsters.MonsterSet;
import moves.Move;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import monsters.Monster;
import general.Attack;
import general.Battle;
import general.Status;
import general.MonsterID;

public class TestMonsters {

    /*
     * public TestMonsters(){ this.used = MonsterSet.getRandomMonster(); }
     * 
     * public TestMonsters(Monster m){ this.used = m; }
     */

    @Test
    public void test__checkAlive__1() {
        Battle b = Battle.testBattle();
        Monster m = b.p1.getActiveMonster();
        assertEquals(m.isAlive(), true);
    }

    @Test
    public void test__checkAlive_2() {
        Battle b = Battle.testBattle();
        Monster m = b.p1.getActiveMonster();
        int dmg = m.getHP() * 10;
        m.receiveAttack(dmg);
        assertEquals(m.isAlive(), false);
    }

    @Test
    public void test__receiveAttack__1() {
        Battle b = Battle.testBattle();
        Monster m = b.p1.getActiveMonster();
        int hp = m.getHP();
        m.receiveAttack(hp / 2);
        assertNotEquals(hp, m.getHP());
    }

    @Test
    public void test__receiveAttack__2() {
        Battle b = Battle.testBattle();
        Monster m = b.p1.getActiveMonster();
        int hp = m.getHP();
        m.receiveAttack(hp / 2);
        assertEquals(m.isAlive(), true);
    }

    @Test
    public void test__addStatus__1() {
        for (int i = 0; i < 10000; i++) {
            Battle b = Battle.testBattle();
            Monster m = b.p1.getActiveMonster();
            assertEquals(m.getStatus(), Status.Normal);
        }
    }

    @Test
    public void test__addStatus__2() {
        Battle b = Battle.testBattle();
        Monster m = b.p1.getActiveMonster();
        assertEquals(m.getStatus(), Status.Normal);
        m.setStatus(Status.Paralysis);
        assertEquals(m.getStatus(), Status.Paralysis);
    }

    @Test
    public void test__addStatus__3() {
        Battle b = Battle.testBattle();
        Monster m = b.p1.getActiveMonster();
        assertEquals(m.getStatus(), Status.Normal);
        m.setStatus(Status.Sleep);
        assertEquals(m.getStatus(), Status.Sleep);
    }

    @Test
    public void test__addStatus__4() {
        Battle b = Battle.testBattle();
        Monster m = b.p1.getActiveMonster();
        assertEquals(m.getStatus(), Status.Normal);
        m.setStatus(Status.Poison);
        assertEquals(m.getStatus(), Status.Poison);
    }

    @Test
    public void test__addStatus__5() {
        Battle b = Battle.testBattle();
        Monster m = b.p1.getActiveMonster();
        assertEquals(m.getStatus(), Status.Normal);
        m.setStatus(Status.Burn);
        assertEquals(m.getStatus(), Status.Burn);
    }

    @Test
    public void test__addStatus__6() {
        Battle b = Battle.testBattle();
        Monster m = b.p1.getActiveMonster();
        assertEquals(m.getStatus(), Status.Normal);
        m.setStatus(Status.Freeze);
        assertEquals(m.getStatus(), Status.Freeze);

        for (MonsterID id : MonsterID.values()) {
            Monster mm = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(mm.getStatus(), Status.Normal);
            mm.setStatus(Status.Freeze);
            assertEquals(mm.getStatus(), Status.Freeze);
        }
    }

    @Test
    public void test__addStatus__7() {
        for (MonsterID id : MonsterID.values()) {
            Monster one = MonsterSet.getMonster(id);
            assertEquals(one.getStatus(), Status.Normal);
            one.setStatus(Status.Burn);
            Monster two = MonsterSet.getMonster(id);
            assertEquals(two.getStatus(), Status.Normal);
        }
    }

    // @Test
    public void test__resetStatus__1() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            m.setStatus(Status.Freeze);
            assertEquals(m.getStatus(), Status.Freeze);

            while (!m.updateStats())
                ;

            assertEquals(m.getStatus(), Status.Normal);
        }
    }

    @Test
    public void test__resetStatus__2() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            m.setStatus(Status.Burn);
            assertEquals(m.getStatus(), Status.Burn);

            while (!m.updateStats())
                ;
            assertEquals(m.getStatus(), Status.Normal);
        }
    }

    @Test
    public void test__resetStatus__3() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            m.setStatus(Status.Sleep);
            assertEquals(m.getStatus(), Status.Sleep);

            while (!m.updateStats())
                ;
            assertEquals(m.getStatus(), Status.Normal);
        }
    }

    @Test
    public void test__applyStatus__1() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            int hp = m.getHP();
            m.setStatus(Status.Burn);
            m.applyStatusDamage();
            assertEquals(m.getStatus(), Status.Burn);
            assertNotEquals(hp, m.getHP());
        }
    }

    @Test
    public void test__applyStatus__2() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            int hp = m.getHP();
            m.setStatus(Status.Poison);
            m.applyStatusDamage();
            assertEquals(m.getStatus(), Status.Poison);
            assertNotEquals(hp, m.getHP());
        }
    }

    @Test
    public void test__applyStatus__3() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            int spd = m.getSpeed();
            m.setStatus(Status.Paralysis);
            assertEquals(m.getStatus(), Status.Paralysis);
            assertNotEquals(spd, m.getSpeed());
        }
    }

    @Test
    public void test__cureStatus__1() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            Status s = Status.Paralysis;
            m.setStatus(s);
            assertEquals(m.getStatus(), s);
            for (Status stat : Status.values()) {
                if (stat != s) {
                    assertEquals(false, m.cureStatus(stat));
                }
            }
            boolean cured = m.cureStatus(s);
            assertEquals(m.getStatus(), Status.Normal);
            assertEquals(cured, true);
        }
    }

    @Test
    public void test__cureStatus__2() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            Status s = Status.Sleep;
            m.setStatus(s);
            assertEquals(m.getStatus(), s);
            for (Status stat : Status.values()) {
                if (stat != s) {
                    assertEquals(false, m.cureStatus(stat));
                }
            }
            boolean cured = m.cureStatus(s);
            assertEquals(m.getStatus(), Status.Normal);
            assertEquals(cured, true);
        }
    }

    @Test
    public void test__cureStatus__3() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            Status s = Status.Poison;
            m.setStatus(s);
            assertEquals(m.getStatus(), s);
            for (Status stat : Status.values()) {
                if (stat != s) {
                    assertEquals(false, m.cureStatus(stat));
                }
            }
            boolean cured = m.cureStatus(s);
            assertEquals(m.getStatus(), Status.Normal);
            assertEquals(cured, true);
        }
    }

    @Test
    public void test__cureStatus__4() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            Status s = Status.Burn;
            m.setStatus(s);
            assertEquals(m.getStatus(), s);
            for (Status stat : Status.values()) {
                if (stat != s) {
                    assertEquals(false, m.cureStatus(stat));
                }
            }
            boolean cured = m.cureStatus(s);
            assertEquals(m.getStatus(), Status.Normal);
            assertEquals(cured, true);
        }
    }

    @Test
    public void test__cureStatus__5() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            assertEquals(m.getStatus(), Status.Normal);
            Status s = Status.Freeze;
            m.setStatus(s);
            assertEquals(m.getStatus(), s);
            for (Status stat : Status.values()) {
                if (stat != s) {
                    assertEquals(false, m.cureStatus(stat));
                }
            }
            boolean cured = m.cureStatus(s);
            assertEquals(m.getStatus(), Status.Normal);
            assertEquals(cured, true);
        }
    }

    @Test
    public void test__revive__1() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            m.receiveAttack(Integer.MAX_VALUE);
            assertEquals(m.isAlive(), false);
            assertEquals(m.revive(50.0), true);
            assertEquals(m.isAlive(), true);
            assertEquals(m.getPercentHP(), 50.0, 0.1);
        }
    }

    @Test
    public void test__revive__2() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            m.receiveAttack(Integer.MAX_VALUE);
            assertEquals(m.isAlive(), false);
            assertEquals(m.revive(50), true);
            assertEquals(m.isAlive(), true);
            assertEquals(m.getHP(), 50, 0);
        }
    }

    @Test
    public void test__heal__1() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            int hp = m.getHP();
            m.receiveAttack(hp / 2);
            assertEquals(m.isAlive(), true);
            assertEquals(m.restoreHP(hp / 2), true);
            assertEquals(m.getHP(), hp, 0);
        }
    }

    @Test
    public void test__heal__2() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            double hp = m.getPercentHP();
            int dmg = m.getHP();
            m.receiveAttack(dmg / 2);
            assertEquals(m.isAlive(), true);
            assertEquals(m.restoreHP(1.0), true);
            assertEquals(m.getPercentHP(), hp, 0);
        }
    }

    @Test
    public void test_move_5() {
        for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            // System.out.println(id.toString());
            for (Move move : m.getMoves().values()) {
                assertEquals(move.getPP(), move.getMaxPP());
                assertEquals(move.use(), true);
                assertNotEquals(move.getPP(), move.getMaxPP());
            }
        }
    }
    
    @Test
    public void test_move_6(){
    	for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
            for (Move move : m.getMoves().values()) {
                while(move.use());
                assertEquals(move.getPP(), 0);
            }
            for (Move move : m.getMoves().values()) {
                assertEquals(move.getPP(), 0);
            }
        }
    }
    
	@Test
	public void test_move_7(){
		for (MonsterID id : MonsterID.values()) {
	        Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
	        for (Attack atk : m.listMoves()) {
	            while(m.useMove(atk));
	            assertEquals(m.getPPOn(atk), 0);
	        }
	        for (Attack atk : m.listMoves()) {
	            assertEquals(m.getPPOn(atk), 0);
	        }
	    }
		for (MonsterID id : MonsterID.values()) {
	        Monster m = MonsterSet.getMonster(id);// MonsterSet.getRandomMonster();
	        for (Attack atk : m.listMoves()) {
	            assertEquals(m.getPPOn(atk), m.getMaxPPOn(atk));
	        }
	    }
	}
    
    @Test
    public void test_move_8(){
    	for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);
            Monster n = MonsterSet.getMonster(id);
            for(Attack atk : m.listMoves()){
            	m.useMove(atk);
            	assertNotEquals(m.getPPOn(atk), n.getPPOn(atk));
            }
    	}
    	//System.out.println("---------------");
    	for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);
            for(Attack atk : m.listMoves()){
            	assertEquals(m.getPPOn(atk), m.getMaxPPOn(atk));
            }
    	}
    }
    
    @Test
    public void test__unique__1(){
    	for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);
            m.receiveAttack(100);
            assertNotEquals(m.getHP(), m.getMaxHP());
    	}
    	for (MonsterID id : MonsterID.values()) {
            Monster m = MonsterSet.getMonster(id);
           assertEquals(m.getHP(), m.getMaxHP());
    	}
    }
   
    @Test
    public void test__unique__2(){
    	for(MonsterID id : MonsterID.values()){
    		System.out.println(id);
    		Monster m = MonsterSet.getMonster(id);
    		List<String> atks = new ArrayList<>();
    		List<String> movs = new ArrayList<>();
    		for(Attack atk : m.listMoves()){
    			atks.add(atk.toString());
    		}
    		for(Move mov :m.getMoves().values()){
    			movs.add(mov.toAttack().toString());
    		}
    		for(int i = 0; i < atks.size(); i++){
    			assertEquals(atks.get(i).equals(movs.get(i)), true);
    		}
    	}
    }

}
