/**
 * 
 */
package com.mastercard.mcwallet;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mastercard.mcwallet.sdk.*;
import com.mastercard.mcwallet.sampleapp.*;
import com.mastercard.mcwallet.sampleapp.helpers.SampleApplicationHelperTest;

/**
 * @author Brady Georgen - brady.georgen@daugherty.com
 *
 */
@RunWith(Suite.class)
@SuiteClasses({SampleApplicationControllerTest.class,
	SampleApplicationHelperTest.class,
	MasterPassServiceTest.class,
	MasterPassServiceTest.class
})

public class AllTests {

}
