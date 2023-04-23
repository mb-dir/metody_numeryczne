package com.company;
import org.apache.commons.math3.analysis.UnivariateFunction;

public class FunctionEvaluator {
    private UnivariateFunction function;

    public FunctionEvaluator(UnivariateFunction function) {
        this.function = function;
    }

    public double evaluateAt(double x) {
        return function.value(x);
    }
}