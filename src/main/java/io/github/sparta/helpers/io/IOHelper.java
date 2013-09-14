/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package io.github.sparta.helpers.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * A collection of IO helpers
 */
public class IOHelper {
    public static final int BUFFER_SIZE = 64 * 1024;

    private static final transient Logger LOG = LoggerFactory.getLogger(IOHelper.class);

    public static String readFully(File file) throws IOException {
        return readFully(new BufferedReader(new FileReader(file)));
    }

    /**
     * Reads the entire reader into memory as a String
     */
    public static String readFully(BufferedReader reader) throws IOException {
        if (reader == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(BUFFER_SIZE);
        char[] buf = new char[BUFFER_SIZE];
        try {
            int len;
            // read until we reach then end which is the -1 marker
            while ((len = reader.read(buf)) != -1) {
                sb.append(buf, 0, len);
            }
        } finally {
            IOHelper.close(reader, "reader", LOG);
        }

        return sb.toString();
    }

    /**
     * Closes the given resource if it is available, logging any closing exceptions to the given log.
     *
     * @param closeable the object to close
     * @param name      the name of the resource
     * @param log       the log to use when reporting closure warnings, will use this class's own {@link org.slf4j.Logger} if <tt>log == null</tt>
     */
    public static void close(Closeable closeable, String name, Logger log) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                if (log == null) {
                    // then fallback to use the own Logger
                    log = LOG;
                }
                if (name != null) {
                    log.warn("Cannot close: " + name + ". Reason: " + e.getMessage(), e);
                } else {
                    log.warn("Cannot close. Reason: " + e.getMessage(), e);
                }
            }
        }
    }


    /**
     * Writes the text to the given file, overwriting the previous file if it existed.
     */
    public static void write(File file, String text) throws IOException {
        write(file, text, false);
    }

    /**
     * Writes the given text to the file; either in append mode or replace mode depending
     * the append flag
     */
    public static void write(File file, String text, boolean append) throws IOException {
        FileWriter writer = new FileWriter(file, append);
        try {
            writer.write(text);
        } finally {
            writer.close();
        }
    }

    public static int copy(final Reader input, final Writer output) throws IOException {
        return copy(input, output, BUFFER_SIZE);
    }

    public static int copy(final Reader input, final Writer output, int bufferSize) throws IOException {
        final char[] buffer = new char[bufferSize];
        int n = input.read(buffer);
        int total = 0;
        while (-1 != n) {
            output.write(buffer, 0, n);
            total += n;
            n = input.read(buffer);
        }
        output.flush();
        return total;
    }

}
