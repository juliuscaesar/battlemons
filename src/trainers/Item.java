package trainers;

import monsters.Monster;
import general.Attack;

public class Item {
	private final ItemEnum item;
	private int quantity;
	private ItemEffect effect;
	
	Item(ItemEnum item, int quantity){
		this.item = item;
		this.quantity = quantity;
		this.effect = ItemEffectCreator.getEffect(item);
	}
	
	public boolean useOn(Monster m){
		if(this.effect.canUseItem(m)){
			this.effect.useItem(m);
		}
		return false;
	}
	
	public boolean useOnMove(Monster m, Attack attack){
		
	}
	
	public int quantity(){
		return this.quantity;
	}
	
	public boolean isEqual(Item other){
		return (this.item == other.item);
	}
	
	
}
