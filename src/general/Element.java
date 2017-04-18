package general;

import java.util.HashMap;

/**
 * Enumeration for all the Elements supported.
 * 
 * An Element can belong to both a Monster and a Move.
 * 
 * When a Move is used against a Monster, the Elements of both are combined to generate a damage multiplier, that could
 * be {0.5 , 1.0 , 2}.
 *
 */
public enum Element {
    Fire, Water, Grass, Poison, Flying, Normal, Ghost, Psychic, Fighting, Rock, Ground, Electric, Ice, Bug;

    // The mapping of all important (not 1x) element match ups in the game.
    // Accessed through getMatchupValue().
    @SuppressWarnings("serial")
    final private static HashMap<Element, HashMap<Element, Double>> elementMatchups = new HashMap<Element, HashMap<Element, Double>>() {
        private static final long serialVersionUID = 3885690504037188755L;
        {
            put(Element.Bug, new HashMap<Element, Double>() {
                {
                    put(Element.Fire, .5);
                    put(Element.Grass, 2.);
                    put(Element.Fighting, .5);
                    put(Element.Poison, 2.);
                    put(Element.Flying, .5);
                    put(Element.Psychic, 2.);
                }
            });
            put(Element.Electric, new HashMap<Element, Double>() {
                {
                    put(Element.Water, 2.);
                    put(Element.Electric, .5);
                    put(Element.Grass, .5);
                    put(Element.Ground, 0.);
                    put(Element.Flying, 2.);
                }
            });
            put(Element.Fighting, new HashMap<Element, Double>() {
                {
                    put(Element.Normal, 2.);
                    put(Element.Ice, 2.);
                    put(Element.Poison, .5);
                    put(Element.Flying, .5);
                    put(Element.Psychic, .5);
                    put(Element.Bug, .5);
                    put(Element.Rock, 2.);
                    put(Element.Ghost, 0.);
                }
            });
            put(Element.Fire, new HashMap<Element, Double>() {
                {
                    put(Element.Fire, .5);
                    put(Element.Water, .5);
                    put(Element.Grass, 2.);
                    put(Element.Ice, 2.);
                    put(Element.Bug, 2.);
                    put(Element.Rock, .5);
                }
            });
            put(Element.Flying, new HashMap<Element, Double>() {
                {
                    put(Element.Electric, .5);
                    put(Element.Grass, 2.);
                    put(Element.Fighting, 2.);
                    put(Element.Bug, 2.);
                    put(Element.Rock, .5);
                }
            });
            put(Element.Ghost, new HashMap<Element, Double>() {
                {
                    put(Element.Normal, 0.);
                    put(Element.Psychic, 0.);
                    put(Element.Ghost, 2.);
                }
            });
            put(Element.Grass, new HashMap<Element, Double>() {
                {
                    put(Element.Fire, .5);
                    put(Element.Water, 2.);
                    put(Element.Grass, .5);
                    put(Element.Poison, .5);
                    put(Element.Ground, 2.);
                    put(Element.Flying, .5);
                    put(Element.Bug, .5);
                    put(Element.Rock, 2.);
                }
            });
            put(Element.Ground, new HashMap<Element, Double>() {
                {
                    put(Element.Fire, 2.);
                    put(Element.Electric, 2.);
                    put(Element.Grass, .5);
                    put(Element.Poison, 2.);
                    put(Element.Flying, 0.);
                    put(Element.Bug, .5);
                    put(Element.Rock, 2.);
                }
            });
            put(Element.Ice, new HashMap<Element, Double>() {
                {
                    put(Element.Water, .5);
                    put(Element.Grass, 2.);
                    put(Element.Ice, .5);
                    put(Element.Ground, 2.);
                    put(Element.Flying, 2.);
                }
            });
            put(Element.Normal, new HashMap<Element, Double>() {
                {
                    put(Element.Rock, .5);
                    put(Element.Ghost, 0.);
                }
            });
            put(Element.Poison, new HashMap<Element, Double>() {
                {
                    put(Element.Grass, 2.);
                    put(Element.Poison, .5);
                    put(Element.Ground, .5);
                    put(Element.Bug, 2.);
                    put(Element.Rock, .5);
                    put(Element.Ghost, .5);
                }
            });

            put(Element.Psychic, new HashMap<Element, Double>() {
                {
                    put(Element.Fighting, 2.);
                    put(Element.Poison, 2.);
                    put(Element.Psychic, .5);
                }
            });
            put(Element.Rock, new HashMap<Element, Double>() {
                {
                    put(Element.Fire, 2.);
                    put(Element.Ice, 2.);
                    put(Element.Fighting, .5);
                    put(Element.Ground, .5);
                    put(Element.Flying, 2.);
                    put(Element.Bug, 2.);
                }
            });
            put(Element.Water, new HashMap<Element, Double>() {
                {
                    put(Element.Fire, 2.);
                    put(Element.Water, .5);
                    put(Element.Grass, .5);
                    put(Element.Ground, 2.);
                    put(Element.Rock, 2.);
                }
            });

        }
    };

    // Returns the multiplier for the provided matchup. If there's an entry in
    // the above table, that's used; otherwise, a multiplier of 1x is assumed.
    public static Double getMatchupValue(Element attacker, Element defender) {
        HashMap<Element, Double> table = elementMatchups.get(attacker);
        if (table.containsKey(defender)) return table.get(defender);
        return 1.;
    }
}
