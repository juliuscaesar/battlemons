package trainers;

/**
 * Holds a static method to create generate the correct Item Class
 * based on the ItemEnum.
 */
public abstract class ItemEffectCreator{
	
	static ItemEffect getEffect(ItemEnum item){
		switch(item){
		case Antidote:{
			return new Antidote();
		}
		case Awakening: {
			return new AwakeningItem();
		}
		case BurnHeal: {
			return new BurnHealItem();
		}
		case Ether:{
			return new EtherItem();
		}
		case FreshWater:{
			return new FreshwaterItem();
		}
		case IceHeal:{
			return new IceHealItem();
		}
		case ParalyzHeal:{
			return new ParalyzHealItem();
		}
		default:{
			return null;
		}
		}
	}
}