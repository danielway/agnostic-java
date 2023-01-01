package com.danieldway;

import com.sun.jna.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class AgnosticJNA {
    public static void main(String[] args) throws IOException {
        Agnostic agnostic = (Agnostic) Native.loadLibrary("agnostic", Agnostic.class);
        String sampleAgnosticSource = Files.readString(Path.of("sample.agn"));

        Agnostic.GoString str = new Agnostic.GoString();
        str.p = sampleAgnosticSource;
        str.n = str.p.length();
        
        System.out.println("Agnostic AST output: " + agnostic.ParseAgnosticAST(str));
    }

    public interface Agnostic extends Library {
        public class GoString extends Structure {
            public String p;
            public long n;

            @Override
            protected List<String> getFieldOrder() {
                return Arrays.asList(new String[] {"p","n"});
            }
        }

        public GoString ParseAgnosticAST(GoString rawText);
    }
}
