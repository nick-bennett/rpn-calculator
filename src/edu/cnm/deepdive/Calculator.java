package edu.cnm.deepdive;

import java.io.InputStream;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Calculator {

  public static void main(String[] args) {
    Calculator calc = new Calculator();
    calc.process(System.in);
  }

  public void process(InputStream in) {
    Deque<Double> operands = new LinkedList<>();
    try (Scanner scanner = new Scanner(in)) {
      while (scanner.hasNextDouble()) {
        operands.push(scanner.nextDouble());
      }
    } catch (NoSuchElementException ignored) {
      // End of input; complete processing.
    } finally {
      System.out.println(operands);
    }
  }

}
