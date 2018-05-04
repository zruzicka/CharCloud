/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.gen;

import java.io.OutputStream;

import cz.zr.charcloud.CharMetrics;
import cz.zr.charcloud.exc.InputException;

/**
 * Generates HTML output.
 *
 * @author ZRuzicka
 */
public class ContentGenerator extends AbstractGenerator {

    private int counter;

    public ContentGenerator(OutputStream output) {
        super(output);
    }

    public void init() throws InputException {
        writeHeader();
    }

    public void add(CharMetrics metrics) throws InputException {
        StringBuilder sb = new StringBuilder();
        sb.append("<span class='" + metrics.getKey() + "'>");
        sb.append(metrics.getCharValue());
        sb.append("</span>");
        write(sb.toString());
        counter++;
    }

    @Override
	public void finish() throws InputException {
        writeFooter();
        super.finish();
    }

    private void writeHeader() throws InputException {
        String header = "<!DOCTYPE html><html><head>"
                + "<title>CharCloud example</title>"
                + "<meta charset='UTF-8'>"
                + "<link rel='stylesheet' type='text/css' href='fontStyle.css' />"
                + "<style>"
                + "body {color:#000000;margin:10px;font-size:150%;background-color: #aaaaaa;}"
                + "</style>"
                + "</head><body>"
                + "<div>"
                + "<script>var t1= new Date().getTime();console.log('started: ' + t1);"
                + "</script>";
        write(header);
    }

    private void writeFooter() throws InputException {
        String footer = "<script>var t2= new Date().getTime();console.log('finished: ' + t2);console.log('delta t: ' + (t2-t1));console.log('content counter: ' + ("
                + counter + "));</script>" + "</div></body></html>";
        write(footer);
    }

}
