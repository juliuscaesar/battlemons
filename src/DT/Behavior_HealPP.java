package DT;

import java.util.List;

import damage.Damage;
import general.Attack;
import general.Battle;
import general.Decision;
import general.Element;
import general.Status;
import monsters.Monster;
import moves.Move;
import moves.MoveSet;
import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.EtherItem;
import trainers.FreshwaterItem;
import trainers.IceHealItem;
import trainers.ParalyzHealItem;
import trainers.Trainer;

public class Behavior_HealPP extends Behavior {
   public Decision execute(Battle battle, Trainer user) {

        
        EtherItem replenishPP = new EtherItem();
        Monster monster = user.getActiveMonster();

        
      //  int lowestPP = MoveSet.getMove(lowestPPAttack).getPP();

        // Track lowest HP value found and the associated monster
        int lowestPP = Integer.MAX_VALUE;
        Attack lowestPPAttack = null;

        // Iterate through monsters
        for (Attack a : monster.listMoves()) {
            int movePP = MoveSet.getMove(a).getPP();
            if (movePP > 0 && movePP < lowestPP) {
            	lowestPP = movePP;
            	lowestPPAttack = a;
            }
        } 
      

        return new Decision.UseHealPPItem(replenishPP, MoveSet.getMove(lowestPPAttack));
    }
}
