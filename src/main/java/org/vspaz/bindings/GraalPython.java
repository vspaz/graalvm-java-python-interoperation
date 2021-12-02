package org.vspaz.bindings;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;

public class GraalPython {
    private final Context ctx;

    public GraalPython(String pythonModulePath) {
        ctx = Context.newBuilder().allowAllAccess(true).build();
        try {
            ctx.eval(Source.newBuilder("python", new File(pythonModulePath)).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Value runPythonMethod(String pythonFunction, Object[] args) {
        return ctx.getBindings("python").getMember(pythonFunction).execute(args);
    }
}
