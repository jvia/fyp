package org.bham.app.diagnoser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class Test {

    public static void main(String[] args) {
        List<ArrayList<Integer>> l = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> r1 = new ArrayList<Integer>();
        ArrayList<Integer> r2 = new ArrayList<Integer>();
        l.add(r1);
        l.add(r2);
        System.out.println(l.get(0).size());

        r1 = new ArrayList<Integer>();
        r1.add(2);
        r1.add(1);
        l.get(0).clear();

    }
}
