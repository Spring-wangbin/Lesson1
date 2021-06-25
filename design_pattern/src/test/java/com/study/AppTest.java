package com.study;

import static org.junit.Assert.assertTrue;

import com.study.singleton.lazy.ExecutorRun;
import com.study.singleton.lazy.LazySingleton;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
//        LazySingleton a = LazySingleton.getInstance();
//        LazySingleton b = LazySingleton.getInstance();
//        System.out.println(a == b);

        Thread thread1 = new Thread(new ExecutorRun());
        Thread thread2 = new Thread(new ExecutorRun());
        thread1.start();
        thread2.start();
    }
}
