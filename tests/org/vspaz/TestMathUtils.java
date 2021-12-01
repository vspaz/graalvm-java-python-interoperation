package org.vspaz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class TestMathUtils {
    @Test
    void testComputeTotalWithPythonAndNumpyOk() {
        String sourceCodeRoot = Paths.get(".").toAbsolutePath().normalize().toString();
        MathUtils pythonMathUtils = new MathUtils(sourceCodeRoot + "/tests/data/math.py");
        Object[] nums = {1.0, 2.23, 3.49494, 4.40404, 5.10110, 181.101, 133.11};
        try {
            double total = pythonMathUtils.ComputeTotalWithPythonAndNumpy(nums);
            Assertions.assertEquals(330.44108, total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
