package DT;

import general.Battle;
import trainers.Trainer;
import trainers.Item;
import trainers.ItemEnum;

// Returns true if the current user has at least one Fresh Water in
// their inventory.
public class Condition_UserHasHealItem extends Condition {

    // Constructor
    public Condition_UserHasHealItem() {
        super();
        uses_parameter = false;
    }

    public boolean check_condition(Battle battle, Trainer user) {

        // Iterate across items
        for (Item i : user.listItems()) {

            // The only healing item is the Fresh Water
            if (i.quantity() > 0
                    && i.getItemEnum() == ItemEnum.FreshWater) {
                return true;
            }
        }
        return false;
    }
}
