package graph.parser;

import static org.junit.Assert.fail;
import graph.components.Graph;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testParseTxtFile() {
		ParserGraph parser = new ParserGraph();
		try {
			Graph graph;
			graph = parser.ParseTxtFile("SimpleSimpsonsCity1.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Error creating city file");
		}
		
	}

}
