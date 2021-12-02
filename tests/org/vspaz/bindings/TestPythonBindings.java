package org.vspaz.bindings;

import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class TestPythonBindings {
    Object[] nums = {1.0, 2.23, 3.49494, 4.40404, 5.10110, 181.101, 133.11};
    PythonBindings mathUtils = new PythonBindings(Paths.get(".").toAbsolutePath().normalize() + "/tests/data/math.py");

    @Test
    void testComputeTotalWithPythonAndNumpyOk() {
        Value total = mathUtils.runPythonMethod("compute_total_with_numpy", nums);
            Assertions.assertEquals(330.44108, total.asDouble());
    }

    @Test
    void testComputeTotalWithPythonAndPandasOk() {
        Value total = mathUtils.runPythonMethod("compute_total_with_pandas", nums);
        Assertions.assertEquals(330.44108, total.asDouble());
    }

    @Test
    void testComputeTotalWithPurePythonOk() {
        Value total = mathUtils.runPythonMethod("compute_total_with_pure_python", nums);
        Assertions.assertEquals(330.44108, total.asDouble());
    }

    @Test
    void testComputeTotalWithPythonOk() {
        Value pandasTotal = mathUtils.runPythonMethod("compute_total_with_pandas", nums);
        Value numpyTotal = mathUtils.runPythonMethod("compute_total_with_numpy", nums);
        Assertions.assertEquals(pandasTotal.asDouble(), numpyTotal.asDouble());
        Assertions.assertEquals(330.44108, pandasTotal.asDouble());
    }
}
