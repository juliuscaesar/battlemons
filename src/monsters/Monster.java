package monsters;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Random;

import general.Attack;
import general.Element;
import general.Status;
import general.MonsterID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import moves.Move;

/**
 * Monster Class.
 * A Monster consists of:
 * - Name (Attack)
 * - Attributes
 * - Its Current Status.
 * - Element1
 * - Element2
 * - List of Moves
 */
public final class Monster {

  private MonsterID name; // The Monster's Name.
  private Status status; // The Monter's Abnormal Status.
  private int statusStart; // The Round where the Monster got this Status.
  private int statusDuration; // The Duration for this Status.
  private Element e1; // Monster's Element 1.
  private static Map<Attack, Move> moves = new HashMap<Attack, Move>(); // Map of Moves for this Monster.
  private boolean canMove; // True if the Monster can move, false if not.
  private boolean alive;

  private final Attributes att;
  private int hp;
  private final int maxHP;

  Monster(MonsterID name, int hp, int atk, int spAtk, int def, int spDef, int spd, Element... elements) {
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
    this.alive = true;
  }

  public static void addMoves(Move m1, Move m2, Move m3, Move m4){
	  moves.put(m1.toAttack(), m1);
	  moves.put(m2.toAttack(), m2);
	  moves.put(m3.toAttack(), m3);
	  
	  moves.put(m4.toAttack(), m4);
  }

  /**
   * Will check if the Monster is still alive, if not, reset the Monster.
   */
  private void checkAlive(){
	  if(hp <= 0){
		  hp = 0;
		  alive = false;
		  resetStatus();
	  }
  }

    /**
   * Recalculate the Monster's remaining HP.
   *
   * @param dmg is the value that should be decreased from the Monster's HP.
   */
  public void receiveAttack(int dmg){
	  this.hp -= dmg;
	  checkAlive();
  }

  /************************************************
   *
   * 		GET METHODS
   *
   * **********************************************/

  public boolean canMove(){
	  return this.canMove;
  }

  /**
   * Check if the Monster is alive or not.
   *
   * @return true if the Monster is alive, false if not.
   */
  public boolean isAlive(){
	  checkAlive();
	  return this.alive;
  }


  /**
   * List all the Moves for this Monster.
   *
   * @return a list with AttackEnums for this Monster Moves.
   */
  public List<Attack> listMoves(){
	  List<Attack> list = new ArrayList<>();
	  for(Entry<Attack, Move> e : moves.entrySet()){
		  list.add(e.getKey());
	  }
	  return list;
  }

  public int getSpeed(){
	  return att.getSpd(this.status);
  }

  public int getHP() {
	  return this.hp;
  }
  
  /**
   * Returns the percent value of the in a double number in the range of
   * 0.0000 to 1.0000.
   * @return the percent value of the monster.
   */
  public double getPercentHP(){
	  double current = (double)this.hp;
	  double max = (double)this.maxHP;
	  return (current/max);
  }

  public Status getStatus(){
	  return this.status;
  }

  public MonsterID getID(){
	  return this.name;
  }

  public int getAtk(){
	  return this.att.getAtk();
  }

  public int getDef(){
	  return this.att.getDef();
  }

  public int getSpDef(){
	  return this.att.getSpDef();
  }

  public Element getElem(){
	  return this.e1;
  }

  public int getSpAtk(){
	  return this.att.getSpAtk();
  }


  /************************************************
   *
   * 		STATUS METHODS
   *
   * **********************************************/

  /**
   * Add the Status to a Monster.
   *
   * @param stat the status.
   */
  public void setStatus(Status stat) {
    if (this.status == stat) {
      return;
    }
    this.statusStart = 0;
    this.status = stat;
    this.applyStatus();
    getStatusDuration();
  }

  /**
   * This method will apply the status debuff.
   * Freeze: can't move.
   * Sleep: can't move.
   * Paralysis: 25% chance of not moving. Cuts Speed in half.
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
    	  if(percent < 25) {
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
   * This method will apply the status damage
   * Burn: takes damage to equal of 16% of max HP.
   * Poison: takes damage to equal of 16% of max HP.
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
	  switch(this.status){
	  case Burn: {
		  /*
		   * 37.5% chance of lasting 2 rounds
		   * 37.5% chance of lasting 3 rounds
		   * 12.5% chance of lasting 4 rounds
		   * 12.5% chance of lasting 5 rounds
		   */
		  Random rng = new Random();
		  int minimum = 2;
		  int percent = Math.abs(rng.nextInt(1000));
		  if(percent < 375){
			  this.statusDuration = minimum;
		  }
		  else if(percent < 750){
			  this.statusDuration = minimum + 1;
		  }
		  else if(percent < 875){
			  this.statusDuration = minimum + 2;
		  }
		  else{
			  this.statusDuration = minimum + 3;
		  }
		  return;
	  }
	  case Sleep: {
		  /*
		   * Lasts a random value of 1 to 7 rounds.
		   */
		  Random rng = new Random();
		  this.statusDuration = Math.abs(rng.nextInt(7)) + 1;
	  }
	  case Freeze: {
		  /*
		   * Each round there is a 20% chance to break the ice.
		   */
		  int rounds = 1;
		  Random rng = new Random();
		  while(Math.abs(rng.nextInt(100)) >= 20){
			  rounds++;
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
  private void resetStatus(){
	  this.status = Status.Normal;
	  this.att.reset();
	  this.canMove = true;
  }

   /**
   * Will check if the status should be over.
   */
  public void updateStats(){
    if(this.status == Status.Normal){
      return;
    }
    if(this.statusStart >= this.statusDuration) {
      resetStatus();
    }else{
    	applyStatus();
    }
    this.statusStart++;
  }

  /************************************************
   *
   * 		ITEM METHODS
   *
   * **********************************************/

  public boolean cureStatus(Status stat){
	  if(this.status == Status.Normal || this.status != stat){
		  return false;
	  }
	  else{
		  this.status = Status.Normal;
		  return true;
	  }
  }

  public boolean increasePP(Attack atk, int amount){
	  if(moves.containsKey(atk)){
		  return moves.get(atk).addPowerPoints(amount);
	  }
	  return false;
  }

  /**
   * Revive the Monster. And restore is HP.
   *
   * @param restore is the absolute value of HP restored.
   */
  public void revive(int restore) {
	  this.alive = true;
	  this.hp = restore;
  }

  /**
   * Revive the Monster. And restore is HP.
   *
   * @param percent is the percent of HP restored.
   */
  public void revive(double percent) {
	  this.alive = true;
	  this.hp = (int)((double)maxHP * percent);
  }

  /**
   * Restore the Monster's HP by an absolute value.
   *
   * @param value is the value restored.
   */
  public void restoreHP(int value) {
	  if(!alive) {
		  throw new IllegalArgumentException("Can't heal dead Monsters.");
	  }
	  if(hp == maxHP) {
		  throw new IllegalArgumentException("Max HP Already.");
	  }
	  hp += value;
	  if(hp > maxHP){
		  hp = maxHP;
	  }
  }

  /**
   * Restore a percent of the Monster's health.
   *
   * @param percent is the percent restored.
   */
  public void restoreHP(double percent) {
	  if(!alive) {
		  throw new IllegalArgumentException("Can't heal dead Monsters.");
	  }
	  if(hp == maxHP) {
		  throw new IllegalArgumentException("Max HP Already.");
	  }
	  hp = (int)((double)maxHP * percent);
	  if(hp > maxHP){
		  hp = maxHP;
	  }
  }
}
