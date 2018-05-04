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
import cz.zr.charcloud.serialization.CSSSerializer;
import cz.zr.charcloud.serialization.HTMLSerializer;
import cz.zr.charcloud.serialization.MetricsSerializer;
import cz.zr.charcloud.utils.Consts;
import cz.zr.charcloud.utils.Consts.GeneratedFileName;

/**
 * Main entry point which executes generating scenario.
 *
 * @author ZRuzicka
 */
public class Scenario {

    private final File inputFile;
    private final HTMLSerializer htmlSerializer;
    private final CSSSerializer styleSerializer;
    private final CharRegister register;
    private final MetricsSerializer metricsSerializer;

    /**
     * @param inputFile
     *            Input template for content analysis.
     * @throws IOException
     *             In case that related output files are not available.
     */
    public Scenario(File inputFile) throws IOException {
        super();
        this.inputFile = inputFile;
        register = new CharRegister();
        // TODO Use factory methods for serializers!
        htmlSerializer = new HTMLSerializer(new FileOutputStream(new File(GeneratedFileName.HTML.getName())));
        styleSerializer = new CSSSerializer(new FileOutputStream(new File(GeneratedFileName.CSS.getName())));
        metricsSerializer = new MetricsSerializer(new FileOutputStream(new File(GeneratedFileName.DATA.getName())));
    }

    /**
     * Executes main CharCloud scenario which run input analysis and content generating.
     *
     * @throws CharCloudException
     */
    public void execute() throws CharCloudException {
        try {
            int contentCharsAmount = generateHTMLContent();
            Collection<CharMetrics> metrics = register.getMetrics();
            calculateMetrics(metrics, contentCharsAmount);
            metricsSerializer.serialize(metrics);
            metricsSerializer.finish();
            styleSerializer.serialize(metrics);
            styleSerializer.finish();
            /*
             * HTML needs to be generated while input file is read, but CSS and metrics could be handled more separately and
             * eventual fail of one serializer does not need to affect other one(s).
             */
        } catch (IOException e) {
            // TODO provide exception logging.
            throw new CharCloudException(e);
        }
    }

    private int generateHTMLContent() throws IOException {
        int input;
        int contentCharsCounter = 0;
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            htmlSerializer.init();
            while ((input = inputStream.read()) != -1) {
                char inputChar = (char) input;
                if (inputChar < 32 || inputChar >= CharRegister.REGISTER_LENGTH) {
                    continue;
                }
                CharMetrics metrics = register.add(inputChar);
                htmlSerializer.add(metrics);
                contentCharsCounter++;
            }
            htmlSerializer.finish();
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
