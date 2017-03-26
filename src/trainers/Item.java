package trainers;

import monsters.Monster;

public class Item {
	private final ItemEnum item;
	private int quantity;
	
	Item(ItemEnum item, int quantity){
		this.item = item;
		this.quantity = quantity;
	}
	
	public void useOn(Monster m){
		
	}
	
	public int quantity(){
		return this.quantity;
	}
	
	public boolean isEqual(Item other){
		return (this.item == other.item);
	}
}
