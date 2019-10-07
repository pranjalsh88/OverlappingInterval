package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IntervalsTest {
    private Intervals intervals;

    @Before
    public void setup() {
        intervals = new Intervals();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        intervals.findOverlappingIntervals(null);
    }

    @Test
    public void testEmptyArray() {
        List<String> output = intervals.findOverlappingIntervals(new ArrayList<>());
        Assert.assertEquals(0, output.size());
    }

    @Test
    public void testNoConflicts() {
        int[] a1 = {21,25};
        int[] a2 = {6,7};
        int[] a3 = {10,15};
        int[] a4 = {29,30};
        int[] a5 = {40,46};
        int[] a6 = {101,110};

        List<int[]> inputList = new ArrayList<>();
        inputList.add(a1);
        inputList.add(a2);
        inputList.add(a3);
        inputList.add(a4);
        inputList.add(a5);
        inputList.add(a6);

        List<String> output = intervals.findOverlappingIntervals(inputList);
        List<String> expected = new ArrayList<>();

        Assert.assertEquals(output, expected);
    }

    @Test
    public void testDoubleConflicts() {

        int[] a1 = {1,5};
        int[] a2 = {3,7};
        int[] a3 = {2,6};
        int[] a4 = {10,15};
        int[] a5 = {5,6};
        int[] a7 = {101, 110};
        List<int[]> inputList = new ArrayList<>();
        inputList.add(a1);
        inputList.add(a2);
        inputList.add(a3);
        inputList.add(a4);
        inputList.add(a5);
        inputList.add(a7);

        List<String> output = intervals.findOverlappingIntervals(inputList);
        List<String> expected = new ArrayList<>();
        expected.add("[{1,5} conflicts with {3,7}]");
        expected.add("[{1,5} conflicts with {2,6}]");
        expected.add("[{3,7} conflicts with {5,6}]");

        Assert.assertEquals(expected, output);
    }

    @Test
    public void testTripleConflicts() {

        int[] a1 = {1,20};
        int[] a2 = {3,7};
        int[] a3 = {8,10};
        int[] a4 = {11,16};
        int[] a5 = {29,46};
        int[] a6 = {32,39};
        int[] a7 = {40,43};
        List<int[]> inputList = new ArrayList<>();
        inputList.add(a1);
        inputList.add(a2);
        inputList.add(a3);
        inputList.add(a4);
        inputList.add(a5);
        inputList.add(a6);
        inputList.add(a7);

        List<String> output = intervals.findOverlappingIntervals(inputList);
        List<String> expected = new ArrayList<>();

        expected.add("[{1,20} conflicts with {3,7}]");
        expected.add("[{1,20} conflicts with {8,10}]");
        expected.add("[{1,20} conflicts with {11,16}]");
        expected.add("[{29,46} conflicts with {32,39}]");
        expected.add("[{29,46} conflicts with {40,43}]");
        StringBuilder sb = new StringBuilder();
        for (String x : output) {
            sb.append(x).append(",");
        }
        Assert.assertEquals(sb.toString(), expected, output);
    }

}

