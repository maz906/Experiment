package dynamicList;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DTLTest {
	
	DynamicTypeList list;

	@Before
	public void setUp() throws Exception {
		list = new DynamicTypeList();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		list.add(1);
		list.add("hahaha");
		assertEquals(1, (int) list.get(0));
		assertEquals("hahaha", (String) list.get(1));
		list.add(list);
		assertEquals(list, (DynamicTypeList) list.get(2));
		assertTrue(!list.get(0).equals(list.get(1)));
		list.add(1);
		assertTrue(list.get(0).equals(list.get(3)));
		assertTrue(list.remove(new Integer(1)));
		System.out.println(list.toString());
		assertTrue("hahaha".equals((String) list.get(0)));
		assertTrue(list.contains("hahaha"));
	}

}
