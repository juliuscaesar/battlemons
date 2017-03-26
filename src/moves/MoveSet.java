package moves;
import monsters.Element;

import java.util.HashMap;
import java.util.Map;


public class MoveSet {
  private static Map<String, Move> moves;

  static {
    moves = new HashMap<>();
    moves.put("Bite", new Move("Bite", 60, 100, 25, Element.Dark, MoveCategory.Physical));

  }

  public Move getMove(String name){
    if(moves.containsKey(name)) {
      return moves.get(name);
    }
    throw new IllegalArgumentException("Move: [" + name + "] not found.");
  }
}