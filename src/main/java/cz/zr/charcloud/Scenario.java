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

import cz.zr.charcloud.exc.CharCloudException;
import cz.zr.charcloud.exc.InputException;
import cz.zr.charcloud.gen.CSSGenerator;
import cz.zr.charcloud.gen.ContentGenerator;
import cz.zr.charcloud.gen.MetricsSerializer;
import cz.zr.charcloud.utils.Consts;
import cz.zr.charcloud.utils.Consts.GeneratedFileName;

/**
 * Main entry point which executes generating scenario.
 *
 * @author ZRuzicka
 */
public class Scenario {

    private final File inputFile;
    private final ContentGenerator contentGenerator;
    private final CSSGenerator styleGenerator;
    private final CharRegister register;
    private final MetricsSerializer metricsSerializer;

    /**
     * @param inputFile
     *            Input template for content analysis.
     * @throws CharCloudException
     *             in case of dependencies instantiation fail.
     */
    public Scenario(File inputFile) throws CharCloudException {
        super();
        this.inputFile = inputFile;
        try {
            contentGenerator = new ContentGenerator(new FileOutputStream(new File(GeneratedFileName.HTML.getName())));
            styleGenerator = new CSSGenerator(new FileOutputStream(new File(GeneratedFileName.CSS.getName())));
            metricsSerializer = new MetricsSerializer(new FileOutputStream(new File(GeneratedFileName.DATA.getName())));
        }
        catch (IOException e) {
            throw new CharCloudException(e);
        }
        register = new CharRegister();
    }

    /**
     * Executes main CharCloud scenario which run input analysis and content generating.
     *
     * @throws CharCloudException
     */
    public void execute() throws CharCloudException {
        try {
            int contentCharsAmount = generateContent();
            Collection<CharMetrics> metrics = register.getMetrics();
            calculateMetrics(metrics, contentCharsAmount);
            metricsSerializer.serialize(metrics);
            metricsSerializer.finish();
            styleGenerator.generate(metrics);
            styleGenerator.finish();
        }
        catch (CharCloudException e) {
            throw e;
        }
        catch (InputException e) {
            throw new CharCloudException(e);
        }
    }

    private int generateContent() throws CharCloudException {
        int input;
        int contentCharsCounter = 0;
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            contentGenerator.init();
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
        } catch (Exception e) {
            throw new CharCloudException(e);
        }
        return contentCharsCounter;
    }

    private void calculateMetrics(Collection<CharMetrics> metrics, int totalCharsCounter) {
        for (CharMetrics charMetrics : metrics) {
            charMetrics.calculatePercentage(totalCharsCounter);
            charMetrics.calculateCharSize(Consts.CHAR_MINIMAL_SIZE, Consts.CHAR_ADDITIONAL_SIZE_RANGE);
        }
    }

}
