package org.vspaz;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;

public class PythonMath {
    String pythonModulePath;
    Context ctx;

    public PythonMath(String pythonModulePath) {
        this.pythonModulePath = pythonModulePath;
        ctx = Context.newBuilder().allowAllAccess(true).build();
        File pythonModule = new File(pythonModulePath);
        try {
            ctx.eval(Source.newBuilder("python", pythonModule).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Value ComputeTotalWithPythonAndNumpy(String pythonFunction, Object[] nums) {
        Value computeTotal = ctx.getBindings("python").getMember(pythonFunction);
        return computeTotal.execute(nums);
    }
}
