package com.yu;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.logging.Logger;

/**
 * 用于测试过程中的日志对象
 *
 * @author YU
 * @date 2022-06-17 18:39
 */
public class TestMethodNameLogger implements TestRule {
    private static final Logger LOGGER = Logger.getLogger("TestMethodNameLogger");

    @Override
    public Statement apply(Statement base, Description description) {
        logInfo("Before test ", description);
        try {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    base.evaluate();
                }
            };
        } finally {
            logInfo("After test", description);
        }
    }

    private void logInfo(String msg, Description description) {
        LOGGER.info(msg + description.getMethodName());
    }
}
