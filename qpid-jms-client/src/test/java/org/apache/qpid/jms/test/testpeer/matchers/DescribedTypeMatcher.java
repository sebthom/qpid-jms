/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.qpid.jms.test.testpeer.matchers;

import org.apache.qpid.jms.test.testpeer.DescriptorMatcher;
import org.apache.qpid.proton.amqp.DescribedType;
import org.apache.qpid.proton.amqp.Symbol;
import org.apache.qpid.proton.amqp.UnsignedLong;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class DescribedTypeMatcher extends DescriptorMatcher
{
    private Matcher<?> valueMatcher;

    public DescribedTypeMatcher(UnsignedLong expectedDescriptorCode, Symbol expectedDescriptorSymbol, Matcher<?> value)
    {
        super(expectedDescriptorCode, expectedDescriptorSymbol);
        this.valueMatcher = value;
    }

    @Override
    public void describeTo(Description description)
    {
        super.describeTo(description);
        description.appendText(" and value ");
        valueMatcher.describeTo(description);
    }

    @Override
    protected boolean matchesSafely(DescribedType dt)
    {
        return super.matchesSafely(dt) && valueMatcher.matches(dt.getDescribed());
    }
}
