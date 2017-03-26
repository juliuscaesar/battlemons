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

class Move{
  final String name;
  final int dmg;
  final int acc;
  final int pp;
  final Element element;
  final MoveCategory cat;

  Move(String name, int dmg, int acc, int pp,  Element element, MoveCategory cat) {
    this.name = name;
    this.dmg = dmg;
    this.acc = acc;
    this.pp = pp;
    this.element = element;
    this.cat = cat;

    if(acc < 1 || acc > 100) {
      throw new IllegalArgumentException(
          "Accuracy out of bounds. Valid values are only between 1 and 100");
    }
  }
}