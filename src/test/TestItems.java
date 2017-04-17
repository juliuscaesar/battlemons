package test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

import org.junit.Test;

import monsters.*;
import trainers.*;
import general.*;

public class TestItems {
	ItemEffect ant = ItemEffectCreator.getEffect(ItemEnum.Antidote);
	ItemEffect awak = ItemEffectCreator.getEffect(ItemEnum.Awakening);
	ItemEffect para = ItemEffectCreator.getEffect(ItemEnum.ParalyzHeal);
	ItemEffect burn = ItemEffectCreator.getEffect(ItemEnum.BurnHeal);
	ItemEffect ice = ItemEffectCreator.getEffect(ItemEnum.IceHeal);
	Map<ItemEffect, Status> efs;
	Map<ItemEnum, Status> enm;
	
	private void fillMap(){
		efs = new HashMap<>();
		efs.put(ant, Status.Poison);
		efs.put(awak, Status.Sleep);
		efs.put(para, Status.Paralysis);
		efs.put(burn, Status.Burn);
		efs.put(ice, Status.Freeze);
		
		enm = new HashMap<>();
		enm.put(ItemEnum.Antidote, Status.Poison);
		enm.put(ItemEnum.Awakening, Status.Sleep);
		enm.put(ItemEnum.ParalyzHeal, Status.Paralysis);
		enm.put(ItemEnum.BurnHeal, Status.Burn);
		enm.put(ItemEnum.IceHeal, Status.Freeze);
	}

	
	//Testing (Status Remover)s.
	//@Test
	public void test01(){
		fillMap();
		for(MonsterID id : MonsterID.values()){
			Monster m = MonsterSet.getMonster(id);
			for(Status s : Status.values()){
				m.setStatus(s);
				for(Entry<ItemEffect, Status> entry : efs.entrySet()){
					if(entry.getValue() != s){
						assertEquals(entry.getKey().canUseItem(m), false);
					}
				}
			}
		}
	}
	
	//Testing if Item with negative quantity cannot be used.
	//@Test
	public void test02(){
		fillMap();
		for(MonsterID id : MonsterID.values()){
			Monster m = MonsterSet.getMonster(id);
			for(Entry<ItemEnum, Status> entry : enm.entrySet()){
				int numberOfItems = 10;
				Item item = new Item(entry.getKey(), numberOfItems);
				for(int i = 0; i < numberOfItems; i++){
					m.setStatus(entry.getValue());
					assertEquals(item.useOn(m), true);
				}
				assertEquals(item.useOn(m), false);
			}
		}
	}
	

	@Test
	public void test__ether__01(){
		fillMap();
		for(MonsterID id : MonsterID.values()){
			Monster m = MonsterSet.getMonster(id);
			for(Attack atk : m.listMoves()){
				Item ether = new Item(ItemEnum.Ether, 10);
				for(int i = 0; i < 10; i++){
					m.useMove(atk);
					assertEquals(ether.useOnMove(m, atk), true);
				}
				assertEquals(ether.useOnMove(m, atk), false);
			}
		}
	}
	
	
	@Test
	public void test__ether__02(){
		fillMap();
		for(MonsterID id : MonsterID.values()){
			Monster m = MonsterSet.getMonster(id);
			for(Attack atk : m.listMoves()){
				Item ether = new Item(ItemEnum.Ether, 10);
				assertEquals(ether.useOnMove(m, atk), false);
			}
		}
	}
	
	@Test
	public void test__ether__03(){
		fillMap();
		for(MonsterID id : MonsterID.values()){
			Monster m = MonsterSet.getMonster(id);
			for(Attack atk : m.listMoves()){
				Item ether = new Item(ItemEnum.Ether, 10);
				for(int i = 0; i < 10; i++){
					assertEquals(m.canIncreasePP(atk), false);
					m.useMove(atk);
					assertEquals(m.canIncreasePP(atk), true);
					ether.useOnMove(m, atk);
					assertEquals(m.canIncreasePP(atk), false);
				}
				assertEquals(ether.useOnMove(m, atk), false);
			}
			System.out.println("");
		}
	}
	
	@Test
	public void test__freshWater__01(){
		for(MonsterID id : MonsterID.values()){
			Monster m = MonsterSet.getMonster(id);
			Item water = new Item(ItemEnum.FreshWater, 10);
			assertEquals(water.useOn(m), false);
			m.receiveAttack(50);
			assertEquals(water.useOn(m), true);
			assertEquals(water.useOn(m), false);
		}
	}
	
	@Test
	public void test__freshWater__02(){
		for(MonsterID id : MonsterID.values()){
			Monster m = MonsterSet.getMonster(id);
			Item water = new Item(ItemEnum.FreshWater, 10);
			for(int i = 0; i < 10; i++){
				assertEquals(water.useOn(m), false);
				m.receiveAttack(50);
				assertEquals(water.useOn(m), true);
				assertEquals(water.useOn(m), false);
			}
			m.receiveAttack(50);
			assertEquals(water.useOn(m),false);
		}
	}
}
