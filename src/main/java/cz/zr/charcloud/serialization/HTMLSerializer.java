/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.serialization;

import java.io.IOException;
import java.io.OutputStream;

import cz.zr.charcloud.CharMetrics;

/**
 * Generates HTML output.
 *
 * @author ZRuzicka
 */
public class HTMLSerializer extends AbstractSerializer {

    private int counter;

    public HTMLSerializer(OutputStream output) {
        super(output);
    }

    public void init() throws IOException {
        writeHeader();
    }

    public void add(CharMetrics metrics) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<span class='" + metrics.getKey() + "'>");
        sb.append(metrics.getCharValue());
        sb.append("</span>");
        write(sb.toString());
        counter++;
    }

    @Override
	public void finish() throws IOException {
        writeFooter();
        super.finish();
    }

    private void writeHeader() throws IOException {
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

    private void writeFooter() throws IOException {
        String footer = "<script>var t2= new Date().getTime();console.log('finished: ' + t2);console.log('delta t: ' + (t2-t1));console.log('content counter: ' + ("
                + counter + "));</script>" + "</div></body></html>";
        write(footer);
    }

}
