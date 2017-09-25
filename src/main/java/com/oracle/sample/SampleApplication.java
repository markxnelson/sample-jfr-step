// Copyright 2017 Oracle and/or its affliates.  All rights reserved.

// This is a sample application that is used in a demonstration of how
// to use the Java Flight Recorder Wercker Step.  This program will
// randomly create and remove objects from the heap every period,
// where the period is random in length, up to ten seconds.  The
// program will run for five minutes and then stop.

package com.oracle.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleApplication {

  private static List objects = new ArrayList();
  private static Random random = new Random();

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();

    // create some objects to get started
    for (int i = 0; i < 10; i++) createObjects();

    // keep running for five minutes
    while (System.currentTimeMillis() - startTime < (5 * 60 * 1000)) {
      switch (random.nextInt(3)) {
        case 0:
        case 1:
          // add some objects
          createObjects();
          break;
        case 2:
          // remove some objects
          removeObjects();
          break;
        case 3:
        default:
          // do nothing
          System.out.print("z");
      }
      try {
        // now sleep for a random time up to ten seconds
        Thread.sleep(random.nextInt(10 * 1000));
      } catch (InterruptedException e) {
        // ignore
      }
    }
    System.out.println("\nBye!");
  }

  private static void createObjects() {
    System.out.print("+");
    for (int i = 0; i < 2; i++) {
       objects.add(new byte[10*1024*1024]);
     }
  }

  private static void removeObjects() {
    System.out.print("-");
    int start = objects.size() - 1;
    int end = start - 2;
    for (int i = start; ((i >= 0) && (i > end)); i--) {
      objects.remove(i);
    }
  }

}
