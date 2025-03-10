package trainers;

import monsters.Monster;
import moves.MoveSet;
import general.Attack;
import moves.Move;

public class Item {
	private final ItemEnum item;
	private int quantity;
	public ItemEffect effect;
	
	public Item(ItemEnum item, int quantity){
		this.item = item;
		this.quantity = quantity;
		this.effect = ItemEffectCreator.getEffect(item);
	}
	
	public boolean useOn(Monster m){
		if(quantity > 0){
			if(this.effect.canUseItem(m)){
				this.effect.useItem(m);
				quantity--;
				return true;
			}
		}
		return false;
	}
	
	public ItemEnum getItemEnum() {
	    return item;
	}
	
	public boolean useOnMove(Monster m, Attack attack){
		if(quantity > 0){
			Move mov = MoveSet.getMove(attack);
			if(this.effect.canUseItemOnMove(m, attack)){
				this.effect.useItemOnMove(m, mov);
				quantity--;
				return true;
			}
		}
		return false;
	}
	
	public int quantity(){
		return this.quantity;
	}
	
	public boolean isEqual(Item other){
		return (this.item == other.item);
	}
	
	
}