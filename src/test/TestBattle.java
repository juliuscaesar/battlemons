package test;

import static org.junit.Assert.*;

import org.junit.Test;

import trainers.Trainer;
import general.MonsterID;
import monsters.Monster;
import monsters.MonsterSet;
import general.Attack;
import general.Battle;
import general.Decision;
import moves.MoveSet;
import damage.Damage;

public class TestBattle {

    Damage d = new Damage();
    Battle battle;
    Monster a = MonsterSet.getMonster(MonsterID.Adnocana);
    Monster b = MonsterSet.getMonster(MonsterID.Emberfly);

    @Test
    public void test1() {
        for (Attack atk : a.listMoves()) {
            int dmg = d.getDamage(atk, a, b);
            assertNotEquals(dmg, 0);
            int hp = b.getHP();
            b.receiveAttack(dmg);
            assertNotEquals(hp, b.getHP());
            return;
        }
    }

    @Test
    public void test_turnOrder() {
        Battle battle = Battle.testBattle();

        // both players doing non-moves
        assertArrayEquals(battle.getTurnOrder(), new Trainer[] { battle.p1,
                battle.p2 });

        // p1 using a move, p2 isn't
        battle.p1.decision = new Decision.UseMove(MoveSet.getMove(battle.p1
                .getActiveMonster().listMoves().get(0)));
        assertArrayEquals(battle.getTurnOrder(), new Trainer[] { battle.p2,
                battle.p1 });

        // p2 using a move, p1 isn't
        battle.p1.decision = null;
        battle.p2.decision = new Decision.UseMove(MoveSet.getMove(battle.p2
                .getActiveMonster().listMoves().get(0)));
        assertArrayEquals(battle.getTurnOrder(), new Trainer[] { battle.p1,
                battle.p2 });

        // both are using moves;
        // p1 (Adnocana, 105) is faster than p2 (Emberfly, 90)
        battle.p1.decision = new Decision.UseMove(MoveSet.getMove(battle.p1
                .getActiveMonster().listMoves().get(0)));
        assertArrayEquals(battle.getTurnOrder(), new Trainer[] { battle.p1,
                battle.p2 });

        // both are using moves;
        // p2 (Adnocana, 105) is faster than p1 (Emberfly, 90)
        battle.p1.clearMonsters();
        battle.p1.addMonster(MonsterSet.getMonster(MonsterID.Emberfly));
        battle.p2.clearMonsters();
        battle.p2.addMonster(MonsterSet.getMonster(MonsterID.Adnocana));
        assertArrayEquals(battle.getTurnOrder(), new Trainer[] { battle.p2,
                battle.p1 });

        // both are using moves;
        // both monsters have same speed, result is up to rng
        battle.p2.clearMonsters();
        battle.p2.addMonster(MonsterSet.getMonster(MonsterID.Emberfly));
        battle.getTurnOrder();
        battle.getTurnOrder();
        battle.getTurnOrder();

    }

    @Test
    public void test_runBattle() {
        Battle battle = Battle.testBattle();
        // TODO
        assertTrue(battle.runBattle() >= 0.);
    }
}
