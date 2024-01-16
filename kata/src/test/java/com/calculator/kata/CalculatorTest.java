package com.calculator.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public final void whenEmptyStringIsUsedThenReturnValueIs0() {
        assertEquals(0, Calculator.add(""));
    }

    @Test
    public final void whenOneNumberIsUsedThenReturnValueIsThatSameNumber() {
        assertEquals(1, Calculator.add("1"));
    }

    @Test
    public final void whenTwoNumbersAreUsedThenReturnValueIsTheirSum() {
        assertEquals(3, Calculator.add("1,2"));
    }

    @Test
    public final void whenAnyNumberOfNumbersIsUsedThenReturnValuesAreTheirSums() {
        assertEquals(3 + 6 + 15 + 18 + 46 + 33, Calculator.add("3,6,15,18,46,33"));
    }

    @Test
    public final void whenNewLineIsUsedBetweenNumbersThenReturnValuesAreTheirSums() {
        assertEquals(3 + 6 + 15, Calculator.add("3,6\n15"));
    }

    @Test
    public final void whenDelimiterIsSpecifiedThenItIsUsedToSeparateNumbers() {
        assertEquals(3 + 6 + 15, Calculator.add("//;\n3;6;15"));
    }

    @Test
    public final void whenNegativeNumberIsUsedThenRuntimeExceptionIsThrown() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Calculator.add("2,-4,3,-5");
        });

        String expectedMessage = "Negatives not allowed: [-4, -5]";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public final void whenOneOrMoreNumbersAreGreaterThan1000IsUsedThenItIsNotIncludedInSum() {
        assertEquals(3 + 1000 + 6, Calculator.add("3,1000,1001,6,1234"));
    }
}
