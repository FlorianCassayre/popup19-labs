package se.kth.popup;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class KattisTester {

    private final String name;
    private final Consumer<Kattio> function;

    public KattisTester(String name, Consumer<Kattio> function) {
        this.name = name;
        this.function = function;
    }

    @TestFactory
    public Collection<DynamicTest> testsForAllSamples() throws IOException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File directory = new File(classLoader.getResource(name).getFile());
        final File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".in"));

        final List<DynamicTest> tests = new ArrayList<>(files.length);

        for(File inFile : files) {
            final String testName = inFile.getName().substring(0, inFile.getName().lastIndexOf("."));
            final File outFile = new File(directory.getCanonicalPath() + "/" + testName + ".ans");

            final String expected = String.join(System.lineSeparator(), Files.readAllLines(outFile.toPath()));

            tests.add(DynamicTest.dynamicTest(testName, () -> {
                final FileInputStream in = new FileInputStream(inFile);
                final ByteArrayOutputStream out = new ByteArrayOutputStream();

                function.accept(new Kattio(in, out));

                final String actual = out.toString("UTF-8");

                assertEquals(expected, actual.trim());

                in.close();
            }));

        }
        return tests;
    }

}
