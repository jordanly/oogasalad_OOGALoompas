package test.java.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import main.java.data.DataHandler;
import main.java.exceptions.data.InvalidGameBlueprintException;
import main.java.schema.GameBlueprint;
import main.java.schema.GameSchema;
import net.lingala.zip4j.exception.ZipException;

import org.junit.Test;

public class TestDataHandler {

	public final static String FILE_PATH = "src/test/resources/";
	public final static String BLUEPRINT_PATH = "TestBlueprint.ser";
	public final static String SAVEBLUEPRINT_PATH = "SavedBlueprint";
	public final static String ZIPPED_RESOURCES = "ZippedResources.zip";
	public final static String STATE_PATH = "TestState.ser";
	public final static String TEST_ATTRIBUTE_1 = "testAttribute1";
	public final static String TEST_VALUE_1 = "testValue1";
	public final static int CURRENT_WAVE_NUMBER = 43;

	//	@Test
	//	public void testBlueprintSavingAndLoading() {
	//		//Set up blueprint
	//		GameSchema scenario = new GameSchema();
	//		scenario.addAttribute(TEST_ATTRIBUTE_1, TEST_VALUE_1);
	//		GameBlueprint savedBlueprint = new GameBlueprint();
	//		savedBlueprint.setMyGameScenario(scenario);
	//		
	//		//Try to save blueprint
	//		DataHandler dataHandler = new DataHandler();
	//		if (!dataHandler.saveBlueprint(savedBlueprint, FILE_PATH + BLUEPRINT_PATH))
	//			fail();
	//		
	//		//Load blueprint
	//		GameSchema loadedSchema = null;
	//		try {
	//			GameBlueprint loadedBlueprint = dataHandler.loadBlueprint(FILE_PATH + BLUEPRINT_PATH);
	//			loadedSchema = loadedBlueprint.getMyGameScenario();
	//		} catch (ClassNotFoundException | IOException e) {
	//			fail();
	//		}
	//		
	//		//Check if the variable values are the same
	//		assertTrue(loadedSchema.getAttributesMap().get(TEST_ATTRIBUTE_1).equals(TEST_VALUE_1));
	//		assertFalse(loadedSchema.getAttributesMap().get(TEST_ATTRIBUTE_1).equals("THIS SHOULDNT MATCH WITH ANYTHING"));
	//	}

	/**
	 * test to see if an incomplete blueprint
	 * causes testDataHandler to throw a
	 * InvalidGameBlueprintException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws ZipException
	 */
	@Test(expected=InvalidGameBlueprintException.class)
	public void testEngineLoadingBlueprint() throws ClassNotFoundException, IOException, ZipException {
		DataHandler testDataHandler = new DataHandler();
		GameSchema testSchema = new GameSchema();
		testSchema.addAttribute("Lives",10);
		GameBlueprint testBlueprint = new GameBlueprint();
		testBlueprint.setMyGameScenario(testSchema);
		testDataHandler.saveBlueprint(testBlueprint, FILE_PATH + SAVEBLUEPRINT_PATH);
		testDataHandler.loadBlueprint(FILE_PATH + "SavedBlueprintZippedAuthoringEnvironment.zip",true);
	}

	/**
	 * Tests compression and decompression
	 * only works if saveObjectFromFile
	 * and loadObjectFromFile are public
	 * so we can test pre-compressed size
	 * and post-compressed size
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws ZipException
	 */
	@Test
	public void testCompressionAndDecompression() throws ClassNotFoundException, IOException, ZipException {
		DataHandler testDataHandler = new DataHandler();
		//set up gameblueprint, testing by just adding a gameschema
		GameSchema testSchema = new GameSchema();
		testSchema.addAttribute("Lives",10);
		GameBlueprint testBlueprint = new GameBlueprint();
		testBlueprint.setMyGameScenario(testSchema);
		testDataHandler.saveObjectToFile(testBlueprint, FILE_PATH + BLUEPRINT_PATH); // 555 bytes
		testDataHandler.saveBlueprint(testBlueprint, FILE_PATH + SAVEBLUEPRINT_PATH);
		GameBlueprint loadedBlueprint = testDataHandler.loadBlueprint(FILE_PATH + "SavedBlueprintZippedAuthoringEnvironment.zip",false);
		String savedBlueprintLocation =  FILE_PATH + "testSerialzedBlueprint.ser";
		testDataHandler.saveObjectToFile(loadedBlueprint, savedBlueprintLocation);
		File serializedTestBlueprint = new File(savedBlueprintLocation);
		File testBlueprintFile = new File(savedBlueprintLocation);
		assertEquals(testBlueprintFile.length(),serializedTestBlueprint.length());
		assertEquals(testBlueprint.getMyGameScenario().getAttributesMap().get("Lives"),
				((GameBlueprint) testDataHandler.loadObjectFromFile(savedBlueprintLocation)).getMyGameScenario().getAttributesMap().get("Lives"));

		//		System.out.println(testDataHandler.saveBlueprint(testBlueprint, FILE_PATH + SAVEBLUEPRINT_PATH));
	}


}
