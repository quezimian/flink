/***********************************************************************************************************************
 *
 * Copyright (C) 2010 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/
package eu.stratosphere.sopremo.serialization;

import org.junit.Ignore;
import org.junit.Test;

import eu.stratosphere.pact.common.type.PactRecord;
import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.ArrayNodeBaseTest;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.IntNode;

/**
 * @author Michael Hopstock
 */
@Ignore
public class LazyTailArrayNodeTest extends ArrayNodeBaseTest<LazyTailArrayNode> {

	/*
	 * (non-Javadoc)
	 * @see eu.stratosphere.sopremo.type.ArrayNodeBaseTest#initArrayNode()
	 */
	@Override
	public void initArrayNode() {
		final TailArraySchema schema = new TailArraySchema(5);
		final PactRecord record = schema.jsonToRecord(
			new ArrayNode(IntNode.valueOf(0), IntNode.valueOf(1), IntNode.valueOf(2)), null, null);

		this.node = new LazyTailArrayNode(record, schema);
	}

	@Override
	public void testValue() {
	}

	@Override
	protected IJsonNode lowerNode() {
		TailArraySchema schema = new TailArraySchema(5);
		PactRecord record = schema.jsonToRecord(
			new ArrayNode(IntNode.valueOf(0), IntNode.valueOf(1), IntNode.valueOf(2)), null, null);

		return new LazyTailArrayNode(record, schema);
	}

	@Override
	protected IJsonNode higherNode() {
		TailArraySchema schema = new TailArraySchema(5);
		PactRecord record = schema.jsonToRecord(
			new ArrayNode(IntNode.valueOf(0), IntNode.valueOf(1), IntNode.valueOf(3)), null, null);

		return new LazyTailArrayNode(record, schema);
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	public void shouldNormalizeKeys() {
		super.shouldNormalizeKeys();
	}

}
