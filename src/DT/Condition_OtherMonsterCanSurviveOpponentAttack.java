package DT;

import general.Battle;
import monsters.Monster;
import damage.Damage;
import general.Attack;
import trainers.Trainer;
import trainers.Item;
import trainers.ItemEnum;

// Returns true if the user has a monster on their team that can survive
// any attack the opponent's monster can throw at it on the next turn.
public class Condition_OtherMonsterCanSurviveOpponentAttack extends
        Condition {

    // Constructor
    public Condition_OtherMonsterCanSurviveOpponentAttack() {
        super();
        uses_parameter = false;
    }

    boolean check_condition(Battle battle, Trainer user) {
        Monster opponent = battle.getOpponentsMonster(user);
        Damage d = new Damage();

        // Check for a monster that can survive the opponent
        for (Monster m : user.listMonsters()) {

            if (!m.isAlive()) continue;

            // Track the most damage the opponent can deal
            double highestDamage = 0;

            // Find the attack with the highest damage
            for (Attack a : opponent.listMoves()) {
                double possibleDamage = d.highestPossibleDamage(a,
                        opponent, m);
                if (possibleDamage > highestDamage) {
                    highestDamage = possibleDamage;
                }
            }

            // If the highest damage possible is less health than this
            // monster has, it's viable!
            if (highestDamage < m.getHP()) return true;
        }

        // If no viable monsters were found...
        return false;
    }
}
