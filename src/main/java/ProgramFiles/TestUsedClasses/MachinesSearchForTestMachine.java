package ProgramFiles.TestUsedClasses;

import ProgramFiles.GloballyUsedClasses.NavigationBarLinks;
import ProgramFiles.GloballyUsedClasses.OpenSelectedScreen;
import ProgramFiles.GloballyUsedClasses.WaitAndNotify;


public class MachinesSearchForTestMachine {
	
	public static String SelectTestMachine () throws InterruptedException {
		
		// Opens Operations dropdown:
		NavigationBarLinks.OpenDropdown(0);
		WaitAndNotify.StartWait("Machines test > Operation Menu call");
		
		// Creates an array of Operations dropdown links:
		MenuItemsList.ItemsList.CreateItemsList();
		WaitAndNotify.StartWait("Machines test > Menu Items List call");

		// Opens Machines screen from Operations dropdown:
		OpenSelectedScreen.OpenScreen(MenuItemsList.menuItemsText, 0, "");
		WaitAndNotify.StartWait("Machines test > Open Selected Screen call");

		// Search for machine:
		SelectMachineInHierarchy.SearchForMachine(MenuItemsList.menuItemsText.get(0));
//		WaitAndNotify.StartWait("Machines test > SelectMachineInHierarchy.SearchForMachine call");
		
		return MenuItemsList.menuItemsText.get(0);
		
	}//SelectTestMachine

}
