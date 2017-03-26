package moves;

import monsters.Element;

public class Move{
    final String name;
    final int damage;
    final int acc;
    final int pp;
    final Element element;
    final MoveCategory cat;

    Move(String name, int dmg, int acc, int pp,  Element element, MoveCategory cat) {
      this.name = name;
      this.damage = dmg;
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