package DT;

import general.Battle;
import general.Decision;
import monsters.Monster;
import trainers.FreshwaterItem;
import trainers.Item;
import trainers.ItemEnum;
import trainers.Trainer;

public class Behavior_HealHP extends Behavior {
    public Decision execute(Battle battle, Trainer user) {
        Monster activeMonsterOfUser = user.getActiveMonster();

        // Iterate through user's items
        for (Item i : user.items) {
            
            // If this item is of the type we need...
            if (i.getItemEnum() == ItemEnum.FreshWater) {
                
                // And they have at least one - use it
                if (i.quantity() > 0) {
                    return new Decision.UseHealHPItem(
                            (FreshwaterItem) i.effect, activeMonsterOfUser);
                } 
                // Otherwise do nothing
                else {
                    return null;
                }
            }
        }
        return null;
    }
}