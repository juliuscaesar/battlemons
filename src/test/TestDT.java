package test;

import DT.DT;

import org.junit.Test;

public class TestDT {

    DT decisionTree = new DT();

    @Test
    public void testTreePrint() {
        System.out.println(decisionTree.printTree());
        DT decisionTree2 = new DT(decisionTree);
        System.out.println(decisionTree2.printTree());
    }

}
