package org.parabot.bbherblore.strategies;

import org.parabot.bbherblore.methods.HerbloreMethods;
import org.parabot.bbherblore.methods.HerbloreReturns;
import org.parabot.core.ui.components.LogArea;
import org.parabot.environment.scripts.framework.Strategy;

public class HerbloreBankClose implements Strategy {

	@Override
	public boolean activate() {

		if (HerbloreReturns.needBankClose()) {
			LogArea.log("closing method");
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		try {
			if (HerbloreReturns.needBankClose()) {
				LogArea.log("closing bank");
				HerbloreMethods.Click(487, 30);
			}
		} catch (Exception fucker) {

		}
	}

}
