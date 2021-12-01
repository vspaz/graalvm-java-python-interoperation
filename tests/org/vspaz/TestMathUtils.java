package org.vspaz;

import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class TestMathUtils {
    Object[] nums = {1.0, 2.23, 3.49494, 4.40404, 5.10110, 181.101, 133.11};
    PythonMath pythonMathUtils = new PythonMath(Paths.get(".").toAbsolutePath().normalize() + "/tests/data/math.py");

    @Test
    void testComputeTotalWithPythonAndNumpyOk() {
        Value total = pythonMathUtils.ComputeTotalWithPython("compute_total_with_numpy", nums);
        Assertions.assertEquals(330.44108, total.asDouble());
    }

    @Test
    void testComputeTotalWithPythonAndPandasOk() {
        Value total = pythonMathUtils.ComputeTotalWithPython("compute_total_with_pandas", nums);
        Assertions.assertEquals(330.44108, total.asDouble());
    }

    @Test
    void testComputeTotalWithPythonOk() {
        Value pandasTotal = pythonMathUtils.ComputeTotalWithPython("compute_total_with_pandas", nums);
        Value numpyTotal = pythonMathUtils.ComputeTotalWithPython("compute_total_with_numpy", nums);
        Assertions.assertEquals(pandasTotal.asDouble(), numpyTotal.asDouble());
        Assertions.assertEquals(330.44108, pandasTotal.asDouble());
    }
}
