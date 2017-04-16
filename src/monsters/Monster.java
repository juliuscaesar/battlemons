package monsters;

import java.util.Map;
import java.util.Map.Entry;

import java.util.Random;

import damage.Damage;
import general.Attack;
import general.Element;
import general.Status;
import general.MonsterID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import moves.Move;
import moves.MoveSet;

/**
 * Monster Class. A valid Monster should have: - 1 or 2 Elements (Note: current
 * project only support one element) - 6 Attributes ( Hit Points, Attack,
 * Special Attack, Defense, Special Defense, Speed) - 1 to 4 Moves.
 * 
 * A Monster is subjected to be inflicted with the one of the following Status:
 * { Poison, Burn, Freeze, Paralysis, Sleep }, and each will have a different
 * effect on the Monster. Status can inflict damage on a Monster per turn, alter
 * its status and prevent it from attacking.
 * 
 * A Monster is subjected to have to an item being used on item, items are as
 * follow: { Antidote, Awakening, Burn Heal, Ether, Fresh Water, Ice Heal,
 * Paralyze Heal }. Items in general work only as buffs for the Monster. In
 * general they can: heal a Status, revive a Monster, restore their Health ( Hit
 * Points ), restore the PP values for a move.
 * 
 * A Monster's move has their own Element, and that Element should inflict
 * multipliers to the damage when landing a hit on another Monster. A Monster's
 * should also be unable to move if { they can't move, they are not alive, that
 * move wanted to use has no more PP }.
 * 
 */
public final class Monster {

    private MonsterID name; // The Monster's Name.
    private Status status; // The Monter's Current Abnormal Status.
    private int statusStart; // The Round where the Monster got this Status.
    private int statusDuration; // The Duration for this Status.
    private Element e1; // Monster's Element 1.
    private Map<Attack, Move> moves = new HashMap<Attack, Move>(); // Map of
                                                                   // Moves for
                                                                   // this
                                                                   // Monster.
    private boolean canMove; // True if the Monster can move, false if not.
    private boolean alive;
    private final Attributes att;
    private int hp;
    private final int maxHP;
    private int survivabilityScore;

    /**
     * Constructor for a Monster. This method should be called only by the
     * MonsterSet function. But by not doing that, the only trouble you can run
     * into is created a over/under powered monster. Note, a Monster's
     * construction is not complete until you call the { Monster.addMoves }
     * method.
     * 
     * @param name
     *            is the MonsterID Enum for this Monster's name.
     * @param hp
     *            is this Monster's Max Hit Point / Health
     * @param atk
     *            is this Monster's default Attack Value.
     * @param spAtk
     *            is this Monster's default Special Attack Value.
     * @param def
     *            is this Monster's default Defense Value.
     * @param spDef
     *            is this Monster's default Special Defense Value.
     * @param spd
     *            is this Monster's default Speed Value.
     * @param elements
     *            is this Monster's Elements. If can be an array of 1 to 2
     *            Elements. //Currently our project only supports Monsters with
     *            1 element.
     */
    Monster(MonsterID name, int hp, int atk, int spAtk, int def, int spDef,
            int spd, Element... elements) {
        this.name = name;
        this.status = Status.Normal;
        this.hp = hp;
        this.maxHP = hp;
        this.canMove = true;
        att = new Attributes(atk, spAtk, spDef, def, spd);
        int i = 0;
        for (Element e : elements) {
            if (i == 0) {
                this.e1 = e;
            } else if (i == 1) {
            } else {
                throw new IllegalArgumentException("More than 2 Elements");
            }
            i++;
        }
        this.moves = new HashMap<>();
        this.alive = true;
    }

    /**
     * Copy constructor for the monster class;
     * 
     * @param other
     *            is another Monster.
     */
    Monster(Monster other) {
        this.name = other.name;
        this.status = Status.Normal;
        this.hp = other.getHP();
        this.maxHP = other.getHP();
        this.canMove = true;
        att = new Attributes(other.getAtk(), other.getSpAtk(),
                other.getSpDef(), other.getDef(), other.getSpeed());
        this.e1 = other.getElem();
        this.moves = other.moves;
        this.alive = true;
    }

    /**
     * Add four moves to the list of moves. [Important] This method must be
     * called after constructing a Monster, otherwise a Monster won't be able to
     * attack at all.
     * 
     * @param m1
     *            is the first move.
     * @param m2
     *            is the second move.
     * @param m3
     *            is the third move.
     * @param m4
     *            is the fourth move.
     */
    public void addMoves(Move m1, Move m2, Move m3, Move m4) {
        Map<Attack, Move> temp = new HashMap<>();
        temp.put(m1.toAttack(), m1);
        temp.put(m2.toAttack(), m2);
        temp.put(m3.toAttack(), m3);
        temp.put(m4.toAttack(), m4);
        moves = new HashMap<>(temp);
    }

    /**
     * Will check if the Monster is still alive, if not, reset the Monster.
     */
    private void checkAlive() {
        if (hp <= 0) {
            hp = 0;
            alive = false;
            resetStatus();
        }
    }

    /**
     * Recalculate the Monster's remaining HP.
     *
     * @param dmg
     *            is the value that should be decreased from the Monster's HP.
     */
    public void receiveAttack(int dmg){
		this.hp -= dmg;
		System.out.println("  It dealt " + dmg + " points of damage.");
		checkAlive();
	}

    public boolean useMove(Attack atk) {
        if (moves.isEmpty()) {
            throw new IllegalArgumentException(
                    "You need to add moves to a Monster before sending it to battle.");
        }
        if (moves.containsKey(atk)) {
            return moves.get(atk).use();
        }
        return false;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    /************************************************
     *
     * GET METHODS
     *
     * **********************************************/

    public boolean canMove() {
        return this.canMove;
    }

    /**
     * Check if the Monster is alive or not.
     *
     * @return true if the Monster is alive, false if not.
     */
    public boolean isAlive() {
        checkAlive();
        return this.alive;
    }

    /**
     * List all the Moves for this Monster.
     *
     * @return a list with AttackEnums for this Monster Moves.
     */
    public List<Attack> listMoves() {
        if (moves.isEmpty()) {
            throw new IllegalArgumentException(
                    "You need to add moves to a Monster before sending it to battle.");
        }
        List<Attack> list = new ArrayList<>();
        for (Entry<Attack, Move> e : moves.entrySet()) {
            list.add(e.getKey());
        }
        return list;
    }

    public Map<Attack, Move> getMoves() {
        if (moves.isEmpty()) {
            throw new IllegalArgumentException(
                    "You need to add moves to a Monster before sending it to battle.");
        }
        return this.moves;
    }

    public int getSpeed() {
        return att.getSpd(this.status);
    }

    public int getHP() {
        return this.hp;
    }

    /**
     * Returns the percent value of the in a double number in the range of
     * 0.0000 to 1.0000.
     * 
     * @return the percent value of the monster.
     */
    public double getPercentHP() {
        double current = (double) this.hp;
        double max = (double) this.maxHP;
        return (current / max);
    }

    public Status getStatus() {
        return this.status;
    }

    public MonsterID getID() {
        return this.name;
    }

    public int getAtk() {
        return this.att.getAtk();
    }

    public int getDef() {
        return this.att.getDef();
    }

    public int getSpDef() {
        return this.att.getSpDef();
    }

    public Element getElem() {
        return this.e1;
    }

    public int getSpAtk() {
        return this.att.getSpAtk();
    }

    /************************************************
     *
     * STATUS METHODS
     *
     * **********************************************/

    /**
     * Add the Status to a Monster.
     * 
     * Status usage on this Project. To make a Status effectively work. It
     * should be used a combination of all 3 public Status Method inside the
     * Monster class. { setStatus, applyStatusDamage, updateStatus }
     * updateStatus should be called in the beginning of the Turn.
     * applyDamageStatus should be called in the end of the Turn.
     * 
     * Not calling updateStatus will make the Status go on forever.
     * 
     * Not calling applyDamageStatus will make Status that should inflict damage
     * on Monster, deal no damage at all.
     *
     * @param stat
     *            the status.
     */
    public void setStatus(Status stat) {
        if (this.status == stat) {
            return;
        }
        this.statusStart = -1;
        this.status = stat;
        this.applyStatus();
        getStatusDuration();
    }

    /**
     * This method will apply the status debuff. Freeze: can't move. Sleep:
     * can't move. Paralysis: 25% chance of not moving. Cuts Speed in half.
     */
    private void applyStatus() {
        switch (this.status) {
        case Freeze: {
            this.canMove = false;
            return;
        }
        case Paralysis: {
            Random rng = new Random();
            int percent = Math.abs(rng.nextInt(100));
            if (percent < 25) {
                this.canMove = false;
            } else {
                this.canMove = true;
            }
            return;
        }
        case Sleep: {
            this.canMove = false;
            return;
        }
        default: {

        }
        }
    }

    /**
     * This method will apply the status damage Burn: takes damage to equal of
     * 16% of max HP. Poison: takes damage to equal of 16% of max HP.
     */
    public void applyStatusDamage() {
        switch (this.status) {
        case Burn: {
            this.hp = this.maxHP / 16;
            return;
        }
        case Poison: {
            this.hp = this.maxHP / 16;
            return;
        }
        default: {

        }
        }
        checkAlive();
    }

    /**
     * Calculate the Duration in rounds for a given Status.
     */
    private void getStatusDuration() {
        switch (this.status) {
        case Burn: {
            /*
             * 37.5% chance of lasting 2 rounds 37.5% chance of lasting 3 rounds
             * 12.5% chance of lasting 4 rounds 12.5% chance of lasting 5 rounds
             */
            Random rng = new Random();
            int minimum = 1;
            int percent = Math.abs(rng.nextInt(1000));
            if (percent < 375) {
                this.statusDuration = minimum;
            } else if (percent < 750) {
                this.statusDuration = minimum + 1;
            } else if (percent < 875) {
                this.statusDuration = minimum + 2;
            } else {
                this.statusDuration = minimum + 3;
            }
            // System.out.println("Rounds: " + this.statusDuration);
            return;
        }
        case Sleep: {
            /*
             * Lasts a random value of 1 to 7 rounds.
             */
            Random rng = new Random();
            this.statusDuration = Math.abs(rng.nextInt(6)) + 1;
            return;
        }
        case Freeze: {
            /*
             * Each round there is a 20% chance to break the ice.
             */
            int rounds = 1;
            Random rng = new Random();
            while (Math.abs(rng.nextInt(100)) >= 20) {
                rounds++;
                if (rounds == 7) {
                    break;
                }
            }
            this.statusDuration = rounds;
            return;
        }
        default: {
            this.statusDuration = Integer.MAX_VALUE;
        }
        }
    }

    /**
     * This method will reset the Monster's Status to initial values.
     */
    private void resetStatus() {
        this.status = Status.Normal;
        this.att.reset();
        this.canMove = true;
    }

    /**
     * Will check if the status should be over.
     */
    public boolean updateStats() {
        if (this.status == Status.Normal) {
            return true;
        }
        if (this.statusStart < this.statusDuration) {
            applyStatus();
        } else {
            resetStatus();
            return true;
        }
        this.statusStart++;
        return false;
    }

    /************************************************
     *
     * ITEM METHODS
     *
     * **********************************************/

    public boolean cureStatus(Status stat) {
        if (this.status == Status.Normal || this.status != stat) {
            return false;
        } else {
            this.status = Status.Normal;
            return true;
        }
    }

    public boolean increasePP(Attack atk, int amount) {
        if (amount <= 0) {
            return false;
        }
        if (moves.containsKey(atk)) {
            return moves.get(atk).addPowerPoints(amount);
        }
        return false;
    }

    /**
     * Revive the Monster. And restore is HP.
     *
     * @param restore
     *            is the absolute value of HP restored.
     */
    public boolean revive(int restore) {
        if (restore <= 0) {
            return false;
        }
        if (isAlive()) {
            return false;
        }
        this.alive = true;
        this.hp = restore;
        return true;
    }

    /**
     * Revive the Monster. And restore is HP.
     *
     * @param percent
     *            is the percent of HP restored.
     */
    public boolean revive(double percent) {
        if (percent <= 0) {
            return false;
        }
        if (isAlive()) {
            return false;
        }
        this.alive = true;
        this.hp = (int) ((double) maxHP * percent);
        return true;
    }

    /**
     * Restore the Monster's HP by an absolute value.
     *
     * @param value
     *            is the value restored.
     */
    public boolean restoreHP(int value) {
        if (value <= 0) {
            return false;
        }
        if (!alive) {
            return false;
        }
        if (hp == maxHP) {
            return false;
        }
        hp += value;
        if (hp > maxHP) {
            hp = maxHP;
        }
        return true;
    }

    /**
     * Restore a percent of the Monster's health.
     *
     * @param percent
     *            is the percent restored.
     */
    public boolean restoreHP(double percent) {
        if (percent <= 0) {
            return false;
        }
        if (!alive) {
            return false;
        }
        if (Math.abs(hp - maxHP) <= 0) {
            return false;
        }
        int value = (int) ((double) maxHP * percent);
        if (value <= hp) {
            return false;
        }
        hp = value;
        if (hp > maxHP) {
            hp = maxHP;
        }
        return true;

    }

    /**
     * This method will iterate through all the Monster's moves and check if any
     * of them can inflict a status on a opponent upon landing a hit.
     * 
     * @return true if this Monster has any Status Move.
     */
    public boolean hasStatusMoves() {

        for (Attack attack : this.listMoves()) {
            if (MoveSet.getMove(attack).isStatusMove()) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method will calculate the Survivability, which is the likelyhood
     * that the Monster will survive the next round against an opponent monster.
     * This Survivability is calculated by checking if the highest possible
     * damage for every of the enemy's moves, is enough to kill this Monster in
     * the next round. The Survivability Score is an integer number between 0
     * and 100.
     * 
     * @param opponent
     * @return
     */
    public int GetSurvivabilityScoreOfMonAgainstOpponent(Monster opponent) {
        int maxDamageThatCanBeIncurred = 0;

        Damage damageToMon = new Damage();

        for (Attack attack : opponent.listMoves()) {
            maxDamageThatCanBeIncurred = maxDamageThatCanBeIncurred
                    + damageToMon.highestPossibleDamage(attack, opponent, this);
        }

        this.survivabilityScore = 100 - maxDamageThatCanBeIncurred;

        return survivabilityScore;
    }

    /**
     * This method is used for testing only, and should under no circumstances
     * be used outside scope of a test.
     * 
     * @param i
     */
    public void testSetHP(int i) {
        this.hp = i;
    }

}