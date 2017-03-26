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
import moves.CalcSpecialDmg;

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
  private Element e2; // Monster's Element 2.
  private boolean twoElements; // True if Monster has 2 Elements, False if not.
  private Map<Attack, Move> moves; // Map of Moves for this Monster.
  private boolean canMove; // True if the Monster can move, false if not.
  private boolean alive;
  
  private final Attributes att;
  private int hp;
  private final int maxHP;

  Monster(MonsterID name, int hp, int atk, int spAtk, int def, int spDef, int spd, Element... elements) {
    this.name = name;
    this.moves = new HashMap<>();
    this.status = Status.Normal;
    this.hp = hp;
    this.maxHP = hp;
    this.canMove = true;
    att = new Attributes(atk, spAtk, spDef, def, spd);
    int i = 0;
    for (Element e : elements) {
      if (i == 0) {
        this.e1 = e;
        this.twoElements = false;
      } else if (i == 1) {
        this.e2 = e;
        this.twoElements = true;
      } else {
        throw new IllegalArgumentException("More than 2 Elements");
      }
      i++;
    }
    this.alive = true;
  }
  
  public void addMoves(Move... moves){
	  for(Move ms : moves){
		  this.moves.put(ms.toAttack(), ms);
	  }
  }

  /**
   * Add the Status to a Monster.
   *
   * @param stat the status.
   * @param roundStart the round where this Status started.
   */
  public void addStatus(Status stat) {
    if (this.status == stat) {
      throw new IllegalArgumentException("Already with this stat.");
    }
    this.statusStart = 0;
    this.status = stat;
    getStatusDuration();
  }

  /**
   * This method will apply the status debuff.
   * Burn: takes damage to equal of 16% of max HP.
   * Freeze: cant move.
   * Poison: takes damage to equal of 16% of max HP.
   * Sleep: cant move.
   * Paralysis: 25% chance of not moving. Cuts Speed in half.
   */
  private void applyStatus() {
    switch (this.status) {
      case Burn: {
    	  this.hp = this.maxHP / 16;
    	  return;
      }
      case Freeze: {
    	  this.canMove = false;
    	  return;
      }
      case Poison: {
    	  this.hp = this.maxHP / 16;  	 
    	  return;
      }
      case Paralysis: {
    	  Random rng = new Random();
    	  int percent = rng.nextInt(100);
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
		  int percent = rng.nextInt(1000);
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
		  this.statusDuration = rng.nextInt(7) + 1;
	  }
	  case Freeze: {
		  /*
		   * Each round there is a 20% chance to break the ice.
		   */
		  int rounds = 1;
		  Random rng = new Random();
		  while(rng.nextInt(100) >= 20){
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
   * Will execute the abnormal status' debuff. If duration of status is over, will change the
   * Monster's status back to Normal.
   *
   */
  public void updateStats(){
    if(this.status == Status.Normal){
      return;
    }
    if(this.statusStart >= this.statusDuration) {
      resetStatus();
    }
    else{
      applyStatus();
      checkAlive();
    }
    this.statusStart++;
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
  
  /**
   * Calculate the Damage that should be received if a Monster is hit by an Attack.
   * 
   * @param atk is the Attack received.
   * @return the damage taken.
   */
  public int calculateDmg(Attack atk) {
	  Move move = moves.get(atk);
	  double rawDmg = (double)move.rawDmg(att);
	  double multiplier;
	  CalcSpecialDmg csd = new CalcSpecialDmg();
	  if(twoElements) {
		  multiplier = csd.getMultiplier(move.getElem(), e1,e2);
	  }
	  else {
		  multiplier = csd.getMultiplier(move.getElem(), e1);
	  }
	  Random r = new Random();
	  double rng = 0.85 + (0.15) * r.nextDouble();
	  return (int)(rawDmg * multiplier * rng);
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
  
  /**
   * Check if the Monster is alive or not.
   * 
   * @return true if the Monster is alive, false if not.
   */
  public boolean isAlive(){
	  return this.alive;
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
  
  public Status getStatus(){
	  return this.status;
  }
  
  public MonsterID getID(){
	  return this.name;
  }
  
}
