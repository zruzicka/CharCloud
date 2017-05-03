package cz.zr.charcloud;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import cz.zr.charcloud.gen.CSSGenerator;
import cz.zr.charcloud.gen.ContentGenerator;
import cz.zr.charcloud.gen.MetricsSerializer;
import cz.zr.charcloud.utils.Consts;

public class Scenario {

    private final File inputFile;
    private final ContentGenerator contentGenerator;
    private final CSSGenerator styleGenerator;
    private final CharRegister register;
    private final MetricsSerializer metricsSerializer;

    public Scenario(File inputFile) throws Exception {
        super();
        this.inputFile = inputFile;
        contentGenerator = new ContentGenerator(new FileOutputStream(new File("output.html")));
        styleGenerator = new CSSGenerator(new FileOutputStream(new File("fontStyle.css")));
        metricsSerializer = new MetricsSerializer(new FileOutputStream(new File("metrics.data")));
        register = new CharRegister();
    }

    public void execute() throws IOException, InputException {
        int contentCharsAmount = generateContent();
        Collection<CharMetrics> metrics = register.getMetrics();
        calculateMetrics(metrics, contentCharsAmount);
        metricsSerializer.serialize(metrics);
        styleGenerator.generate(metrics);
    }

    private int generateContent() throws InputException, FileNotFoundException, IOException {
        int input;
        int contentCharsCounter = 0;
        contentGenerator.init();
        InputStream inputStream = new FileInputStream(inputFile);
        while ((input = inputStream.read()) != -1) {
            char inputChar = (char) input;
            if (inputChar < 32 || inputChar >= CharRegister.REGISTER_LENGTH) {
                continue;
            }
            CharMetrics metrics = register.add(inputChar);
            contentGenerator.add(metrics);
            contentCharsCounter++;
        }
        contentGenerator.finish();
        inputStream.close();
        return contentCharsCounter;
    }

    private void calculateMetrics(Collection<CharMetrics> metrics, int totalCharsCounter) {
        for (CharMetrics charMetrics : metrics) {
            charMetrics.calculatePercentage(totalCharsCounter);
            charMetrics.calculateCharSize(Consts.CHAR_MINIMAL_SIZE, Consts.CHAR_ADDITIONAL_SIZE_RANGE);
        }
    }

}
