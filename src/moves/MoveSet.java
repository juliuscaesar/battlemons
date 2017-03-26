package moves;
import java.util.HashMap;
import java.util.Map;

import general.Attack;


public class MoveSet {
  private static Map<Attack, Move> moves;

  static {
    moves = new HashMap<>();
  }

  public Move getMove(Attack name){
    if(moves.containsKey(name)) {
      return moves.get(name);
    }
    throw new IllegalArgumentException("Move: [" + name.name() + "] not found.");
  }
}