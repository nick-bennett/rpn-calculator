package edu.cnm.deepdive;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Calculator {

  private Scanner scanner;
  private Deque<Double> operands;

  public static void main(String[] args) {
    Calculator calc = new Calculator(new Scanner(System.in));
    calc.process();
  }

  public Calculator(Scanner scanner) {
    this.scanner = scanner;
    operands = new LinkedList<>();
  }

  public void process() {
    try {
      while (scanner.hasNextDouble()) {
        operands.push(scanner.nextDouble());
      }
    } catch (NoSuchElementException ignored) {

    } finally {
      System.out.println(operands);
    }
  }

}
