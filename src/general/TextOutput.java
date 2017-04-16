package general;

import trainers.Trainer;
import general.Decision;


public class TextOutput {
	
	// False denotes 'OFF'
	// a false value means minimal data would be printed to the console
	private boolean verboseSwitch = false; 
	
	
	
	// this method will be executed every time a trainer makes a move
	public void printStuffToConsole(Trainer user, Trainer opponent)
	{				
		if (!verboseSwitch)
		{
			// display the active monster of the user
			System.out.println("The active monster for the " + user.name +
					" is " + user.getActiveMonster());
			
			// display the active monster of the opponent 
			System.out.println("The active monster for the " + opponent.name +
					" is " + opponent.getActiveMonster());
			
			// display the decision chosen by AI for the user
			System.out.println(user.getActiveMonster() + 
					" used " + user.getDecision());			
		
		}
		
		else 
		{
			// display the active monster of the user
			System.out.println("The active monster for the " + user.name +
								" is " + user.getActiveMonster());
						
			// display the active monster of the opponent 
			System.out.println("The active monster for the " + opponent.name +
								" is " + opponent.getActiveMonster());
			
			// display the decision chosen by AI for the user
			System.out.println(user.getActiveMonster() + 
					" used " + user.getDecision());
			
			// display the HP of the active monster of the user
			System.out.println("The HP of the active monster of " + user.name 
					+ " is " + user.getActiveMonster().getHP());
			
			// display the current status of the user's monster
			System.out.println("The current status of the active monster of " +
			user.name + " is " + user.getActiveMonster().getStatus());
			
			// display the current status of the opponent's monster
			System.out.println("The current status of the active monster of " +
			opponent.name + " is " + opponent.getActiveMonster().getStatus());
			
			// display the HP of the active user of the opponent
			System.out.println("The HP of the active monster of " + opponent.name 
					+ " is " + opponent.getActiveMonster().getHP());
					
			
			
		}
	}
	
	
}
