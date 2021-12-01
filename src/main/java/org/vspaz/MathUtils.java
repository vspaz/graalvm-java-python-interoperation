package org.vspaz;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;

public class MathUtils {
    String pythonModulePath;

    public MathUtils(String pythonModulePath) {
        this.pythonModulePath = pythonModulePath;
    }

    public double ComputeTotalWithPythonAndNumpy(Object[] nums) throws IOException {
        Context ctx = Context.newBuilder().allowAllAccess(true).build();
        File pythonModule = new File(pythonModulePath);
        ctx.eval(Source.newBuilder("python", pythonModule).build());
        Value computeTotal = ctx.getBindings("python").getMember("compute_total");
        Value computedResult = computeTotal.execute(nums);
        return computedResult.asDouble();
    }
}
