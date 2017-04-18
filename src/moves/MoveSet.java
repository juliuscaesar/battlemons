package moves;
import java.util.HashMap;
import java.util.Map;

import general.Attack;
import general.Element;
import general.MoveCategory;
import general.Status;


public class MoveSet {
  private static Map<Attack, Move> moves;
  
	  
  static {
    moves = new HashMap<>();
   // moves.put(Attack.Acid, Move(Attack.Acid, 100, 100, 10, Element.Bug, MoveCategory.Physical));
    
    Move AcidMove = new Move(Attack.Acid, 40, 100, 30, Element.Poison, MoveCategory.Special);
	  Move AmnesiaMove = new Move(Attack.Amnesia, 0, 0, 20, Element.Psychic, MoveCategory.Status);
	  Move BarrageMove = new Move(Attack.Barrage, 15, 85, 20, Element.Normal, MoveCategory.Physical);
	  Move BarrierMove = new Move(Attack.Barrier, 0, 0, 20, Element.Psychic, MoveCategory.Status);
	  Move BlizzardMove = new Move(Attack.Blizzard, 110, 70, 5, Element.Ice, MoveCategory.Special);
	  Move BodySlamMove = new Move(Attack.BodySlam, 85, 100, 15, Element.Normal, MoveCategory.Physical);
	  Move BonemerangMove = new Move(Attack.Bonemerang, 50, 90, 10, Element.Ground, MoveCategory.Physical);
	  Move BubbleMove = new Move(Attack.Bubble, 40, 100, 30, Element.Water, MoveCategory.Special);
	  Move BubblebeamMove = new Move(Attack.Bubblebeam, 65, 100, 20, Element.Water, MoveCategory.Special);
	  Move CometPunchMove = new Move(Attack.CometPunch, 18, 15, 20, Element.Normal, MoveCategory.Physical);
	  Move ConfuseRayMove = new Move(Attack.ConfuseRay, 0, 100, 10, Element.Ghost, MoveCategory.Status);
	  Move CounterMove = new Move(Attack.Counter, 0, 100, 20, Element.Fighting, MoveCategory.Physical);
	   Move CutMove = new Move(Attack.Cut, 50, 95, 30, Element.Normal, MoveCategory.Physical);
	   Move DigMove = new Move(Attack.Dig, 80, 100, 10, Element.Ground, MoveCategory.Physical);
	    Move DoubleKickMove = new Move(Attack.DoubleKick, 30, 100, 30, Element.Fighting, MoveCategory.Physical);
	    Move EarthquakeMove = new Move(Attack.Earthquake, 100, 100, 10, Element.Ground, MoveCategory.Physical);
	    Move EmberMove = new Move(Attack.Ember, 40, 100, 25, Element.Fire, MoveCategory.Special);
	    Move FireBlastMove = new Move(Attack.FireBlast, 110, 85, 5, Element.Fire, MoveCategory.Special);
	    Move FirePunchMove = new Move(Attack.FirePunch, 75, 100, 15, Element.Fire, MoveCategory.Physical);
	    Move FireSpinMove = new Move(Attack.FireSpin, 35, 85, 15, Element.Fire, MoveCategory.Special);
	   Move FissureMove = new Move(Attack.Fissure, 0, 0, 5, Element.Ground, MoveCategory.Physical);
	    Move FlamethrowerMove = new Move(Attack.Flamethrower, 90, 100, 15, Element.Fire, MoveCategory.Special);
	    Move FlyMove = new Move(Attack.Fly, 90, 95, 15, Element.Flying, MoveCategory.Physical);
	    Move GustMove = new Move(Attack.Gust, 40, 100, 35, Element.Flying, MoveCategory.Special);
	    Move HazeMove = new Move(Attack.Haze, 0, 0, 30, Element.Ice, MoveCategory.Status);
	    Move HighJumpKickMove = new Move(Attack.HighJumpKick, 130, 90, 10, Element.Fighting, MoveCategory.Physical);
	    Move IceBeamMove = new Move(Attack.IceBeam, 90, 100, 10, Element.Ice, MoveCategory.Special);
	    Move IcePunchMove = new Move(Attack.IcePunch, 75, 100, 15, Element.Ice, MoveCategory.Physical);
	    Move KinesisMove = new Move(Attack.Kinesis, 0, 80, 15, Element.Psychic, MoveCategory.Status);
	    Move LeechLifeMove = new Move(Attack.LeechLife, 80, 100, 10, Element.Bug, MoveCategory.Physical);
	    Move LeechSeedMove = new Move(Attack.LeechSeed, 0, 90, 10, Element.Grass, MoveCategory.Status);
	    Move LickMove = new Move(Attack.Lick, 30, 100, 30, Element.Ghost, MoveCategory.Physical);
	    Move LowKickMove = new Move(Attack.LowKick, 0, 100, 20, Element.Fighting, MoveCategory.Physical);
	    Move MistMove = new Move(Attack.Mist, 0, 0, 30, Element.Ice, MoveCategory.Status);
	    Move NightshadeMove = new Move(Attack.Nightshade, 0, 100, 15, Element.Ghost, MoveCategory.Special);
	    Move PeckMove = new Move(Attack.Peck, 35, 100, 35, Element.Flying, MoveCategory.Physical);
	    Move PinMissileMove = new Move(Attack.PinMissile, 25, 95, 20, Element.Bug, MoveCategory.Physical);
	    Move PoisonGasMove = new Move(Attack.PoisonGas, 0, 90, 40, Element.Poison, MoveCategory.Status);
	    Move PoisonStingMove = new Move(Attack.PoisonSting, 15, 100, 35, Element.Poison, MoveCategory.Physical);
	    Move PsybeamMove = new Move(Attack.Psybeam, 65, 100, 20, Element.Psychic, MoveCategory.Special);
	    Move RazorLeafMove = new Move(Attack.RazorLeaf, 55, 95, 25, Element.Grass, MoveCategory.Physical);
	    Move SandattackMove = new Move(Attack.Sandattack, 0, 100, 15, Element.Ground, MoveCategory.Status);
	    Move ScratchMove = new Move(Attack.Scratch, 40, 100, 35, Element.Normal, MoveCategory.Physical);
	    Move SeismicTossMove = new Move(Attack.SeismicToss, 0, 100, 20, Element.Fighting, MoveCategory.Physical);
	   Move SkyAttackMove = new Move(Attack.SkyAttack, 140, 90, 5, Element.Flying, MoveCategory.Physical);
	   Move SmogMove = new Move(Attack.Smog, 30, 70, 20, Element.Poison, MoveCategory.Special);
	   Move SolarBeamMove = new Move(Attack.FirePunch, 120, 100, 10, Element.Grass, MoveCategory.Special);
	   Move SporeMove = new Move(Attack.Spore, 0, 100, 15, Element.Grass, MoveCategory.Status);
	    Move StringShotMove = new Move(Attack.StringShot, 0, 95, 40, Element.Bug, MoveCategory.Status);
	    Move StruggleMove = new Move(Attack.Struggle, 50, 100, 0, Element.Normal, MoveCategory.Physical);
	    Move SurfMove = new Move(Attack.Surf, 90, 100, 15, Element.Water, MoveCategory.Special);
	   Move TeleportMove = new Move(Attack.Teleport, 0, 0, 20, Element.Psychic, MoveCategory.Status);
	    Move ThunderMove = new Move(Attack.Thunder, 110, 70, 10, Element.Electric, MoveCategory.Special);
	   Move ThunderboltMove = new Move(Attack.Thunderbolt, 90, 100, 15, Element.Electric, MoveCategory.Special);
	    Move ThunderPunchMove = new Move(Attack.ThunderPunch, 75, 100, 15, Element.Electric, MoveCategory.Physical);
	   Move ThunderShockMove = new Move(Attack.ThunderShock, 40, 100, 30, Element.Electric, MoveCategory.Special);
	    Move ThunderWaveMove = new Move(Attack.ThunderWave, 0, 90, 20, Element.Electric, MoveCategory.Status);
	    Move ToxicMove = new Move(Attack.Toxic, 0, 90, 10, Element.Poison, MoveCategory.Status);
	    Move TwinNeedleMove = new Move(Attack.TwinNeedle, 25, 100, 20, Element.Bug, MoveCategory.Physical);
	    Move VineWhipMove = new Move(Attack.VineWhip, 45, 100, 25, Element.Grass, MoveCategory.Physical);
	    Move WaterfallMove = new Move(Attack.Waterfall, 80, 100, 15, Element.Water, MoveCategory.Physical);
	    Move WaterGunMove = new Move(Attack.WaterGun, 40, 100, 25, Element.Water, MoveCategory.Special);
	    Move WingAttackMove = new Move(Attack.WingAttack, 60, 100, 35, Element.Flying, MoveCategory.Physical);
	    
	    //If MoveCategory == Status , Status Accuracy = Set to 100.
	    //If MoveCategory != Status, Status Accuracy = Anything you want ( Note that, over 10 is a little broken, try to keep it at 10, max 20).
	    ThunderboltMove.addStatus(Status.Paralysis, 10);
	    EmberMove.addStatus(Status.Burn, 10);
	    ToxicMove.addStatus(Status.Poison, 100);
	  
    
    moves.put(Attack.Acid, AcidMove);
    moves.put(Attack.Amnesia, AmnesiaMove);
    moves.put(Attack.Barrage, BarrageMove);
    moves.put(Attack.Barrier, BarrierMove);
    moves.put(Attack.Blizzard, BlizzardMove);
    moves.put(Attack.BodySlam, BodySlamMove);
    moves.put(Attack.Bonemerang, BonemerangMove);
    moves.put(Attack.Bubble, BubbleMove);
    moves.put(Attack.Bubblebeam, BubblebeamMove);
    moves.put(Attack.CometPunch, CometPunchMove);
    moves.put(Attack.ConfuseRay, ConfuseRayMove);
    moves.put(Attack.Counter, CounterMove);
    moves.put(Attack.Cut, CutMove);
    moves.put(Attack.Dig, DigMove);
    moves.put(Attack.DoubleKick, DoubleKickMove);
    moves.put(Attack.Earthquake, EarthquakeMove);
    moves.put(Attack.Ember	, EmberMove);
    moves.put(Attack.FireBlast, FireBlastMove);
    moves.put(Attack.FirePunch, FirePunchMove);
    moves.put(Attack.FireSpin	, FireSpinMove);
    moves.put(Attack.Fissure, FissureMove);
    moves.put(Attack.Flamethrower, FlamethrowerMove);
    moves.put(Attack.Fly, FlyMove);
    moves.put(Attack.Gust, GustMove);
    moves.put(Attack.Haze, HazeMove);
    moves.put(Attack.HighJumpKick, HighJumpKickMove);
    moves.put(Attack.IceBeam, IceBeamMove);
    moves.put(Attack.IcePunch, IcePunchMove);
    moves.put(Attack.Kinesis, KinesisMove);
    moves.put(Attack.LeechLife, LeechLifeMove);
    moves.put(Attack.LeechSeed, LeechSeedMove);
    moves.put(Attack.Lick, LickMove);
    moves.put(Attack.LowKick, LowKickMove);
    moves.put(Attack.Mist, MistMove);
    moves.put(Attack.Nightshade, NightshadeMove);
    moves.put(Attack.Peck, PeckMove);
    moves.put(Attack.PinMissile, PinMissileMove);
    moves.put(Attack.PoisonGas, PoisonGasMove);
    moves.put(Attack.PoisonSting, PoisonStingMove);
    moves.put(Attack.Psybeam, PsybeamMove);
    moves.put(Attack.RazorLeaf, RazorLeafMove);
    moves.put(Attack.Sandattack, SandattackMove);
    moves.put(Attack.Scratch, ScratchMove);
    moves.put(Attack.SeismicToss, SeismicTossMove);
    moves.put(Attack.SkyAttack, SkyAttackMove);
    moves.put(Attack.Smog, SmogMove);
    moves.put(Attack.SolarBeam, SolarBeamMove);
    moves.put(Attack.Spore, SporeMove);
    moves.put(Attack.StringShot, StringShotMove);
    moves.put(Attack.Struggle, StruggleMove);
    moves.put(Attack.Surf, SurfMove);
    moves.put(Attack.Teleport, TeleportMove);
    moves.put(Attack.Thunder, ThunderMove);
    moves.put(Attack.Thunderbolt, ThunderboltMove);
    moves.put(Attack.ThunderPunch, ThunderPunchMove);
    moves.put(Attack.ThunderShock, ThunderShockMove);
    moves.put(Attack.ThunderWave, ThunderWaveMove);
    moves.put(Attack.Toxic, ToxicMove);
    moves.put(Attack.TwinNeedle, TwinNeedleMove);
    moves.put(Attack.VineWhip, VineWhipMove);
    moves.put(Attack.Waterfall, WaterfallMove);
    moves.put(Attack.WaterGun, WaterGunMove);
    moves.put(Attack.WingAttack, WingAttackMove);

  }
  
  

  public static Move getMove(Attack name){
    if(moves.containsKey(name)) {
      return new Move(moves.get(name));
    }
    throw new IllegalArgumentException("Move: [" + name.name() + "] not found."); 
  }
  
  public Move move(Attack name){
	  if(moves.containsKey(name)){
		  return new Move(moves.get(name));
	  }
	  return null;
  }
}

