package monsters;
import java.util.HashMap;
import java.util.Map;

import general.MonsterID;
import general.Attack;
import general.Element;
import moves.MoveSet;

public class MonsterSet {
  private static Map<MonsterID, Monster> monsters;


    // Monsters name, int hp, int atk, int spAtk, int def, int spDef, int spd, Element... elements)
	  static Monster Emberfly = new Monster(MonsterID.Emberfly, 120, 100, 75, 110, 75, 90, Element.Fire);
    static Monster Flamber = new Monster(MonsterID.Flamber, 110, 80, 115, 85, 85, 60, Element.Fire);
    static Monster Oceolot = new Monster(MonsterID.Oceolot, 130, 80, 120, 95, 110, 110, Element.Water);
    static Monster Feesh = new Monster(MonsterID.Feesh, 120, 80, 75, 88, 89, 101, Element.Water);
    static Monster Parrit = new Monster(MonsterID.Parrit, 110, 80, 75, 65, 75, 55, Element.Flying);
    static Monster Hummbee = new Monster(MonsterID.Hummbee, 140, 80, 75, 65, 75, 55, Element.Flying);
    static Monster Shockeel = new Monster(MonsterID.Shockeel, 100, 80, 115, 65, 75, 140, Element.Electric);
    static Monster Bulblight = new Monster(MonsterID.Bulblight, 95, 80, 75, 65, 75, 55, Element.Electric);
    static Monster Enchantu = new Monster(MonsterID.Enchantu, 120, 80, 75, 65, 75, 55, Element.Psychic);
    static Monster Hopper = new Monster(MonsterID.Hopper, 110, 80, 75, 65, 75, 55, Element.Psychic);
    static Monster Armordillo = new Monster(MonsterID.Armordillo, 135, 120, 75, 110, 75, 95, Element.Ground);
    static Monster Munkrock = new Monster(MonsterID.Munkrock, 99, 98, 75, 65, 75, 55, Element.Ground);
    static Monster Ursidae = new Monster(MonsterID.Ursidae, 120, 80, 75, 65, 75, 110, Element.Normal);
    static Monster Meowser = new Monster(MonsterID.Meowser, 105, 80, 75, 110, 95, 55, Element.Normal);
    static Monster Glacipup = new Monster(MonsterID.Glacipup, 135, 65, 115, 105, 154, 113, Element.Ice);
    static Monster Yeeti = new Monster(MonsterID.Yeeti, 110, 80, 130, 95, 130, 95, Element.Ice);
    static Monster Punchroo = new Monster(MonsterID.Punchroo, 120, 160, 75, 65, 75, 120, Element.Fighting);
    static Monster Carrotay = new Monster(MonsterID.Carrotay, 100, 100, 75, 135, 75, 110, Element.Fighting);
    static Monster Grandant = new Monster(MonsterID.Grandant, 120, 80, 75, 65, 75, 95, Element.Bug);
    static Monster Skorpen = new Monster(MonsterID.Skorpen, 120, 95, 75, 90, 70, 85, Element.Bug);
    static Monster Supalm = new Monster(MonsterID.Supalm, 140, 140, 80, 65, 75, 93, Element.Grass);
    static Monster Crysanthum = new Monster(MonsterID.Crysanthum, 110, 75, 115, 65, 115, 110, Element.Grass);
    static Monster Boomtu = new Monster(MonsterID.Boomtu, 120, 80, 135, 65, 150, 110, Element.Ghost);
    static Monster Haunting = new Monster(MonsterID.Haunting, 135, 95, 105, 95, 75, 85, Element.Ghost);
    static Monster Nadifly = new Monster(MonsterID.Nadifly, 115, 80, 75, 65, 75, 99, Element.Poison);
    static Monster Adnocana = new Monster(MonsterID.Adnocana, 120, 110, 75, 99, 104, 105, Element.Poison);


  static {
    monsters = new HashMap<>();

    /**
     * Assigns 4 moves to each Battlemon
     */
     Emberfly.addMoves(MoveSet.getMove(Attack.FirePunch), MoveSet.getMove(Attack.WingAttack),
                      MoveSet.getMove(Attack.Amnesia), MoveSet.getMove(Attack.BodySlam));
     Flameber.addMoves(MoveSet.getMove(Attack.FireBlast), MoveSet.getMove(Attack.Barrier),
                      MoveSet.getMove(Attack.Sandattack), MoveSet.getMove(Attack.ConfuseRay));
     Oceolot.addMoves(MoveSet.getMove(Attack.WaterGun), MoveSet.getMove(Attack.Blizzard),
                      MoveSet.getMove(Attack.Mist), MoveSet.getMove(Attack.Barrier));
     Feesh.addMoves(MoveSet.getMove(Attack.Waterfall), MoveSet.getMove(Attack.Scratch),
                      MoveSet.getMove(Attack.Acid), MoveSet.getMove(Attack.BodySlam));
     Parrit.addMoves(MoveSet.getMove(Attack.Fly), MoveSet.getMove(Attack.WingAttack),
                      MoveSet.getMove(Attack.SkyAttack), MoveSet.getMove(Attack.Counter));
     Hummbee.addMoves(MoveSet.getMove(Attack.ConfuseRay), MoveSet.getMove(Attack.WingAttack),
                      MoveSet.getMove(Attack.Amnesia), MoveSet.getMove(Attack.BodySlam));
     Shockeel.addMoves(MoveSet.getMove(Attack.Thunder), MoveSet.getMove(Attack.AcidMove),
                      MoveSet.getMove(Attack.Toxic), MoveSet.getMove(Attack.WaterGun));
     Bulblight.addMoves(MoveSet.getMove(Attack.ThunderPunch), MoveSet.getMove(Attack.ThunderWave),
                      MoveSet.getMove(Attack.BodySlam), MoveSet.getMove(Attack.Sandattack));
     Enchantu.addMoves(MoveSet.getMove(Attack.Teleport), MoveSet.getMove(Attack.Kinesis),
                      MoveSet.getMove(Attack.Nightshade), MoveSet.getMove(Attack.BodySlam));
     Hopper.addMoves(MoveSet.getMove(Attack.Teleport), MoveSet.getMove(Attack.Kinesis),
                      MoveSet.getMove(Attack.Nightshade), MoveSet.getMove(Attack.BodySlam));
     Armordillo.addMoves(MoveSet.getMove(Attack.Earthquake), MoveSet.getMove(Attack.DoubleKick),
                      MoveSet.getMove(Attack.Barrage), MoveSet.getMove(Attack.BodySlam));
     Munkrock.addMoves(MoveSet.getMove(Attack.Earthquake), MoveSet.getMove(Attack.DoubleKick),
                      MoveSet.getMove(Attack.Barrage), MoveSet.getMove(Attack.BodySlam));
     Ursidae.addMoves(MoveSet.getMove(Attack.Earthquake), MoveSet.getMove(Attack.Amnesia),
                      MoveSet.getMove(Attack.SeismicToss), MoveSet.getMove(Attack.BodySlam));
     Meowser.addMoves(MoveSet.getMove(Attack.Scratch), MoveSet.getMove(Attack.DoubleKick),
                      MoveSet.getMove(Attack.Barrage), MoveSet.getMove(Attack.BodySlam));
     Glacipup.addMoves(MoveSet.getMove(Attack.IceBeam), MoveSet.getMove(Attack.Surf),
                      MoveSet.getMove(Attack.Haze), MoveSet.getMove(Attack.ConfuseRay));
     Yeeti.addMoves(MoveSet.getMove(Attack.Blizzard), MoveSet.getMove(Attack.Surf),
                      MoveSet.getMove(Attack.Haze), MoveSet.getMove(Attack.ConfuseRay));
     Punchroo.addMoves(MoveSet.getMove(Attack.HighJumpKick), MoveSet.getMove(Attack.SeismicToss),
                      MoveSet.getMove(Attack.ConfuseRay), MoveSet.getMove(Attack.IcePunch));
     Carrotay.addMoves(MoveSet.getMove(Attack.Earthquake), MoveSet.getMove(Attack.LowKick),
                      MoveSet.getMove(Attack.SeismicToss), MoveSet.getMove(Attack.ConfuseRay));
     Grandant.addMoves(MoveSet.getMove(Attack.StringShot), MoveSet.getMove(Attack.SporeMove),
                      MoveSet.getMove(Attack.PoisonSting), MoveSet.getMove(Attack.PinMissile));
     Skorpen.addMoves(MoveSet.getMove(Attack.StringShot), MoveSet.getMove(Attack.SporeMove),
                      MoveSet.getMove(Attack.PoisonSting), MoveSet.getMove(Attack.PinMissile));
     Supalm.addMoves(MoveSet.getMove(Attack.RazorLeaf), MoveSet.getMove(Attack.SporeMove),
                      MoveSet.getMove(Attack.LeechLife), MoveSet.getMove(Attack.LeechSeed));
     Crysanthum.addMoves(MoveSet.getMove(Attack.IceBeam), MoveSet.getMove(Attack.Poison),
                      MoveSet.getMove(Attack.SolarBeam), MoveSet.getMove(Attack.PinMissile));
     Boomtu.addMoves(MoveSet.getMove(Attack.Psybeam), MoveSet.getMove(Attack.Thunder),
                      MoveSet.getMove(Attack.Nightshade), MoveSet.getMove(Attack.ConfuseRay));
     Haunting.addMoves(MoveSet.getMove(Attack.Lick), MoveSet.getMove(Attack.Teleport),
                      MoveSet.getMove(Attack.Counter), MoveSet.getMove(Attack.TwinNeedle));
     Nadifly.addMoves(MoveSet.getMove(Attack.Fly), MoveSet.getMove(Attack.Toxic),
                      MoveSet.getMove(Attack.PoisonSting), MoveSet.getMove(Attack.TwinNeedle));
     Adnocana.addMoves(MoveSet.getMove(Attack.Lick), MoveSet.getMove(Attack.BodySlam),
                      MoveSet.getMove(Attack.PoisonSting), MoveSet.getMove(Attack.TwinNeedle));

     /**
      * Adds the Battlemons to the HashMap
      */
      monsters.put(MonsterID.Emberfly, Emberfly);
      monsters.put(MonsterId.Flamber, Flamber);
      monsters.put(MonsterID.Oceolot, Oceolot);
      monsters.put(MonsterID.Feesh, Feesh);
      monsters.put(MonsterID.Parrit, Parrit);
      monsters.put(MonsterID.Hummbee, Hummbee);
      monsters.put(MonsterID.Shockeel, Shockeel);
      monsters.put(MonsterID.Bulblight, Bulblight);
      monsters.put(MonsterID.Enchantu, Enchantu);
      monsters.put(MonsterID.Hopper, Hopper);
      monsters.put(MonsterID.Armordillo, Armordillo);
      monsters.put(MonsterID.Munkrock, Munkrock);
      monsters.put(MonsterID.Ursidae, Ursidae);
      monsters.put(MonsterID.Meowser, Meowser);
      monsters.put(MonsterID.Glacipup, Glacipup);
      monsters.put(MonsterID.Yeeti, Yeeti);
      monsters.put(MonsterID.Punchroo, Punchroo);
      monsters.put(MonsterID.Carrotay, Carrotay);
      monsters.put(MonsterID.Grandant, Grandant);
      monsters.put(MonsterID.Skorpen, Skorpen);
      monsters.put(MonsterID.Supalm, Supalm);
      monsters.put(MonsterID.Crysanthum, Crysanthum);
      monsters.put(MonsterID.Boomtu, Boomtu);
      monsters.put(MonsterID.Haunting, Haunting);
      monsters.put(MonsterID.Nadifly, Nadifly);
      monsters.put(MonsterID.Adnocana, Adnocana);

  }

  /**
   * Return the Monster associated with the given MonsterID
   */
  public Monster getMonster(MonsterID name) {
    if(monsters.containsKey(name)) {
      return monsters.get(name);
    }
    throw new IllegalArgumentException("Monster: [" + name.name() + "] not found.");
  }
}
