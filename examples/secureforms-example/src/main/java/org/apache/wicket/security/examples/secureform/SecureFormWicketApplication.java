/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.security.examples.secureform;

import org.apache.wicket.security.swarm.SwarmWebApplication;
import org.apache.wicket.security.hive.config.PolicyFileHiveFactory;
import org.apache.wicket.security.hive.config.SwarmPolicyFileHiveFactory;
import org.apache.wicket.security.hive.HiveMind;
import org.apache.wicket.security.examples.secureform.pages.HomePage;
import org.apache.wicket.security.examples.secureform.pages.LoginPage;
import org.apache.wicket.WicketRuntimeException;

import java.net.MalformedURLException;

/**
 * 
 */
public class SecureFormWicketApplication extends SwarmWebApplication
{    
    /**
     * Constructor
     */
	public SecureFormWicketApplication()
	{
	}

    /**
	 * @see SecureFormWicketApplication#getHomePage()
	 */
	public Class getHomePage()
	{
		return HomePage.class;
	}

    protected void setUpHive() {
        if (HiveMind.getHive(getHiveKey()) == null) {
            PolicyFileHiveFactory factory = new SwarmPolicyFileHiveFactory(getActionFactory());
            try {
                factory.addPolicyFile(getServletContext().getResource("/WEB-INF/secure-rules.hive"));
            } catch (MalformedURLException e) {
                throw new WicketRuntimeException(e);
            }
            HiveMind.registerHive(getHiveKey(), factory);
        }
    }

    protected Object getHiveKey() {
        return "sample-static-key";
    }

    public Class getLoginPage() {
        return LoginPage.class;
    }
}
