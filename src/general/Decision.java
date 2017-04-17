package general;

import trainers.Antidote;
import general.BattleVariables;
import damage.Hit;
import damage.Damage;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.EtherItem;
import trainers.FreshwaterItem;
import trainers.IceHealItem;
import trainers.ParalyzHealItem;
import trainers.Trainer;
import monsters.Monster;
import moves.Move;
import moves.MoveSet;

public abstract interface Decision {

    abstract void executeDecision(Battle b, Trainer user);

    abstract public String toString();

    public class ChangeMonster implements Decision {

        Monster new_monster;

        public ChangeMonster(Monster new_monster) {
            this.new_monster = new_monster;
        }

        public void executeDecision(Battle b, Trainer user) {
            if (BattleVariables.printEachTurn) {
                System.out.println(user.name + " sent out "
                        + new_monster.getID());
            }
            user.changeActive(new_monster);
        }

        public String toString() {
            return "Change Monster to " + new_monster + ": "
                    + new_monster.getID().toString();
        }

    }

    public class UseIceHealItem implements Decision {

        IceHealItem item_to_use;

        public UseIceHealItem(IceHealItem cureFreeze, Monster monster) {
            this.item_to_use = cureFreeze;

        }

        public void executeDecision(Battle b, Trainer user) {
            item_to_use.useItem(user.getActiveMonster());
        }

        public String toString() {
            return "Use Ice Heal";
        }

    }

    public class UsePoisonHealItem implements Decision {

        Antidote item_to_use;

        public UsePoisonHealItem(Antidote curePoison, Monster monster) {
            this.item_to_use = curePoison;

        }

        public void executeDecision(Battle b, Trainer user) {
            item_to_use.useItem(user.getActiveMonster());
        }

        public String toString() {
            return "Use Antidote";
        }
    }

    public class UseSleepHealItem implements Decision {

        AwakeningItem item_to_use;

        public UseSleepHealItem(AwakeningItem cureSleep, Monster monster) {
            this.item_to_use = cureSleep;

        }

        public void executeDecision(Battle b, Trainer user) {
            item_to_use.useItem(user.getActiveMonster());
        }

        public String toString() {
            return "Use Awakening";
        }
    }

    public class UseBurnHealItem implements Decision {

        BurnHealItem item_to_use;

        public UseBurnHealItem(BurnHealItem cureBurn, Monster monster) {
            this.item_to_use = cureBurn;

        }

        public void executeDecision(Battle b, Trainer user) {
            item_to_use.useItem(user.getActiveMonster());
        }

        public String toString() {
            return "Use Burn Heal";
        }
    }

    public class UseParalyzHealItem implements Decision {

        ParalyzHealItem item_to_use;

        public UseParalyzHealItem(ParalyzHealItem cureParalysis, Monster monster) {
            this.item_to_use = cureParalysis;

        }

        public void executeDecision(Battle b, Trainer user) {
            item_to_use.useItem(user.getActiveMonster());
        }

        public String toString() {
            return "Use Paralyze Heal";
        }
    }

    public class UseHealHPItem implements Decision {

        FreshwaterItem item_to_use;

        public UseHealHPItem(FreshwaterItem replenishHP, Monster monster) {
            this.item_to_use = replenishHP;

        }

        public void executeDecision(Battle b, Trainer user) {
            item_to_use.useItem(user.getActiveMonster());
        }

        public String toString() {
            return "Use Fresh Water";
        }
    }

    public class UseHealPPItem implements Decision {

        EtherItem item_to_use;
        Move move_to_replenish;

        public UseHealPPItem(EtherItem replenishPP, Move move) {
            this.item_to_use = replenishPP;
            this.move_to_replenish = move;
        }

        public void executeDecision(Battle b, Trainer user) {
            item_to_use.useItemOnMove(user.getActiveMonster(),
                    move_to_replenish);
        }

        public String toString() {
            return "Use Ether";
        }
    }

    public class UseMove implements Decision {

        Move move_to_use;

        // Attack attack_to_use;

        public UseMove(Move move) {
            this.move_to_use = move;

        }

        public void executeDecision(Battle b, Trainer user) {

            Hit h = new Hit();
            Damage d = new Damage();
            Attack attackToUse = move_to_use.toAttack();
            Monster userMon = user.getActiveMonster();
            Monster enemyMon = b.getOpponentsMonster(user);

            if (BattleVariables.printEachTurn) {
                System.out.println("  " + user.name + "\'s "
                        + user.getActiveMonster().getID() + " used "
                        + move_to_use.toAttack() + "!");
            }
            if (!h.hit(attackToUse, userMon, enemyMon,
                    d.getDamage(attackToUse, userMon, enemyMon), b.rng_move)) {
                if (BattleVariables.printEachTurn) {
                    System.out.println("  But it missed!");
                }
            }
            user.getActiveMonster().useMove(move_to_use.toAttack());
        }

        public String toString() {
            return "Use move " + move_to_use.toAttack().toString();
        }
    }

    public class Struggle implements Decision {

        public void executeDecision(Battle b, Trainer user) {

            Hit h = new Hit();
            Damage d = new Damage();
            Attack attackToUse = Attack.Struggle;
            Monster userMon = user.getActiveMonster();
            Monster enemyMon = b.getOpponentsMonster(user);
            int enemyStartingHP = enemyMon.getHP();

            if (BattleVariables.printEachTurn) {
                System.out.println("  " + user.name + "\'s " + userMon.getID()
                        + " used " + attackToUse + "!");
            }
            if (!h.hit(attackToUse, userMon, enemyMon,
                    d.getDamage(attackToUse, userMon, enemyMon), b.rng_move)) {
                if (BattleVariables.printEachTurn) {
                    System.out.println("  But it missed!");
                }
            }
            int diffDmg = enemyStartingHP - enemyMon.getHP();
            diffDmg /= 2;
            userMon.receiveAttack(diffDmg);
            if (BattleVariables.printEachTurn) {
                System.out.println("  " + user.name + "\'s " + userMon.getID()
                        + " was hurt by the recoil!");
            }
            // don't change PP!

        }

        public String toString() {
            return "Struggle";
        }

    }
}
