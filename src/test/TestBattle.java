package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import trainers.Item;
import trainers.Trainer;
import general.MonsterID;
import monsters.Monster;
import monsters.MonsterSet;
import moves.Move;
import general.Attack;
import general.Battle;
import general.Decision;
import general.Decision.UseMove;
import general.Element;
import moves.MoveSet;
import damage.Damage;

public class TestBattle {

    MonsterSet set = new MonsterSet();
    MoveSet moves = new MoveSet();
    Monster a = set.getMonster(MonsterID.Adnocana);
    Monster b = set.getMonster(MonsterID.Emberfly);
    Damage d = new Damage();

    Battle initBattle() {
        Trainer t1 = new Trainer("player", new ArrayList<Monster>() {
            {
                add(set.getMonster(MonsterID.Adnocana));
            }
        }, new ArrayList<Item>());
        Trainer t2 = new Trainer("enemy", new ArrayList<Monster>() {
            {
                add(set.getMonster(MonsterID.Emberfly));
            }
        }, new ArrayList<Item>());
        return new Battle(t1, t2);
    }

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
        Battle battle = initBattle();

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
        battle.p1.addMonster(set.getMonster(MonsterID.Emberfly));
        battle.p2.clearMonsters();
        battle.p2.addMonster(set.getMonster(MonsterID.Adnocana));
        assertArrayEquals(battle.getTurnOrder(), new Trainer[] { battle.p2,
                battle.p1 });

        // both are using moves;
        // both monsters have same speed, result is up to rng
        battle.p2.clearMonsters();
        battle.p2.addMonster(set.getMonster(MonsterID.Emberfly));
        battle.getTurnOrder();
        battle.getTurnOrder();
        battle.getTurnOrder();

    }

    @Test
    public void test_runBattle() {
        Battle battle = initBattle();
        // TODO
        assertTrue(battle.runBattle() >= 0.);
    }
}
