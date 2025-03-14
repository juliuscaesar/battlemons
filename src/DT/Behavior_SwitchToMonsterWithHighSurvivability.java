package DT;

import general.Battle;
import general.Decision;

import monsters.Monster;
import trainers.Trainer;

public class Behavior_SwitchToMonsterWithHighSurvivability extends Behavior {
    public Decision execute(Battle battle, Trainer user) {
        Monster opponent = battle.getOpponentsMonster(user);
        Monster bestMon = user.getActiveMonster();
        int highestScore = bestMon.calculateSurvivability(opponent);
        for (Monster m : user.listMonsters()) {

            if (!m.isAlive() || m == user.getActiveMonster()) continue;

            int survivability = m.calculateSurvivability(opponent);

            if (survivability > highestScore) {
                bestMon = m;
                highestScore = survivability;
            }
        }
        if (bestMon == user.getActiveMonster()) {
            return new Decision.ChangeMonster(bestMon);
        } else
            return null;
    }
}
