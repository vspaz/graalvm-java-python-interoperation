package org.vspaz;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;

public class MathUtils {
    public static double ComputeTotalWithPythonAndNumpy() {
        Context ctx = Context.newBuilder().allowAllAccess(true).build();
        File pythonModule = new File("/home/vspaz/graal_tests/pure_python.py");
        try {
            ctx.eval(Source.newBuilder("python", pythonModule).build());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        Value computeTotal = ctx.getBindings("python").getMember("compute_total");
        Object[] nums = {1.0, 2.23, 3.49494, 4.40404, 5.10110, 181.101, 133.11};
        Value computedResult = computeTotal.execute(nums);
        return computedResult.asDouble();
    }

    public static void main(String[] args) {
        System.out.println(ComputeTotalWithPythonAndNumpy()); // -> 330.44108
    }
}
