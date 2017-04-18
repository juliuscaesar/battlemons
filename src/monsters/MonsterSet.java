package monsters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import general.Battle;
import general.MonsterID;
import general.Attack;
import general.Element;

public class MonsterSet {
	private static Map<MonsterID, Monster> monsters;
	private static Map<MonsterID, Attack[]> moves;


	// Monsters name, int hp, int atk, int spAtk, int def, int spDef, int spd, Element... elements)
	static {
		monsters = new HashMap<>();
		moves = new HashMap<>();

		Monster Emberfly = new Monster(MonsterID.Emberfly, 120, 100, 75, 110, 75, 90, Element.Fire);
		Monster Flamber = new Monster(MonsterID.Flamber, 110, 80, 215, 85, 85, 60, Element.Fire);
		Monster Oceolot = new Monster(MonsterID.Oceolot, 130, 80, 120, 95, 110, 110, Element.Water);
		Monster Feesh = new Monster(MonsterID.Feesh, 120, 80, 75, 88, 89, 101, Element.Water);
		Monster Parrit = new Monster(MonsterID.Parrit, 110, 80, 75, 65, 75, 55, Element.Flying);
		Monster Hummbee = new Monster(MonsterID.Hummbee, 140, 80, 75, 65, 75, 55, Element.Flying);
		Monster Shockeel = new Monster(MonsterID.Shockeel, 100, 80, 115, 65, 75, 140, Element.Electric);
		Monster Bulblight = new Monster(MonsterID.Bulblight, 95, 80, 75, 65, 75, 55, Element.Electric);
		Monster Enchantu = new Monster(MonsterID.Enchantu, 120, 80, 75, 65, 75, 55, Element.Psychic);
		Monster Hopper = new Monster(MonsterID.Hopper, 110, 80, 75, 65, 75, 55, Element.Psychic);
		Monster Armordillo = new Monster(MonsterID.Armordillo, 175, 120, 75, 110, 75, 95, Element.Ground);
		Monster Munkrock = new Monster(MonsterID.Munkrock, 99, 98, 75, 65, 75, 55, Element.Ground);
		Monster Ursidae = new Monster(MonsterID.Ursidae, 120, 80, 75, 65, 75, 110, Element.Normal);
		Monster Meowser = new Monster(MonsterID.Meowser, 105, 80, 75, 110, 95, 55, Element.Normal);
		Monster Glacipup = new Monster(MonsterID.Glacipup, 135, 65, 115, 105, 154, 113, Element.Ice);
		Monster Yeeti = new Monster(MonsterID.Yeeti, 110, 80, 130, 95, 130, 95, Element.Ice);
		Monster Punchroo = new Monster(MonsterID.Punchroo, 120, 260, 75, 65, 75, 120, Element.Fighting);
		Monster Carrotay = new Monster(MonsterID.Carrotay, 100, 100, 75, 135, 75, 110, Element.Fighting);
		Monster Grandant = new Monster(MonsterID.Grandant, 120, 80, 75, 65, 75, 95, Element.Bug);
		Monster Skorpen = new Monster(MonsterID.Skorpen, 120, 95, 75, 90, 70, 85, Element.Bug);
		Monster Supalm = new Monster(MonsterID.Supalm, 140, 205, 80, 65, 75, 93, Element.Grass);
		Monster Crysanthum = new Monster(MonsterID.Crysanthum, 110, 75, 115, 65, 115, 110, Element.Grass);
		Monster Boomtu = new Monster(MonsterID.Boomtu, 120, 80, 335, 65, 150, 110, Element.Ghost);
		Monster Haunting = new Monster(MonsterID.Haunting, 135, 95, 105, 95, 75, 85, Element.Ghost);
		Monster Nadifly = new Monster(MonsterID.Nadifly, 115, 80, 75, 65, 75, 99, Element.Poison);
		Monster Adnocana = new Monster(MonsterID.Adnocana, 120, 110, 75, 99, 104, 105, Element.Poison);

		/**
		 * Assigns 4 moves to each Battlemon
		 */
		moves.put(MonsterID.Emberfly, new Attack[]{ Attack.FirePunch, Attack.WingAttack, Attack.Amnesia, Attack.BodySlam });
		moves.put(MonsterID.Flamber, new Attack[]{ (Attack.FireBlast),  (Attack.Barrier), (Attack.Sandattack),  (Attack.ConfuseRay) });
		moves.put(MonsterID.Oceolot, new Attack[]{ (Attack.WaterGun),  (Attack.Blizzard), (Attack.Mist),  (Attack.Barrier) });
		moves.put(MonsterID.Feesh, new Attack[]{ (Attack.Waterfall),  (Attack.Scratch), (Attack.Acid),  (Attack.BodySlam) });
		moves.put(MonsterID.Parrit, new Attack[]{ (Attack.Fly),  (Attack.WingAttack), (Attack.SkyAttack),  (Attack.Counter) });
		moves.put(MonsterID.Hummbee, new Attack[]{ (Attack.ConfuseRay),  (Attack.WingAttack), (Attack.Amnesia),  (Attack.BodySlam) });
		moves.put(MonsterID.Shockeel, new Attack[]{ (Attack.Thunder),  (Attack.Acid), (Attack.Toxic),  (Attack.WaterGun) });
		moves.put(MonsterID.Bulblight, new Attack[]{ (Attack.ThunderPunch),  (Attack.ThunderWave), (Attack.BodySlam),  (Attack.Sandattack) });
		moves.put(MonsterID.Enchantu, new Attack[]{ (Attack.Teleport),  (Attack.Kinesis), (Attack.Nightshade),  (Attack.BodySlam) });
		moves.put(MonsterID.Hopper, new Attack[]{ (Attack.Teleport),  (Attack.Kinesis), (Attack.Nightshade),  (Attack.BodySlam) });
		moves.put(MonsterID.Armordillo, new Attack[]{ (Attack.Earthquake),  (Attack.DoubleKick), (Attack.Barrage),  (Attack.BodySlam) });
		moves.put(MonsterID.Munkrock, new Attack[]{ (Attack.Earthquake),  (Attack.DoubleKick), (Attack.Barrage),  (Attack.BodySlam) });
		moves.put(MonsterID.Ursidae, new Attack[]{ (Attack.Earthquake),  (Attack.Amnesia), (Attack.SeismicToss),  (Attack.BodySlam) });
		moves.put(MonsterID.Meowser, new Attack[]{ (Attack.Scratch),  (Attack.DoubleKick), (Attack.Barrage),  (Attack.BodySlam) });
		moves.put(MonsterID.Glacipup, new Attack[]{ (Attack.IceBeam),  (Attack.Surf), (Attack.Haze),  (Attack.ConfuseRay) });
		moves.put(MonsterID.Yeeti, new Attack[]{ (Attack.Blizzard),  (Attack.Surf), (Attack.Haze),  (Attack.ConfuseRay) });
		moves.put(MonsterID.Punchroo, new Attack[]{ (Attack.HighJumpKick),  (Attack.SeismicToss), (Attack.ConfuseRay),  (Attack.IcePunch) });
		moves.put(MonsterID.Carrotay, new Attack[]{ (Attack.Earthquake),  (Attack.LowKick), (Attack.SeismicToss),  (Attack.ConfuseRay) });
		moves.put(MonsterID.Grandant, new Attack[]{ (Attack.StringShot),  (Attack.Spore), (Attack.PoisonSting),  (Attack.PinMissile) });
		moves.put(MonsterID.Skorpen, new Attack[]{ (Attack.StringShot),  (Attack.Spore), (Attack.PoisonSting),  (Attack.PinMissile) });
		moves.put(MonsterID.Supalm, new Attack[]{ (Attack.RazorLeaf),  (Attack.Spore), (Attack.LeechLife),  (Attack.LeechSeed) });
		moves.put(MonsterID.Crysanthum, new Attack[]{ (Attack.IceBeam),  (Attack.PoisonGas),	 (Attack.SolarBeam),  (Attack.PinMissile) });
		moves.put(MonsterID.Boomtu, new Attack[]{ (Attack.Psybeam),  (Attack.Thunder), (Attack.Nightshade),  (Attack.ConfuseRay) });
		moves.put(MonsterID.Haunting, new Attack[]{ (Attack.Lick),  (Attack.Teleport), (Attack.Counter),  (Attack.TwinNeedle) });
		moves.put(MonsterID.Nadifly, new Attack[]{ (Attack.Fly),  (Attack.Toxic), (Attack.PoisonSting),  (Attack.TwinNeedle) });
		moves.put(MonsterID.Adnocana, new Attack[]{ (Attack.Lick),  (Attack.BodySlam), (Attack.PoisonSting),  (Attack.TwinNeedle) });
		
		/**
		 * Adds the Battlemons to the HashMap
		 */
		monsters.put(MonsterID.Emberfly, Emberfly);
		monsters.put(MonsterID.Flamber, Flamber);
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
	public static Monster getMonster(MonsterID name) {
		if(monsters.containsKey(name)) {
			Monster m = new Monster(monsters.get(name));
			m.addMoves(moves.get(name));
			return m;
		}
		throw new IllegalArgumentException("Monster: [" + name.name() + "] not found.");
	}

	public static Monster getRandomMonster(Battle b){
		int randomIndex = Math.abs(b.rng_monster.nextInt(monsters.size()));
		List<Monster> list = new ArrayList<Monster>(monsters.values());
		Monster m = new Monster(list.get(randomIndex));
		m.addMoves(moves.get(m.getID()));
		m.listMoves();
		return m;
	}
}
