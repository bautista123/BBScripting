package org.parabot.bbherblore.methods;

import org.parabot.bbherblore.data.HerbloreVariables;
import org.parabot.environment.api.utils.Time;
import org.rev317.api.methods.Bank;

public class HerbloreWithdrawMethods {

	public static void WithdrawSupplies(int[] i) {
		if (HerbloreVariables.getMakeXT()) {
			for (int s : i) {
				Bank.withdraw(s, 14);
			}
		} else if (HerbloreVariables.getMakeOvls()) {
			for (int s : i) {
				Bank.withdraw(s, 4);
				Time.sleep(500);
			}
		}
	}
}
