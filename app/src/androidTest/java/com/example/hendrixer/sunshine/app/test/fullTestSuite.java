package com.example.hendrixer.sunshine.app.test;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Hendrixer on 7/27/14.
 */
public class fullTestSuite extends TestSuite {
    public static Test suite() {
        return  new TestSuiteBuilder(fullTestSuite.class)
                .includeAllPackagesUnderHere().build();
    }

    public fullTestSuite() {
        super();
    }
}
