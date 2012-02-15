/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class FolderFilterTest {

    /**
     * Test of accept method, of class FolderFilter.
     */
    @Test
    public void testAccept() {
        FolderFilter instance = new FolderFilter();
        File f = null;
        assertThat(instance.accept(f), is(false));
        f = new File("/");
         assertThat(instance.accept(f), is(true));
    }
}