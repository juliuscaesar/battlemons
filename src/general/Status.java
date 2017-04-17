package general;

/**
 * List of Abnormal Status that can be inflicted on a Monster.
 * Status inflict debuff on the Monsters, each Status give different debuffs.
 * Status can prevent a Monster from attacking, decrease Attribute values or cause damage to the Monster.
 * 
 * Normal.
 * This is the default Status and inflict no buff or debuff on the Monster.
 * 
 * Sleep.
 * This Status lasts from 1 to 7 rounds and prevents the Monster from attacking.
 * 
 * Paralysis.
 * This Status lasts forever, and on each round, there's a 25% chance that it will prevent the Monster from attacking.
 * Monster under Paralysis will have their Speed Attribute cut in half.
 * 
 * Poison.
 * This Status lasts forever, and at the end of each round, the Monster inflicted with Poison will lose 1/16 of their health.
 *
 * Burn.
 * This Status last from 2 to 5 rounds, and at the end of each round, the Monster inflicted with Burn will lose 1/16 of their health.
 * 
 * Freeze.
 * This Status can last forever. On each round, there's 20% chance of lifting this Status.
 * It will also prevents the Monster from attacking.
 */
public enum Status {
  Normal, Sleep, Paralysis, Poison, Burn, Freeze
}
