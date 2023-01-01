package com.danielway;

import com.sun.jna.*;
import java.nio.file.*;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        Agnostic agnostic = (Agnostic) Native.loadLibrary("./agnostic.so", Agnostic.class);
        String sampleAgnosticSource = Files.readString(Path.of("./sample.agn"));

        Agnostic.GoString.ByValue str = new Agnostic.GoString.ByValue();
        str.p = sampleAgnosticSource;
        str.n = str.p.length();
        
        System.out.println("Agnostic AST output: " + agnostic.ParseAgnosticAST(str));
    }

    public interface Agnostic extends Library {
        public class GoString extends Structure {
            public String p;
            public long n;
        }

        public GoString.ByValue ParseAgnosticAST(GoString.ByValue rawText);
    }
}
