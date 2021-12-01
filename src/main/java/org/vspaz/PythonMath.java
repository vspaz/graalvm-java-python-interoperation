package org.vspaz;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PythonMath {
    String pythonModulePath;
    static Context ctx;

    Map<String, Value> pythonFunctionToBindings = new HashMap<>();

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

    public Value ComputeTotalWithPython(String pythonFunction, Object[] nums) {
        if (!pythonFunctionToBindings.containsKey(pythonFunction)) {
            pythonFunctionToBindings.put(pythonFunction, ctx.getBindings("python").getMember(pythonFunction));
            System.out.println(pythonFunction);
        }
        return pythonFunctionToBindings.get(pythonFunction).execute(nums);
    }
}
