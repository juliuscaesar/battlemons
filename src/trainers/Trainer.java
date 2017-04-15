package trainers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import general.Battle;
import general.DTTrainer;
import general.Decision;
import general.MonsterID;
import monsters.Monster;

public class Trainer {
	public Map<MonsterID, Monster> monsters; // List of this Trainer's Monsters.
	private List<MonsterID> order; // List in order of this Trainer's Monsters.
	public Monster active; // The First Pokemon, The one in-battle.
	public String name; // Trainer's Name.
	public List<Item> items; // List of this Trainer's items.
	public Decision decision; // The decision made by the AI for this player.
	private DTTrainer trainerAI;

	public Trainer(String name, List<Monster> monsters, List<Item> itens){
		this.name = name;
		this.items = itens;
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

	public void clearMonsters(){
		this.monsters.clear();
		this.order.clear();
		this.active = null;
	}
	
	public void addMonster(Monster m){
		if(this.monsters.size() < 6){
			this.monsters.put(m.getID(), m);
			this.order.add(m.getID());
			if(this.active == null){
				this.active = m;
			}
		}
	}
	
	public List<Monster> listMonsters(){
		List<Monster> list = new ArrayList<>();
		for(MonsterID m : order){
			list.add(monsters.get(m));
		}
		return list;
	}
	
	public List<Item> listItems(){
        return items;
    }
	
	public void DisplayListOfMonsters()
	{
		System.out.println("The monsters for " + this.name + " are ");
		
		for (int i = 0; i < listMonsters().size(); i++)
		{
			System.out.print(listMonsters().get(i) + ", ");
		}
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

    public void makeDecision(Battle battle) {
        decision = trainerAI.makeDecision(battle, this, 0);
    }

    public Monster getActiveMonster() {
        return active;
    }

    public Decision getDecision() {
        return decision;
    }

    public boolean hasAliveMonster() {
        for (Monster m : monsters.values()) {
            if (m.isAlive()) return true;
        }
        return false;
    }

    public void chooseNewMonster() {

        List<Monster> choices = new ArrayList<Monster>();

        for (Monster m : monsters.values()) {
            if (m.isAlive()) {
                choices.add(m);
            }
        }

        if (choices.size() == 1) {
            changeActive(choices.get(0));
        } else {
            // TODO AI STUFF
        }
    }

}
