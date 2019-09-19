package edu.cnm.deepdive;

import java.util.Deque;

/**
 * Enumerated values representing operators in a postfix (RPN) calculator. Each operator has a token
 * that is used to recognize the operator in an input string, and to represent the operator in an
 * output string.
 */
public enum Operator {

  /** Pops 2 values from stack, pushes sum of the 2 back onto stack. */
  ADD("+"),
  /** Pops 2 values from stack, pushes difference of the 2 back onto stack. */
  SUBTRACT("-"),
  /** Pops 2 values from stack, pushes product of the 2 back onto stack. */
  MULTIPLY("*"),
  /** Pops 2 values from stack, pushes quotient (real) of the 2 back onto stack. */
  DIVIDE("/"),
  /** Pops 1 value from stack, pushes its square root back onto stack. */
  SQUARE_ROOT("sqrt"),
  /** Pops 2 values from stack, pushes the value of 1st raised to the second back onto stack. */
  POWER("^"),
  /** Pops 2 values from stack, pushes remainder after truncated division of the 2 back onto stack. */
  MODULO("%");

  private String token;

  Operator(String token) {
    this.token = token;
  }

  @Override
  public String toString() {
    return token;
  }

  public static String tokenPattern() {
    return "(?<=^|\\s)(\\+|\\-|\\*|\\/|\\^|\\%|sqrt)(?=\\s|$)";
  }

  public static void operate(String token, Deque<Double> operands) {
    Operator op = null;
    for (Operator compare : values()) {
      if (compare.token.equals(token)) {
        op = compare;
        break;
      }
    }
    double operand = operands.pop();
    double result;
    switch (op) {
      case ADD:
        result = operand + operands.pop();
        break;
      case SUBTRACT:
        result = operands.pop() - operand;
        break;
      case MULTIPLY:
        result = operand * operands.pop();
        break;
      case DIVIDE:
        result = operands.pop() / operand;
        break;
      case POWER:
        result = Math.pow(operands.pop(), operand);
        break;
      case MODULO:
        result = operands.pop() % operand;
        break;
      case SQUARE_ROOT:
        result = Math.sqrt(operand);
        break;
      default:
        result = 0;
    }
    operands.push(result);
  }

}
