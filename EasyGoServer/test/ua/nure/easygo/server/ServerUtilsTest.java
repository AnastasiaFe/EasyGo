package ua.nure.easygo.server;

import org.junit.Assert;
import org.junit.Test;

public class ServerUtilsTest {

	@Test
	public void testParseTags(){
		String tagsToParse = "#tag1 #tag2";
		String[] actual = ServerUtils.parseTags(tagsToParse);
		String[] expected = new String[] {"tag1", "tag2"};
		Assert.assertArrayEquals(expected, actual);
	}
	
	@Test
	public void testSearchByTags(){
		
	}
	
	@Test
	public void testGetAllPublicMaps(){
				
	}
	
	@Test
	public void testGetAllUserMaps(){
		
	}
	
	@Test
	public void testEditMark(){
		
	}
}
