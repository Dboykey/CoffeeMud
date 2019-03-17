package com.planet_ink.coffee_mud.Items.Basic;
import com.planet_ink.coffee_mud.core.interfaces.*;
import com.planet_ink.coffee_mud.core.*;
import com.planet_ink.coffee_mud.core.collections.*;
import com.planet_ink.coffee_mud.Abilities.interfaces.*;
import com.planet_ink.coffee_mud.Areas.interfaces.*;
import com.planet_ink.coffee_mud.Behaviors.interfaces.*;
import com.planet_ink.coffee_mud.CharClasses.interfaces.*;
import com.planet_ink.coffee_mud.Commands.interfaces.*;
import com.planet_ink.coffee_mud.Common.interfaces.*;
import com.planet_ink.coffee_mud.Exits.interfaces.*;
import com.planet_ink.coffee_mud.Items.interfaces.*;
import com.planet_ink.coffee_mud.Libraries.interfaces.*;
import com.planet_ink.coffee_mud.Locales.interfaces.*;
import com.planet_ink.coffee_mud.MOBS.interfaces.*;
import com.planet_ink.coffee_mud.Races.interfaces.*;

import java.util.*;

/*
   Copyright 2019-2019 Bo Zimmerman

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

	   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
public class GenFixture extends GenItem
{
	@Override
	public String ID()
	{
		return "GenFixture";
	}

	public GenFixture()
	{
		super();
		setName("a generic fixture");
		basePhyStats().setWeight(20);
		setDisplayText("a generic fixture is here.");
		baseGoldValue=10;
		basePhyStats().setSensesMask(PhyStats.SENSE_ITEMNOTGET);
		material=RawMaterial.RESOURCE_OAK;
		recoverPhyStats();
	}

	@Override
	public boolean okMessage(final Environmental myHost, final CMMsg msg)
	{
		if((msg.target()==this)
		&&(((msg.targetMinor()==CMMsg.TYP_PUSH)
			||(msg.targetMinor()==CMMsg.TYP_PULL))))
		{
			final int savedSenses = basePhyStats().sensesMask();
			try
			{
				basePhyStats().setSensesMask(basePhyStats().sensesMask() & (~PhyStats.SENSE_ITEMNOTGET));
				phyStats().setSensesMask(phyStats().sensesMask() & (~PhyStats.SENSE_ITEMNOTGET));
				if(!super.okMessage(myHost, msg))
					return false;
				return true;
			}
			finally
			{
				basePhyStats().setSensesMask(savedSenses);
				recoverPhyStats();
			}
		}
		return super.okMessage(myHost, msg);
	}
}