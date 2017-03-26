import java.util.Map;

import moves.Move;

/**
 * Monster Class.
 * A Monster consists of:
 * - Name (String)
 * - Stats
 * - Element1
 * - Element2
 * - List of Moves
 */
public final class Monster {

  private String name; // The Monster's Name.
  private Status status; // The Monter's Abnormal Status.
  private int statusStart; // The Round where the Monster got this Status.
  private int statusDuration; // The Duration for this Status.
  private Element e1; // Monster's Element 1.
  private Element e2; // Monster's Element 2.
  private boolean twoElements; // True if Monster has 2 Elements, False if not.
  private Map<Attack, Move> moves; // Map of Moves for this Monster.

  Monster(String name, Map<Attack, Move> moves, Element... elements) {
    this.name = name;
    this.moves = moves;
    this.status = Status.Normal;
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
  }

  /**
   * Add the Status to a pokemon
   *
   * @param stat the stat.
   * @param roundStart the round where this Status started.
   */
  public void addStatus(Status stat, int roundStart) {
    if (this.status == stat) {
      throw new IllegalArgumentException("Already with this stat.");
    }
    this.statusStart = roundStart;
    this.status = stat;
    getStatusDuration();
  }

  private void applyStatus() {
    switch (this.status) {
      case Burn: {

      }
      case Freeze: {

      }
      case Poison: {

      }
      case Paralysis: {

      }
      case Sleep: {

      }
      default: {

      }
    }
  }


  /**
   * Calculate the Duration in rounds for a given Status.
   */
  private void getStatusDuration() {
    switch (this.status) {
      case Burn: {
        this.statusDuration = 1;
      }
      case Freeze: {
        this.statusDuration = 2;
      }
      case Poison: {
        this.statusDuration = 3;
      }
      case Paralysis: {
        this.statusDuration = 4;
      }
      case Sleep: {
        this.statusDuration = 5;
      }
      default: {
        this.statusDuration = 6;
      }
    }
  }

  /**
   * Will execute the abnormal status' debuff. If duration of status is over, will change the
   * Monster's status back to Normal.
   *
   * @param round
   */
  public void updateStats(int round){
    if(this.status == Status.Normal){
      return;
    }
    if((round - this.statusStart) >= this.statusDuration) {
      this.status = Status.Normal;
    }
    else{
      applyStatus();
    }
  }
}
