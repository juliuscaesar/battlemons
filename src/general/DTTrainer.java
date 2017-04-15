package general;

import damage.Damage;
import monsters.Monster;
import moves.MoveSet;
import trainers.Item;
import trainers.ItemEnum;
import trainers.Trainer;

public class DTTrainer {

    DTTrainerInterface decision_tree;

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

        public class Condition_UserHasHealItem extends Condition {

            // Constructor
            public Condition_UserHasHealItem(Node true_child, Node false_child,
                    float parameter) {
                super(true_child, false_child, parameter);
                uses_parameter = false;
            }

            boolean check_condition(Battle battle, Trainer user) {
                for (Item i : user.listItems()) {
                    if (i.quantity() > 0
                            && i.getItemEnum() == ItemEnum.FreshWater) {
                        return true;
                    }
                }
                return false;
            }
        }

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
                for (Attack a : opponent.listMoves()) {
                    double possibleDamage = d.highestPossibleDamage(a,
                            opponent, user.getActiveMonster());
                    if (possibleDamage > user.getActiveMonster().getHP()) {
                        return true;
                    }
                }
                return false;
            }
        }

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
                Attack strongestMove = d.getStrongestMove(user
                        .getActiveMonster().listMoves(), user
                        .getActiveMonster(), battle.getOpponentsMonster(user));
                float percentPP = MoveSet.getMove(strongestMove).getPP()
                        / MoveSet.getMove(strongestMove).getMaxPP();
                return percentPP < parameter;
            }
        }

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

        /******** implemented behaviors ***********/

        /**
         * TEMPLATE public class Behavior_UseBestAttack extends Behavior {
         * Decision execute(Battle battle, Trainer user) { return null; // TODO
         * } }
         */

        public class Behavior_SwitchToMonsterWithLowestHP extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                int lowestHp = Integer.MAX_VALUE;
                Monster lowestHpMon = null;
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

        public class Behavior_SwitchToMonsterWithStrongType extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                Monster opponent = battle.p1 == user ? battle.p2
                        .getActiveMonster() : battle.p1.getActiveMonster();
                Element opposingType = opponent.getElem();
                if (Element.getMatchupValue(user.getActiveMonster().getElem(),
                        opposingType) > 1.) {
                    return null;
                }
                for (Monster m : user.listMonsters()) {
                    if (m.isAlive()
                            && Element.getMatchupValue(m.getElem(),
                                    opposingType) > 1.) {
                        return new Decision.ChangeMonster(m);
                    }
                }
                return null;
            }
        }

        public class Behavior_SwitchToMonsterWithWeakType extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                Monster opponent = battle.p1 == user ? battle.p2
                        .getActiveMonster() : battle.p1.getActiveMonster();
                Element opposingType = opponent.getElem();
                if (Element.getMatchupValue(user.getActiveMonster().getElem(),
                        opposingType) < 1.) {
                    return null;
                }
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

        public class Behavior_SwitchToMonsterWithBestAttack extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                double highestDmg = 0;
                Monster highestDmgMon = null;
                Monster opponent = battle.getOpponentsMonster(user);
                Damage d = new Damage();
                for (Monster m : user.listMonsters()) {
                    if (!m.isAlive()) continue;
                    for (Attack a : m.listMoves()) {
                        double potentialDmg = d.highestPossibleDamage(a, m,
                                opponent);
                        if (potentialDmg > highestDmg) {
                            highestDmg = potentialDmg;
                            highestDmgMon = m;
                        }
                    }
                }
                if (highestDmgMon == null) return null;
                return new Decision.ChangeMonster(highestDmgMon);
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
