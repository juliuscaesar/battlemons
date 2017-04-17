package DT;

import general.Attack;
import general.Battle;
import general.Decision;
import monsters.Monster;
import moves.Move;
import moves.MoveSet;
import trainers.EtherItem;
import trainers.Trainer;

public class Behavior_HealPP extends Behavior {
    public Decision execute(Battle battle, Trainer user) {

        EtherItem replenishPP = new EtherItem();
        Monster monster = user.getActiveMonster();

        // Track lowest HP value found and the associated monster
        int lowestPP = Integer.MAX_VALUE;
        Move lowestPPMove = monster.getMoves().get(monster.listMoves().get(0));

        // Iterate through monsters
        for (Attack a : monster.listMoves()) {
            Move m = monster.getMoves().get(a);
            int movePP = m.getPP();
            if (movePP >= 0 && movePP < lowestPP) {
                lowestPP = movePP;
                lowestPPMove = m;
            }
        }

        return new Decision.UseHealPPItem(replenishPP,
                lowestPPMove);
    }
}
