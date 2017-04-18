package DT;

import general.Battle;
import general.Decision;
import general.Status;
import monsters.Monster;
import trainers.Antidote;
import trainers.AwakeningItem;
import trainers.BurnHealItem;
import trainers.EtherItem;
import trainers.IceHealItem;
import trainers.Item;
import trainers.ItemEnum;
import trainers.ParalyzHealItem;
import trainers.Trainer;

public class Behavior_HealStatus extends Behavior {
    public Decision execute(Battle battle, Trainer user) {

        Monster activeMon = user.getActiveMonster();

        if (activeMon.getStatus().equals(Status.Freeze)) {

            // Iterate through user's items
            for (Item i : user.items) {

                // If this item is of the type we need...
                if (i.getItemEnum() == ItemEnum.IceHeal) {

                    // And they have at least one - use it
                    if (i.quantity() > 0) {
                        return new Decision.UseIceHealItem(
                                (IceHealItem) i.effect, activeMon);
                    }
                    // Otherwise do nothing
                    else {
                        return null;
                    }
                }
            }
            return null;
        }

        if (activeMon.getStatus().equals(Status.Sleep)) {

            // Iterate through user's items
            for (Item i : user.items) {

                // If this item is of the type we need...
                if (i.getItemEnum() == ItemEnum.Awakening) {

                    // And they have at least one - use it
                    if (i.quantity() > 0) {
                        return new Decision.UseSleepHealItem(
                                (AwakeningItem) i.effect, activeMon);
                    }
                    // Otherwise do nothing
                    else {
                        return null;
                    }
                }
            }
            return null;
        }

        if (activeMon.getStatus().equals(Status.Burn)) {

            // Iterate through user's items
            for (Item i : user.items) {

                // If this item is of the type we need...
                if (i.getItemEnum() == ItemEnum.BurnHeal) {

                    // And they have at least one - use it
                    if (i.quantity() > 0) {
                        return new Decision.UseBurnHealItem(
                                (BurnHealItem) i.effect, activeMon);
                    }
                    // Otherwise do nothing
                    else {
                        return null;
                    }
                }
            }
            return null;
        }

        if (activeMon.getStatus().equals(Status.Paralysis)) {

            // Iterate through user's items
            for (Item i : user.items) {

                // If this item is of the type we need...
                if (i.getItemEnum() == ItemEnum.ParalyzHeal) {

                    // And they have at least one - use it
                    if (i.quantity() > 0) {
                        return new Decision.UseParalyzHealItem(
                                (ParalyzHealItem) i.effect, activeMon);
                    }
                    // Otherwise do nothing
                    else {
                        return null;
                    }
                }
            }
            return null;
        }

        if (activeMon.getStatus().equals(Status.Poison)) {

            // Iterate through user's items
            for (Item i : user.items) {

                // If this item is of the type we need...
                if (i.getItemEnum() == ItemEnum.Antidote) {

                    // And they have at least one - use it
                    if (i.quantity() > 0) {
                        return new Decision.UsePoisonHealItem(
                                (Antidote) i.effect, activeMon);
                    }
                    // Otherwise do nothing
                    else {
                        return null;
                    }
                }
            }
            return null;
        }

        return null;
    }
}
