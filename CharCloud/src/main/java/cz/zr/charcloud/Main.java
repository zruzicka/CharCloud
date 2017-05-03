package cz.zr.charcloud;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */
import java.io.InputStream;

import cz.zr.charcloud.exc.InputException;
import cz.zr.charcloud.gen.ContentGenerator;

public class Main {

    private final File inputFile;
    private final ContentGenerator contentGenerator;

    public Main(File inputFile) throws Exception {
        super();
        this.inputFile = inputFile;
        contentGenerator = new ContentGenerator(new FileOutputStream(new File("output.html")));
    }

    public void execute() throws IOException, InputException {
        contentGenerator.init();

        int input;
        InputStream inputStream = new FileInputStream(inputFile);
        while ((input = inputStream.read()) != -1) {
            char inputChar = (char) input;
            contentGenerator.add(inputChar);
        }
        contentGenerator.finish();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main(new File("input_example.txt"));
        main.execute();
    }

}
