/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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

        // null should fail
        File f = null;
        assertThat("Null files should not pass.",
                   instance.accept(f), is(false));

        // directory should pass
        f = new File("/");
        assertThat("A directory should pass the filter.",
                   instance.accept(f), is(true));

        // file should fail
        f = new File("/etc/motd");
        assertThat("Files should not be accepted in the directory filter.",
                   instance.accept(f), is(false));
    }

    @Test
    public void testGetDescription() {
        assertThat(new FolderFilter().getDescription(), is(notNullValue()));
    }
}