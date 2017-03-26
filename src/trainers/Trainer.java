package trainers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import general.MonsterID;

import monsters.Monster;

public class Trainer {
	private Map<MonsterID, Monster> monsters; // List of this Trainer's Monsters.
	private List<MonsterID> order; // List in order of this Trainer's Monsters.
	private Monster active; // The First Pokemon, The one in-battle.
	private String name; // Trainer's Name.
	private List<Item> itens; // List of this Trainer's itens.
	
	Trainer(String name, List<Monster> monsters, List<Item> itens){
		this.name = name;
		this.itens = itens;
		if(monsters.size() == 0) {
			throw new IllegalArgumentException("Trainer can't have no Monsters.");
		}
		Collections.shuffle(monsters);
		this.monsters = new HashMap<>();
		this.order = new ArrayList<>();
		for(Monster m : monsters){
			this.monsters.put(m.getID(), m);
			this.order.add(m.getID());
		}
		active = monsters.get(0);
	}
	
	public List<Monster> listMonsters(){
		List<Monster> list = new ArrayList<>();
		for(MonsterID m : order){
			list.add(monsters.get(m));
		}
		return list;
	}
	
	/**
	 * Change the Monster in-battle.
	 * 
	 * @param m the new Active Monster.
	 */
	public void changeActive(Monster m){
		if(m.isAlive()){
			this.active = monsters.get(m.getID());
		}
		throw new IllegalArgumentException("Can't select dead Monsters.");
	}

}
