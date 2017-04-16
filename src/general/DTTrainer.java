package general;

import java.util.List;
import damage.Damage;
import monsters.Monster;
import moves.MoveSet;
import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.EtherItem;
import trainers.FreshwaterItem;
import trainers.IceHealItem;
import trainers.Item;
import trainers.ItemEffect;
import trainers.ItemEnum;
import trainers.ParalyzHealItem;
import trainers.Trainer;

/**
 * AI for a trainer. Contains an instance of a DTTrainerInterface, which itself
 * is an implementation of a generic Decision Tree type (DT). Access to the
 * decision tree should be done with makeDecision().
 */
public class DTTrainer {

    public DTTrainerInterface decision_tree;

    // Decision tree implementation that returns specific Decisions; Condition
    // (branch) and Behavior (leaf) implementations are enclosed
    public class DTTrainerInterface extends DT<Decision> {

        /********* Conditions below ***********/

        /**
         * TEMPLATE public class Condition_OpponentCanKillMonster extends
         * Condition {
         * 
         * // Constructor
         * 
         * public Condition_HealthGreaterThanPercent(Node true_child, Node
         * false_child, float parameter) { super(true_child, false_child,
         * parameter); upper_bound = 1.0; lower_bound = 0.0; uses_parameter =
         * true; }
         * 
         * boolean check_condition(Battle battle, Trainer user) { return true; }
         * 
         * }
         */

        // Returns true if the user's current monster's health percentage is at
        // least the parameter.
        public class Condition_HealthGreaterThanPercent extends Condition {

            // Constructor
            public Condition_HealthGreaterThanPercent(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = 1.0;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentMon = user.getActiveMonster();
                return currentMon.getPercentHP() > parameter;
            }
        }

        // Returns true if the user's current monster's health is at
        // least the parameter.
        public class Condition_HealthGreaterThanValue extends Condition {

            // Constructor
            public Condition_HealthGreaterThanValue(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = Double.MAX_VALUE;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentMon = user.getActiveMonster();
                return currentMon.getHP() > parameter;
            }
        }

        // Returns true if the current user has at least one Fresh Water in
        // their inventory.
        public class Condition_UserHasHealItem extends Condition {

            // Constructor
            public Condition_UserHasHealItem(Node true_child, Node false_child,
                    float parameter) {
                super(true_child, false_child, parameter);
                uses_parameter = false;
            }

            boolean check_condition(Battle battle, Trainer user) {

                // Iterate across items
                for (Item i : user.listItems()) {

                    // The only healing item is the Fresh Water
                    if (i.quantity() > 0
                            && i.getItemEnum() == ItemEnum.FreshWater) {
                        return true;
                    }
                }
                return false;
            }
        }

        // Returns true if the opponent's current monster has a move that can
        // kill the user's current monster on the next turn.
        public class Condition_OpponentCanKillMonster extends Condition {

            // Constructor
            public Condition_OpponentCanKillMonster(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                uses_parameter = false;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster opponent = battle.getOpponentsMonster(user);
                Damage d = new Damage();

                // Iterate through opponent's moves
                for (Attack a : opponent.listMoves()) {

                    // Check possible damage dealt by this move; if it exceeds
                    // the current monster's health, it's dead
                    double possibleDamage = d.highestPossibleDamage(a,
                            opponent, user.getActiveMonster());
                    if (possibleDamage > user.getActiveMonster().getHP()) {
                        return true;
                    }
                }
                return false;
            }
        }

        // Returns true if the user has a monster on their team that can survive
        // any attack the opponent's monster can throw at it on the next turn.
        public class Condition_OtherMonsterCanSurviveOpponentAttack extends
                Condition {

            // Constructor
            public Condition_OtherMonsterCanSurviveOpponentAttack(
                    Node true_child, Node false_child, float parameter) {
                super(true_child, false_child, parameter);
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

        // Returns true if the user's active monster's strongest attack (the
        // attack that will deal the most damage to the opponent) has a
        // percentage of PP lower than parameter.
        public class Condition_BestAttackHasLowPP extends Condition {

            // Constructor
            public Condition_BestAttackHasLowPP(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                uses_parameter = true;
                upper_bound = 1.0;
                lower_bound = 0.0;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Damage d = new Damage();

                // Get the strongest move and check how much PP is left as a
                // percentage of max PP
                Attack strongestMove = d.getStrongestMove(user
                        .getActiveMonster().listMoves(), user
                        .getActiveMonster(), battle.getOpponentsMonster(user));
                float percentPP = MoveSet.getMove(strongestMove).getPP()
                        / MoveSet.getMove(strongestMove).getMaxPP();

                return percentPP < parameter;
            }
        }

        // Returns true if the user's active monster has at least one move with
        // no PP left.
        public class Condition_IfSomeMoveHasNoPP extends Condition {

            // Constructor
            public Condition_IfSomeMoveHasNoPP(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                uses_parameter = false;
            }

            boolean check_condition(Battle battle, Trainer user) {
                for (Attack a : user.getActiveMonster().listMoves()) {
                    if (MoveSet.getMove(a).getPP() == 0) {
                        return true;
                    }
                }
                return false;
            }

        }

        /**
         * Check if the opponent's health is lower than x%
         */
        public class Condition_OpponentHealthLowerThanPercent extends Condition {

            // Constructor
            public Condition_OpponentHealthLowerThanPercent(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = 1.0;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentMon = battle.p2.getActiveMonster();
                return currentMon.getPercentHP() <= parameter;
            }
        }

        /**
         * Check if the opponent's health is lower than x
         */
        public class Condition_OpponentHealthLowerThanValue extends Condition {

            // Constructor
            public Condition_OpponentHealthLowerThanValue(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = Double.MAX_VALUE;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentMon = battle.p2.getActiveMonster();
                return currentMon.getHP() <= parameter;
            }
        }

        public class Condition_StatStageLowerThanValue extends Condition {

            // Constructor
            public Condition_StatStageLowerThanValue(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = Double.MAX_VALUE;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentMon = user.getActiveMonster();
                return currentMon.getHP() <= parameter;
            }
        }

        public class Condition_StatStageGreaterThanValue extends Condition {

            // Constructor
            public Condition_StatStageGreaterThanValue(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = Double.MAX_VALUE;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentMon = user.getActiveMonster();
                return currentMon.getHP() <= parameter;
            }
        }

        public class Condition_IsStatusNormal extends Condition {
            public Condition_IsStatusNormal(Node true_child, Node false_child,
                    float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = Double.MAX_VALUE;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentMon = user.getActiveMonster();
                return (currentMon.getStatus() == Status.Normal);
            }
        }

        public class Condition_IsOpponentStatusNormal extends Condition {
            public Condition_IsOpponentStatusNormal(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = Double.MAX_VALUE;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentOpponentMon = battle.getOpponentsMonster(user);
                return (currentOpponentMon.getStatus() == Status.Normal);
            }
        }

        public class Condition_IsStatusNotNormal extends Condition {
            public Condition_IsStatusNotNormal(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = Double.MAX_VALUE;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentMon = user.getActiveMonster();
                return (currentMon.getStatus() != Status.Normal);
            }
        }

        public class Condition_IsOpponentStatusNotNormal extends Condition {
            public Condition_IsOpponentStatusNotNormal(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = Double.MAX_VALUE;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentOpponentMon = battle.getOpponentsMonster(user);
                return (currentOpponentMon.getStatus() != Status.Normal);
            }
        }

        public class Condition_CanStatusHealItself extends Condition {
            public Condition_CanStatusHealItself(Node true_child,
                    Node false_child, float parameter) {
                super(true_child, false_child, parameter);
                upper_bound = Double.MAX_VALUE;
                lower_bound = 0.0;
                uses_parameter = true;
            }

            boolean check_condition(Battle battle, Trainer user) {
                Monster currentMon = user.getActiveMonster();
                List<Item> itemsOnUser = user.listItems();

                for (Item item : itemsOnUser) {
                    if (currentMon.getStatus().equals(Status.Poison)
                            && item.getItemEnum().equals(ItemEnum.Antidote)) {
                        return true;
                    }

                    if (currentMon.getStatus().equals(Status.Burn)
                            && item.getItemEnum().equals(ItemEnum.BurnHeal)) {
                        return true;
                    }

                    if (currentMon.getStatus().equals(Status.Freeze)
                            && item.getItemEnum().equals(ItemEnum.IceHeal)) {
                        return true;
                    }

                    if (currentMon.getStatus().equals(Status.Paralysis)
                            && item.getItemEnum().equals(ItemEnum.ParalyzHeal)) {
                        return true;
                    }

                    if (currentMon.getStatus().equals(Status.Poison)
                            && item.getItemEnum().equals(ItemEnum.Antidote)) {
                        return true;
                    }

                }

                return false;
            }

        }

        /******** implemented behaviors ***********/

        /**
         * TEMPLATE public class Behavior_UseBestAttack extends Behavior {
         * Decision execute(Battle battle, Trainer user) { return null; } }
         */

        // Switches the current active monster to the monster on the user's
        // team with the lowest HP.
        public class Behavior_SwitchToMonsterWithLowestHP extends Behavior {
            Decision execute(Battle battle, Trainer user) {

                // Track lowest HP value found and the associated monster
                int lowestHp = Integer.MAX_VALUE;
                Monster lowestHpMon = null;

                // Iterate through monsters
                for (Monster m : user.listMonsters()) {
                    int monHp = m.getHP();
                    if (monHp > 0 && monHp < lowestHp) {
                        lowestHp = monHp;
                        lowestHpMon = m;
                    }
                }
                // TODO figure out how to handle nulls
                return new Decision.ChangeMonster(lowestHpMon);
            }
        }

        // Switches the current active monster to the monster on the user's team
        // with the highest HP.
        public class Behavior_SwitchToMonsterWithHighestHP extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                int highestHp = 0;
                Monster highestHpMon = null;
                for (Monster m : user.listMonsters()) {
                    int monHp = m.getHP();
                    if (monHp > 0 && monHp > highestHp) {
                        highestHp = monHp;
                        highestHpMon = m;
                    }
                }
                // TODO figure out how to handle nulls
                if (highestHpMon == null) {
                    return null;
                }
                return new Decision.ChangeMonster(highestHpMon);
            }
        }

        // Switches to any monster on the user's team whose type is strong
        // (defending) against the opponent's type (attacking).
        public class Behavior_SwitchToMonsterWithStrongType extends Behavior {
            Decision execute(Battle battle, Trainer user) {

                // Check opponent's type
                Monster opponent = battle.getOpponentsMonster(user);
                Element opposingType = opponent.getElem();

                // If the current matchup is strong, return null
                if (Element.getMatchupValue(user.getActiveMonster().getElem(),
                        opposingType) > 1.) {
                    return null;
                }

                // Look for a monster on the user's team with a strong matchup
                for (Monster m : user.listMonsters()) {
                    if (m.isAlive()
                            && Element.getMatchupValue(m.getElem(),
                                    opposingType) > 1.) {
                        return new Decision.ChangeMonster(m);
                    }
                }

                // if nothing's found, return null
                return null;
            }
        }

        // Switches to any monster on the user's team whose type is weak
        // (defending) against the opponent's type (attacking).
        public class Behavior_SwitchToMonsterWithWeakType extends Behavior {
            Decision execute(Battle battle, Trainer user) {

                // Check opponent's type
                Monster opponent = battle.getOpponentsMonster(user);
                Element opposingType = opponent.getElem();

                // If the current matchup is weak, return null
                if (Element.getMatchupValue(user.getActiveMonster().getElem(),
                        opposingType) < 1.) {
                    return null;
                }

                // Look for a monster on the user's team with a weak matchup
                for (Monster m : user.listMonsters()) {
                    if (m.isAlive()
                            && Element.getMatchupValue(m.getElem(),
                                    opposingType) < 1.) {
                        return new Decision.ChangeMonster(m);
                    }
                }
                return null;
            }
        }

        // Switches to the monster on the user's team with the strongest attack
        // (most damage dealt) to the opponent.
        public class Behavior_SwitchToMonsterWithBestAttack extends Behavior {
            Decision execute(Battle battle, Trainer user) {

                // Track highest damage and associated monster
                double highestDmg = 0;
                Monster highestDmgMon = null;

                Monster opponent = battle.getOpponentsMonster(user);
                Damage d = new Damage();

                // Iterate through user's monsters
                for (Monster m : user.listMonsters()) {

                    if (!m.isAlive()) continue;

                    // Iterate through monster's moves
                    for (Attack a : m.listMoves()) {

                        // Look for high damage moves
                        double potentialDmg = d.highestPossibleDamage(a, m,
                                opponent);
                        if (potentialDmg > highestDmg) {
                            highestDmg = potentialDmg;
                            highestDmgMon = m;
                        }
                    }
                }

                // If nothing was found, return null
                if (highestDmgMon == null) return null;

                return new Decision.ChangeMonster(highestDmgMon);
            }
        }

        /**
         * Pick a move that will raise the Battlemon's stats
         */
        // public class Behavior_RaiseStats extends Behavior {
        // Decision execute(Battle battle, Trainer user) {
        // List<Attack> moves = user.getActiveMonster().listMoves();
        // // finds the first move that will raise stats
        // for (Attack m : moves) {
        //
        // }
        // return Decision.UseMove();
        // }
        // }

        // public class Behavior_DropStats extends Behavior {
        // Decision execute(Battle battle, Trainer user) {
        // List<Attack> moves = user.getActiveMonster().listMoves();
        //
        // for (Attack m : moves) {
        // if (MoveSet.getMove(m).getStatus()
        // }
        // }
        // }

        /**
         * Select and use the attack that will inflict the least damage
         */
        public class Behavior_UseLowestDamageMove extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                double lowestDmg = 0;
                Monster monster = user.getActiveMonster();
                Monster opponent = battle.getOpponentsMonster(user);
                Damage d = new Damage();
                Attack lowestAttack = null;

                for (Attack attack : monster.listMoves()) {
                    double damage = d.lowestPossibleDamage(attack, monster,
                            opponent);
                    if (damage < lowestDmg) {
                        lowestDmg = damage;
                        lowestAttack = attack;
                    }
                }
                if (lowestAttack == null) return null;
                return new Decision.UseMove(MoveSet.getMove(lowestAttack));
            }
        }

        /**
         * Select and use the attack that will inflict the most damage
         */
        public class Behavior_UseHighestDamageMove extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                double highestDmg = 0;
                Monster monster = user.getActiveMonster();
                Monster opponent = battle.getOpponentsMonster(user);
                Damage d = new Damage();
                Attack highestAttack = null;

                for (Attack attack : monster.listMoves()) {
                    double damage = d.highestPossibleDamage(attack, monster,
                            opponent);
                    if (damage > highestDmg) {
                        highestDmg = damage;
                        highestAttack = attack;
                    }
                }
                if (highestAttack == null) return null;
                return new Decision.UseMove(MoveSet.getMove(highestAttack));
            }
        }

        public class Behavior_UseHighestAccuracyMove extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                int highestAcc = 0;
                Monster monster = user.getActiveMonster();

                Attack highestAttack = null;

                for (Attack attack : monster.listMoves()) {
                    int accuracy = MoveSet.getMove(attack).getAcc();
                    if (accuracy > highestAcc) {
                        highestAcc = accuracy;
                        highestAttack = attack;
                    }
                }
                if (highestAttack == null) return null;
                return new Decision.UseMove(MoveSet.getMove(highestAttack));
            }
        }

        public class Behavior_UseLowestAccuracyMove extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                int lowestAcc = 0;
                Monster monster = user.getActiveMonster();

                Attack lowestAttack = null;

                for (Attack attack : monster.listMoves()) {
                    int accuracy = MoveSet.getMove(attack).getAcc();
                    if (accuracy < lowestAcc) {
                        lowestAcc = accuracy;
                        lowestAttack = attack;
                    }
                }
                if (lowestAttack == null) return null;
                return new Decision.UseMove(MoveSet.getMove(lowestAttack));
            }
        }

        public class Behavior_HealHP extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                Monster activeMonsterOfUser = user.getActiveMonster();
                FreshwaterItem replenishHP = new FreshwaterItem();

                return new Decision.UseHealHPItem(replenishHP,
                        activeMonsterOfUser);
            }
        }

        public class Behavior_HealStatus extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                Monster activeMonsterOfUser = user.getActiveMonster();
                IceHealItem cureFreeze = new IceHealItem();
                AwakeningItem cureSleep = new AwakeningItem();
                BurnHealItem cureBurn = new BurnHealItem();
                ParalyzHealItem cureParalysis = new ParalyzHealItem();
                Antidote curePoison = new Antidote();

                if (activeMonsterOfUser.getStatus().equals(Status.Freeze)) {
                    return new Decision.UseIceHealItem(cureFreeze,
                            activeMonsterOfUser);
                }

                if (activeMonsterOfUser.getStatus().equals(Status.Sleep)) {
                    return new Decision.UseSleepHealItem(cureSleep,
                            activeMonsterOfUser);
                }

                if (activeMonsterOfUser.getStatus().equals(Status.Burn)) {
                    return new Decision.UseBurnHealItem(cureBurn,
                            activeMonsterOfUser);
                }

                if (activeMonsterOfUser.getStatus().equals(Status.Paralysis)) {
                    return new Decision.UseParalyzHealItem(cureParalysis,
                            activeMonsterOfUser);
                }

                if (activeMonsterOfUser.getStatus().equals(Status.Poison)) {
                    return new Decision.UsePoisonHealItem(curePoison,
                            activeMonsterOfUser);
                }

                return null;
            }
        }

        public class Behavior_HealPP extends Behavior {
            Decision execute(Battle battle, Trainer user) {

                int lowestPP = 0;
                EtherItem replenishPP = new EtherItem();
                Monster monster = user.getActiveMonster();

                Attack lowestPPAttack = null;

                for (Attack attack : monster.listMoves()) {
                    int powerpoints = MoveSet.getMove(attack).getPP();
                    if (powerpoints < lowestPP) {
                        lowestPP = powerpoints;
                        lowestPPAttack = attack;
                    }
                }

                if (lowestPPAttack == null) return null;

                return new Decision.UseMove(MoveSet.getMove(lowestPPAttack));
            }
        }

        public class Behavior_InflictStatusEffect extends Behavior {
            Decision execute(Battle battle, Trainer user) {

                Monster monster = user.getActiveMonster();

                for (Attack attack : monster.listMoves()) {
                    if (MoveSet.getMove(attack).isStatusMove()) {
                        return new Decision.UseAttack(attack);
                    }

                }
                return null;

            }
        }

        public class Behavior_ChangeToMonsterWithHighSurvivability extends
                Behavior {
            Decision execute(Battle battle, Trainer user) {
                Monster opponentMon = battle.getOpponentsMonster(user);
                Monster monWithHighestSurvivabilityScore = user.listMonsters()
                        .get(0);
                List<Monster> monstersOnUser = user.listMonsters();

                for (int i = 1; i < monstersOnUser.size(); i++) {
                    if (monWithHighestSurvivabilityScore
                            .GetSurvivabilityScoreOfMonAgainstOpponent(opponentMon) < monstersOnUser
                            .get(i).GetSurvivabilityScoreOfMonAgainstOpponent(
                                    opponentMon)) {
                        monWithHighestSurvivabilityScore = monstersOnUser
                                .get(i);
                    }
                }

                return new Decision.ChangeMonster(
                        monWithHighestSurvivabilityScore);
            }
        }
    }

    /**
     * Controller interface to get a move from the DT
     */
    public Decision makeDecision(Battle battle, Trainer user, long timeDue) {
        return decision_tree.getBehavior(battle, user, timeDue);
    }

}
