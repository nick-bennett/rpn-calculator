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
      String pattern = Operator.tokenPattern();
      while (scanner.hasNext()) {
        if (scanner.hasNextDouble()) {
          operands.push(scanner.nextDouble());
        } else if (scanner.hasNext(pattern)) {
          Operator op = Operator.fromToken(scanner.next(pattern));
          op.operate(operands);
        } else {
          throw new IllegalArgumentException(scanner.next());
        }
      }
    } catch (NoSuchElementException ignored) {

    } finally {
      System.out.printf("Stack: %s%n", operands);
    }
  }

}
