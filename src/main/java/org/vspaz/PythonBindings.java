package org.vspaz;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;

public class PythonBindings {
    final String pythonModulePath;
    static Context ctx;

    public PythonBindings(String pythonModulePath) {
        this.pythonModulePath = pythonModulePath;
        ctx = Context.newBuilder().allowAllAccess(true).build();
        File pythonModule = new File(pythonModulePath);
        try {
            ctx.eval(Source.newBuilder("python", pythonModule).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Value ComputeTotalWithPython(String pythonFunction, Object[] args) {
        return ctx.getBindings("python").getMember(pythonFunction).execute(args);
    }
}
