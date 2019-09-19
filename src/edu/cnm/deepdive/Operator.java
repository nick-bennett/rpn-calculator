package edu.cnm.deepdive;

import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Enumerated values representing operators in a postfix (RPN) calculator. Each operator has a token
 * that is used to recognize the operator in an input string, and to represent the operator in an
 * output string.
 */
public enum Operator {

  /** Pops 2 values from stack, pushes sum of the 2 back onto stack. */
  ADD("+") {
    @Override
    public void operate(Deque<Double> operands) {
      operands.push(operands.pop() + operands.pop());
    }
  },
  /** Pops 2 values from stack, pushes difference of the 2 back onto stack. */
  SUBTRACT("-") {
    @Override
    public void operate(Deque<Double> operands) {
      operands.push(- operands.pop() + operands.pop());
    }
  },
  /** Pops 2 values from stack, pushes product of the 2 back onto stack. */
  MULTIPLY("*") {
    @Override
    public void operate(Deque<Double> operands) {
      operands.push(operands.pop() * operands.pop());
    }
  },
  /** Pops 2 values from stack, pushes quotient (real) of the 2 back onto stack. */
  DIVIDE("/") {
    @Override
    public void operate(Deque<Double> operands) {
      double denominator = operands.pop();
      operands.push(operands.pop() / denominator);
    }
  },
  /** Pops 1 value from stack, pushes its square root back onto stack. */
  SQUARE_ROOT("sqrt") {
    @Override
    public boolean needsEscape() {
      return false;
    }
    @Override
    public void operate(Deque<Double> operands) {
      operands.push(Math.sqrt(operands.pop()));
    }
  },
  /** Pops 2 values from stack, pushes the value of 1st raised to the second back onto stack. */
  POWER("^") {
    @Override
    public void operate(Deque<Double> operands) {
      double exponent = operands.pop();
      operands.push(Math.pow(operands.pop(), exponent));
    }
  },
  /** Pops 2 values from stack, pushes remainder after truncated division of the 2 back onto stack. */
  MODULO("%") {
    @Override
    public void operate(Deque<Double> operands) {
      double denominator = operands.pop();
      operands.push(operands.pop() % denominator);
    }
  };

  private String token;

  Operator(String token) {
    this.token = token;
  }

  @Override
  public String toString() {
    return token;
  }

  public boolean needsEscape() {
    return true;
  }

  public static String tokenPattern() {
    return String.format("(?<=^|\\s)(%s)(?=\\s|$)",
        Arrays.stream(values())
            .map((op) -> (op.needsEscape() ? "\\" : "") + op.toString())
            .collect(Collectors.joining("|"))
    );
  }

  public static Operator fromToken(String token) {
    for (Operator op : values()) {
      if (op.token.equals(token)) {
        return op;
      }
    }
    return null;
  }

  abstract public void operate(Deque<Double> operands);

}
