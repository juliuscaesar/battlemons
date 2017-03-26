package moves;
import java.util.HashMap;
import java.util.Map;

import general.Element;

public class CalcSpecialDmg {
  private static Map<String, Double> values;
  private final double defaultMultiplier = 1.0;
  private final static double low = 0.5;
  private final static double high = 1.5;

  static{
    values = new HashMap<>();
    values.put(linkNames(Element.Fire,Element.Water), low);
  }

  /**
   * Function will look in the map the key generated from both values, if not found
   * it will look for the key gerenated from both values, but swapped, if not found will return
   * the default value for the multiplier.
   *
   * Ex:
   * (Fire,Water) was added to the map.
   * If method receives: Fire, Water, the key will be found. And wil return 0.5
   * If the method receives: Water, Fire. The key will not be found, so the method will look
   * for the inverse, which is Fire, Water, and that will be found, then will return the inverse value
   * (if 1.5, will return 0.5, else, return 1.5)
   *
   * @param sourceMove the element of the Source Pokemon's Move.
   * @param targetPokemon the element of the Target Pokemon.
   * @return the multiplier for that move.
   */
  private double elemMultiplier(Element sourceMove, Element targetPokemon){
    String key = linkNames(sourceMove, targetPokemon);
    if(values.containsKey(key)) {
      return values.get(key);
    }
    else {
      String inverseKey = linkNames(targetPokemon, sourceMove);
      if(values.containsKey(inverseKey)) {
        double val =  values.get(inverseKey);
        if(val == high) {
          return low;
        }
        else {
          return high;
        }
      }
      else {
        return defaultMultiplier;
      }
    }
  }

  public double getMultiplier(Element source, Element... target){
	  double val = 1;
	  for(Element e : target) {
		  val *= elemMultiplier(source, e);
	  }
	  return val;
  }



  private static String linkNames(Element one, Element two) {
    return (one.name() + ":" + two.name());
  }
}