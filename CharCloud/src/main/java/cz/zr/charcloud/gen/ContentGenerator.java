/*
 * Released under the MIT/X11 License.
 *
 * Copyright (c) 2017 Zbynek Ruzicka
 *
 */

package cz.zr.charcloud.gen;

import java.io.IOException;
import java.io.OutputStream;

import cz.zr.charcloud.CharMetrics;
import cz.zr.charcloud.exc.InputException;
import cz.zr.charcloud.utils.Consts;

public class ContentGenerator implements Generator {

    private final OutputStream output;

    private StringBuilder sb;
    
    private int counter;

    public ContentGenerator(OutputStream output) {
        super();
        this.output = output;
    }

    public void init() throws InputException {
        header();
    }

    public void add(CharMetrics metrics) throws InputException {
        sb = new StringBuilder();
        sb.append("<span class='"+metrics.getKey()+"'>");
        sb.append(metrics.getCharValue());
        sb.append("</span>");
        write(sb.toString());
        counter++;
    }

    private void write(String string) throws InputException {
        try {
            output.write(string.getBytes(Consts.ENCODING));
        } catch (IOException e) {
            throw new InputException(e);
        }
    }

    public void finish() throws IOException {
        footer();
        output.flush();
        output.close();
    }

    private void header() throws InputException {
        String header = "<!DOCTYPE html><html><head><meta charset='UTF-8'><title>title</title><style>"
                + "body {color:#000000;margin:10px;font-size:150%;background-color: #aaaaaa;}"
                + "</style></head><body><div>"
                + "<script>var t1= new Date().getTime();console.log('started: ' + t1);</script>";
        write(header);
    }

    private void footer() throws InputException {
        String footer = "<script>var t2= new Date().getTime();console.log('finished: ' + t2);console.log('delta t: ' + (t2-t1));console.log('content counter: ' + ("+counter+"));</script>"
                + "</div></body></html>";
        write(footer);
    }

}
