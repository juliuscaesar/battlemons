package general;

import java.io.BufferedReader;
import java.io.IOException;

import trainers.Trainer;

public class DTTrainer {

    DTTrainerInterface decision_tree;

    public class DTTrainerInterface extends DT<Decision> {

        // Constructor.
        DTTrainerInterface(String filename) {
            super(filename);
        }

        /********* Conditions below ***********/

        public class Condition_OpponentCanKillMonster extends Condition {

            // Constructor
            public Condition_OpponentCanKillMonster(BufferedReader br,
                    String class_prefix) throws IOException {
                super(br, class_prefix);
            }

            boolean check_condition(Battle battle, Trainer user) {
                return true; // TODO
            }

        }

        /******** implemented behaviors ***********/

        public class Behavior_UseBestAttack extends Behavior {
            Decision execute(Battle battle, Trainer user) {
                return null; // TODO
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
