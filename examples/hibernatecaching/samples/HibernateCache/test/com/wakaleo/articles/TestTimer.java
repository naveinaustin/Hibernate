////////////////////////////////////////////////////////////////////////////////
// TestTimer: $Source$
// TODO Class summary goes here
//
// Created : 31 oct. 2005 by jfsmart
// Last modified $Date$ by $Author$
// Revision: $Revision$
// Version : $ID$
// Copyright (c) 2005
////////////////////////////////////////////////////////////////////////////////

package com.wakaleo.articles;

import junit.framework.TestCase;

/**
 * Simple utilities class for benchmarking unit tests. Use is a follows:
 *     TestTimer t = new TestTimer("My task");
 * 	   // Do something
 *     t.done();
 *
 * @author jfsmart
 */
public class TestTimer extends TestCase {
    
    private long startTime;
    private String message;
    
    /**
     * Initiate a timer
     */
    public TestTimer(String message) {
        startTime = System.currentTimeMillis();
        this.message = message;
    }
    
    /**
     * Reset the timer for another timing session.
     *
     */
    public void reset() {
        startTime = System.currentTimeMillis();
    }
    
    /**
     * End the timing session and output the results.
     */
    public void done() {
        
        System.out.println(message
                + " : "
                + (System.currentTimeMillis() - startTime)
                + " ms.");
    }
}