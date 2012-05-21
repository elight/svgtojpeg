package com.clearfit;

import java.io.*;

public class StringToJPEGTest {
  public static void main(String[] args) {
    String data = null;

    try {
      FileReader fr = new FileReader(args[0]);
      BufferedReader br = new BufferedReader(fr);

      StringBuffer buf = new StringBuffer();
      String line = br.readLine();
      while(line != null) {
        buf.append(line);
        line = br.readLine();
      }
      System.err.println(buf.toString());

      System.out.println("WARMUP");
      test(buf.toString(), Integer.parseInt(args[1]));
      System.out.println("");
      System.out.println("TEST");
      byte[] output = test(buf.toString(), Integer.parseInt(args[1]));

      FileOutputStream fos = new FileOutputStream("./out.jpg");
      fos.write(output);
      fos.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
    System.exit(0);
  }

  private static byte[] test(String data, int iterations) throws Exception {
    long start = System.currentTimeMillis();
    byte[] output = null;
    for(int i = 0 ; i < iterations; i++) {
      output = StringToJPEG.call(data);
    }
    long elapsed = System.currentTimeMillis() - start;
    System.out.println("(" + elapsed + "ms) StringToJPEG x " + iterations);
    double avg = ((double) elapsed) / ((double) iterations);
    System.out.println("Average runtime of " + avg + "ms");
    return output;
  }
}
