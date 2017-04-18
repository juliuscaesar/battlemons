package DT;

import general.Attack;
import general.Battle;
import general.Decision;
import monsters.Monster;
import moves.Move;
import moves.MoveSet;
import trainers.EtherItem;
import trainers.FreshwaterItem;
import trainers.Item;
import trainers.ItemEnum;
import trainers.Trainer;

public class Behavior_HealPP extends Behavior {
    public Decision execute(Battle battle, Trainer user) {

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

        // Iterate through user's items
        for (Item i : user.items) {

            // If this item is of the type we need...
            if (i.getItemEnum() == ItemEnum.Ether) {

                // And they have at least one - use it
                if (i.quantity() > 0) {
                    return new Decision.UseHealPPItem((EtherItem) i.effect,
                            lowestPPMove);
                }
                // Otherwise do nothing
                else {
                    return null;
                }
            }
        }
        return null;
    }
}
