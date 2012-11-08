package com.vaadin.demo.sampler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SourceReader {
    public static String getSourceForClass(Class<?> c) throws IOException {
        StringBuffer src = new StringBuffer();
        /*
         * Use package name + class name so the class loader won't have to guess
         * the package name.
         */
        String resourceName = "/" + c.getName().replace('.', '/') + ".java";
        InputStream is = c.getResourceAsStream(resourceName);
        if (is == null) {
            throw new FileNotFoundException(resourceName);
        }

        BufferedReader bis = new BufferedReader(new InputStreamReader(is));
        for (String line = bis.readLine(); null != line; line = bis.readLine()) {
            src.append(line);
            src.append("\n");
        }
        return src.toString();

    }

}
