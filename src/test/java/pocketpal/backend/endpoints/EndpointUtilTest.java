package pocketpal.backend.endpoints;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pocketpal.frontend.exceptions.InvalidArgumentsException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test utility methods for Endpoint")
public class EndpointUtilTest {
    @Nested
    @DisplayName("Test getPositiveIntegerFromString")
    class TestGetPositiveIntegerFromString {
        @Test
        void getPositiveInteger_validInput_noExceptionThrown() {
            assertDoesNotThrow(() -> {
                int result = EndpointUtil.getPositiveIntegerFromString("1");
                assertEquals(1, result);
            });
        }

        @Test
        void getPositiveInteger_nonInteger_throwsException() {
            assertThrows(NumberFormatException.class,
                    () -> EndpointUtil.getPositiveIntegerFromString("2147483648"));
        }

        @Test
        void getPositiveInteger_zero_throwsException() {
            assertThrows(NumberFormatException.class,
                    () -> EndpointUtil.getPositiveIntegerFromString("0"));
        }

        @Test
        void getPositiveInteger_negativeInteger_throwsException() {
            assertThrows(NumberFormatException.class,
                    () -> EndpointUtil.getPositiveIntegerFromString("-1"));
        }
    }

    @Nested
    @DisplayName("Test getAmountFromString")
    class TestGetAmountFromString {
        @Test
        void getAmount_validInputInteger_noExceptionThrown() {
            assertDoesNotThrow(() -> {
                double result = EndpointUtil.getAmountFromString("1");
                assertEquals(1, result);
            });
        }

        @Test
        void getAmount_validInputDouble_noExceptionThrown() {
            assertDoesNotThrow(() -> {
                double result = EndpointUtil.getAmountFromString("0.01");
                assertEquals(0.01, result);
            });
        }

        @Test
        void getAmount_moreThanNineIntegers_exceptionThrown() {
            assertThrows(InvalidArgumentsException.class, () -> EndpointUtil.getAmountFromString("1000000000.00"));
        }
        @Test
        void getAmount_moreThanTwoDecimalPlaces_exceptionThrown() {
            assertThrows(InvalidArgumentsException.class, () -> EndpointUtil.getAmountFromString("0.001"));
        }
        @Test
        void getAmount_noLeadingZero_accepted() {
            assertDoesNotThrow(() -> {
                double result = EndpointUtil.getAmountFromString(".01");
                assertEquals(0.01, result);
            });
        }
        @Test
        void getAmount_floatingPointWithoutDecimals_accepted() {
            assertDoesNotThrow(() -> {
                double result = EndpointUtil.getAmountFromString("1.");
                assertEquals(1, result);
            });
        }
    }
}
