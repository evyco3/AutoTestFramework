package com.evy.framework.utils;

import io.qameta.allure.Allure;

import java.util.concurrent.Callable;

/**
 * Utility class for executing functions and handling results and exceptions.
 */
public final class ActionUtils {

    private ActionUtils() {}

    /**
     * Executes a function and handles success and error cases.
     *
     * @param clazz The class from which the function is executed, used for logging.
     * @param callable The function to be executed.
     * @param successMessage The message to log and report if the function succeeds.
     * @param errorMessage The message to log and report if the function fails.
     * @param <T> The return type of the function.
     * @return The result of the function execution.
     * @throws RuntimeException If the function execution fails.
     */
    private static <T> T execFunction(Class<?> clazz, Callable<T> callable, String successMessage, String errorMessage) {
        try {
            T result = callable.call();
            LoggerUtils.info(clazz, successMessage);
            Allure.step(successMessage);
            return result;
        } catch (Exception e) {
            LoggerUtils.error(clazz, errorMessage, e);
            Allure.step(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    /**
     * Executes a function that returns void.
     *
     * @param clazz The class from which the function is executed, used for logging.
     * @param callable The function to be executed.
     * @param successMessage The message to log and report if the function succeeds.
     * @param errorMessage The message to log and report if the function fails.
     */
    public static void execVoidFunction(Class<?> clazz, Callable<Void> callable, String successMessage, String errorMessage) {
        execFunction(clazz, callable, successMessage, errorMessage);
    }

    /**
     * Executes a function that returns a String.
     *
     * @param clazz The class from which the function is executed, used for logging.
     * @param callable The function to be executed.
     * @param successMessage The message to log and report if the function succeeds.
     * @param errorMessage The message to log and report if the function fails.
     * @return The result of the function execution.
     */
    public static String execStringFunction(Class<?> clazz, Callable<String> callable, String successMessage, String errorMessage) {
        return execFunction(clazz, callable, successMessage, errorMessage);
    }

    /**
     * Executes a function that returns a boolean.
     *
     * @param clazz The class from which the function is executed, used for logging.
     * @param callable The function to be executed.
     * @param successMessage The message to log and report if the function succeeds.
     * @param errorMessage The message to log and report if the function fails.
     * @return The result of the function execution.
     */
    public static boolean execBooleanFunction(Class<?> clazz, Callable<Boolean> callable, String successMessage, String errorMessage) {
        return execFunction(clazz, callable, successMessage, errorMessage);
    }
}
