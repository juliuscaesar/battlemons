package DT;

import java.util.List;

import damage.Damage;
import general.Attack;
import general.Battle;
import general.Decision;
import general.Element;
import general.Status;
import monsters.Monster;
import moves.MoveSet;
import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.EtherItem;
import trainers.FreshwaterItem;
import trainers.IceHealItem;
import trainers.ParalyzHealItem;
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
