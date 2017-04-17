package DT;

import java.util.List;


import general.Battle;
import general.Decision;

import monsters.Monster;
import trainers.Trainer;

public class Behavior_ChangeToMonsterWithHighSurvivability extends
        Behavior {
    public Decision execute(Battle battle, Trainer user) {
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
