package damage;

import java.util.Random;

import general.Attack;
import general.Status;
import monsters.Monster;
import moves.Move;
import moves.MoveSet;

/**
 * Hit Class. Class used to calculate the chance of a move landing on a target
 * enemy. This class also has a method to effectively execute a move.
 * 
 * For matters of accuracy, this method converts all values to double, and only
 * when returning them, it will convert to an integer value.
 * 
 */
public class Hit {
    private MoveSet set;
    private Random rnd;

    public Hit() {
        set = new MoveSet();
        rnd = new Random();
    }

    /**
     * This method will return a value of 0 to 100 of the chance that this move
     * have to hit the target monster.
     * 
     * @param atk
     *            is identifier for the Source Monster's Move.
     * @param source
     *            is the Variable that holds the Source Monster.
     * @param target
     *            is the Variable that holds the Target Monster
     * @return an integer value between 0 or 100, for the chance that this move
     *         has to hit the target monster.
     */
    public int getHitChance(Attack atk, Monster source, Monster target) {
        Move move = set.move(atk);
        double moveAcc = move.getAcc();
        // double sourceAcc = source.getAcc();

        return (int) moveAcc;
    }

    /**
     * This method will execute a hit, if it hits, it will try to apply the
     * move's status to the target monster. It will return true if the attack
     * landed, false if it missed.
     * 
     * This method will roll a random integer number from 0 to 100, if this
     * value is smaller or equals than the actual accuracy rate the move, the
     * move will land and then decrease the target's Hp (Hit Points) by the
     * received {@code dmg} amount. If a move lands, the method will then
     * calculate if the move should apply its status, or not. The method will
     * roll another random integer number from 0 to 100, if this value is
     * smaller or equals than the actual move's status accuracy rate, the target
     * monster will be set for this move's Status.
     * 
     * Notes: The class could implement without the Damage parameter, and call
     * the Damage class, and with the other 3 variables, calculate the damage
     * alone, but this way, the damage can be calculated, and whatever program
     * is using this method, can precisely know how much damage the Target
     * Monster is going to receive. A version of this method can be written
     * without the parameter {@code dmg}, but the user would have to calculate
     * by himself, how much damage the target monster took.
     * 
     * Notes(2): This method will assume that: The Move has enough PP to perform
     * this attack The Target monster is alive. The Source monster is alive. The
     * damage value was calculate by the Damage Class. Specifically by the (int
     * Damage.getDamage) method. Both the source and target monsters are valid
     * monsters.
     * 
     * @param atk
     *            is identifier for the Source Monster's Move.
     * @param source
     *            is the Variable that holds the Source Monster.
     * @param target
     *            is the Variable that holds the Target Monster
     * @param dmg
     *            is the value that should be decreased from the Target Monster,
     *            should this move hit. This damage is to be calculate by Damage
     *            Class.
     * @return true if the move landed, false if the move didn't land.
     */
    public boolean hit(Attack atk, Monster source, Monster target, int dmg) {
        int chance = getHitChance(atk, source, target);
        int value = Math.abs(rnd.nextInt(100));
        boolean gotHit = (value <= chance);
        if (gotHit) {
            target.receiveAttack(dmg);
            Move m = set.move(atk);
            if (m.getStatus() != Status.Normal) {
                int valueStat = Math.abs(rnd.nextInt(100));
                if (valueStat < m.getStatusChance()) {
                    target.setStatus(m.getStatus());
                }
            }
        }
        return gotHit;
    }
}
