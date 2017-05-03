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
import java.util.Collection;

import cz.zr.charcloud.exc.InputException;
import cz.zr.charcloud.gen.ContentGenerator;

public class Scenario {

    private final File inputFile;
    private final ContentGenerator contentGenerator;
    private final CharRegister register;

    public Scenario(File inputFile) throws Exception {
        super();
        this.inputFile = inputFile;
        contentGenerator = new ContentGenerator(new FileOutputStream(new File("output.html")));
        register = new CharRegister();
    }

    public void execute() throws IOException, InputException {
        contentGenerator.init();

        int input;
        int totalCharsCounter = 0;
        InputStream inputStream = new FileInputStream(inputFile);
        while ((input = inputStream.read()) != -1) {
            char inputChar = (char) input;
            if (inputChar < 32 || inputChar >= CharRegister.REGISTER_LENGTH) {
                continue;
            }
            contentGenerator.add(inputChar);
            register.add(inputChar);
            totalCharsCounter++;
        }
        contentGenerator.finish();
        inputStream.close();

        Collection<CharMetrics> metrics = register.getMetrics();
        for (CharMetrics charMetrics : metrics) {
            charMetrics.updatePercentage(totalCharsCounter);
        }
    }

}
