/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author jxv911
 */
public class AbstractDataTest {

    /**
     * Test of deleteAttribute method, of class AbstractData.
     */
    @Test
    public void testDeleteAttribute() {
        String key = "key";
        String val = "val";
        AbstractData instance = new AbstractDataImpl();
        instance.addAttribute(key, val);
        assertThat(instance.getAttributeValue(key), is(val));
        instance.deleteAttribute(key);
        assertThat(instance.getAttributeValue(key), is(nullValue()));
    }

    /**
     * Test of containsAttribute method, of class AbstractData.
     */
    @Test
    public void testContainsAttribute() {
        String key = "key";
        String val = "val";
        AbstractData instance = new AbstractDataImpl();
        instance.addAttribute(key, val);
        assertThat(instance.getAttributeValue(key), is(val));
    }

    /**
     * Test of markAsFirstElement method, of class AbstractData.
     */
    @Test
    public void testMarkAsFirstElement() {
        AbstractData instance = new AbstractDataImpl();
        instance.setFirstElement(true);
        assertThat(instance.isFirstElement(), is(true));
    }

    /**
     * Test of unmarkAsFirstElement method, of class AbstractData.
     */
    @Test
    public void testUnmarkAsFirstElement() {
        AbstractData instance = new AbstractDataImpl();
        instance.setLastElement(false);
        assertThat(instance.isFirstElement(), is(false));
    }

    /**
     * Test of markAsLastElement method, of class AbstractData.
     */
    @Test
    public void testMarkAsLastElement() {
        AbstractData instance = new AbstractDataImpl();
        instance.setLastElement(true);
        assertThat(instance.isLastElement(), is(true));
    }

    /**
     * Test of unmarkAsLastElement method, of class AbstractData.
     */
    @Test
    public void testUnmarkAsLastElement() {
        AbstractData instance = new AbstractDataImpl();
        instance.setLastElement(false);
        assertThat(instance.isLastElement(), is(false));
    }

    /**
     * Test of isFirstElement method, of class AbstractData.
     */
    @Test
    public void testIsFirstElement() {
        AbstractData instance = new AbstractDataImpl();
        assertThat(instance.isFirstElement(), is(false));
        instance.setFirstElement(true);
        assertThat(instance.isFirstElement(), is(true));
    }

    /**
     * Test of isLastElement method, of class AbstractData.
     */
    @Test
    public void testIsLastElement() {
        AbstractData instance = new AbstractDataImpl();
        assertThat(instance.isLastElement(), is(false));
        instance.setLastElement(true);
        assertThat(instance.isLastElement(), is(true));
    }

    public class AbstractDataImpl extends AbstractData {

        public Object copy() {
            return new AbstractDataImpl();
        }
    }
}