package com.infintyloop.buckeyebuilder;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.infintyloop.buckeyebuilder.Building;
import android.os.Parcel;
import android.os.Parcelable;
import static org.junit.Assert.*;

public class BuildingFactoryTest {
	
	 public BuildingFactory buildingfactory = new BuildingFactory();
	// public Building building = new Building();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMakeBuildings() {
		assertEquals("Hitchcock Hall",buildingfactory.getBuildingNames()[0]);
		assertEquals("Bolz Hall",buildingfactory.getBuildingNames()[1]);
		assertEquals(100,buildingfactory.getCost()[0]);
	}

	@Test
	public void testAssignLevels() {
		fail("Not yet implemented");
	}


	@Test
	public void testPayUser() {
		fail("Not yet implemented");
	}

}
