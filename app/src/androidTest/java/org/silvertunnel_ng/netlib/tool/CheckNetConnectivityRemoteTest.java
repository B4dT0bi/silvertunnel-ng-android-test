/*
 * silvertunnel.org Netlib - Java library to easily access anonymity networks
 * Copyright (c) 2009-2012 silvertunnel.org
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses/>.
 */
/*
 * silvertunnel-ng.org Netlib - Java library to easily access anonymity networks
 * Copyright (c) 2013 silvertunnel-ng.org
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses/>.
 */

package org.silvertunnel_ng.netlib.tool;

import android.test.InstrumentationTestCase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.silvertunnel_ng.netlib.adapter.nameservice.NameServiceGlobalUtil;

/**
 * This test case executes the checks of CheckNetConnectivity: Execution of
 * these checks during automatic test execution could provide additional
 * information.
 * 
 * @author hapke
 * @author Tobias Boese
 */
public class CheckNetConnectivityRemoteTest extends InstrumentationTestCase
{
	@BeforeClass
	public void setUp()
	{
		if (!NameServiceGlobalUtil.isDefaultIpNetAddressNameServiceActive())
		{
			NameServiceGlobalUtil.activateDefaultIpNetAddressNameService();
		}			
	}
	@Test(timeout = 20000)
	public void testExecuteCheck() throws Exception
	{
		final boolean result = CheckNetConnectivity.executeCheck(true);
		assertEquals("wrong result of executeCheck()", true, result);
	}
}
