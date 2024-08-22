package com.evy.framework.utils;

import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AssertionUtils extends AbstractAssert<AssertionUtils, String> {

    public AssertionUtils(String actual) {
        super(actual, AssertionUtils.class);
    }

    /**
     * Entry point for assertions on a String.
     *
     * @param actual the actual string value
     * @return a new instance of AssertionUtils
     */
    public static AssertionUtils assertThatString(String actual) {
        return new AssertionUtils(actual);
    }

    /**
     * Verifies that the actual string contains the expected substring.
     *
     * @param expected the expected substring
     * @return this assertion for method chaining
     */
    @Step("Verifying that the actual string [{actual}] contains the expected substring [{expected}]")
    public AssertionUtils contains(String expected) {
        try {
            LoggerUtils.info(getClass(), "Asserting that string [" + actual + "] contains: " + expected);
            assertThat(actual).contains(expected);
            LoggerUtils.info(getClass(), "Assertion passed: string [" + actual + "] contains " + expected);
        } catch (AssertionError e) {
            LoggerUtils.error(getClass(), "Assertion failed: string [" + actual + "] does not contain " + expected, null);
            throw e;
        }
        return this;
    }

    /**
     * Verifies that the actual string equals the expected string.
     *
     * @param expected the expected string
     * @return this assertion for method chaining
     */
    @Step("Verifying that the actual string [{actual}] equals the expected string [{expected}]")
    public AssertionUtils equalsTo(String expected) {
        try {
            LoggerUtils.info(getClass(), "Asserting that string [" + actual + "] equals: " + expected);
            assertThat(actual).isEqualTo(expected);
            LoggerUtils.info(getClass(), "Assertion passed: string [" + actual + "] equals " + expected);
        } catch (AssertionError e) {
            LoggerUtils.error(getClass(), "Assertion failed: string [" + actual + "] does not equal " + expected, null);
            throw e;
        }
        return this;
    }

    /**
     * Verifies that the actual string equals the expected string, ignoring case.
     *
     * @param expected the expected string
     * @return this assertion for method chaining
     */
    @Step("Verifying that the actual string [{actual}] equals the expected string [{expected}] ignoring case")
    public AssertionUtils equalsIgnoreCase(String expected) {
        try {
            LoggerUtils.info(getClass(), "Asserting that string [" + actual + "] equals ignoring case: " + expected);
            assertThat(actual).isEqualToIgnoringCase(expected);
            LoggerUtils.info(getClass(), "Assertion passed: string [" + actual + "] equals ignoring case " + expected);
        } catch (AssertionError e) {
            LoggerUtils.error(getClass(), "Assertion failed: string [" + actual + "] does not equal ignoring case " + expected, null);
            throw e;
        }
        return this;
    }

    /**
     * Verifies that the actual string contains the expected substring, ignoring case.
     *
     * @param expected the expected substring
     * @return this assertion for method chaining
     */
    @Step("Verifying that the actual string [{actual}] contains the expected substring [{expected}] ignoring case")
    public AssertionUtils containsIgnoreCase(String expected) {
        try {
            LoggerUtils.info(getClass(), "Asserting that string [" + actual + "] contains ignoring case: " + expected);
            assertThat(actual).containsIgnoringCase(expected);
            LoggerUtils.info(getClass(), "Assertion passed: string [" + actual + "] contains ignoring case " + expected);
        } catch (AssertionError e) {
            LoggerUtils.error(getClass(), "Assertion failed: string [" + actual + "] does not contain ignoring case " + expected, null);
            throw e;
        }
        return this;
    }

    /**
     * Verifies that the provided condition is true.
     *
     * @param condition the condition to be checked
     * @return this assertion for method chaining
     */
    @Step("Verifying that condition is true")
    public AssertionUtils isTrue(boolean condition) {
        try {
            LoggerUtils.info(getClass(), "Asserting that condition is true");
            assertThat(condition).isTrue();
            LoggerUtils.info(getClass(), "Assertion passed: condition is true");
        } catch (AssertionError e) {
            LoggerUtils.error(getClass(), "Assertion failed: condition is not true", null);
            throw e;
        }
        return this;
    }
}
