package DT;


import general.Attack;
import general.Battle;
import general.Decision;

import monsters.Monster;

import moves.MoveSet;

import trainers.EtherItem;

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
