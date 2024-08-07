package com.newgen.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertyReader {
    private static PropertyReader reader = null;
    private Properties prop = null;

    /**
     * Private constructor of Property Reader
     */
    private PropertyReader() {
        readPropertyFile();
    }


    /**
     * This method is used to initialize PropertyReader object
     *
     * @return Reader object
     */
    public static PropertyReader getPropertyInstance() {
        if (reader == null) {
            reader = new PropertyReader();
        }
        return reader;
    }

    /**
     * This method is used to read property file
     */
    private void readPropertyFile() {
        try (FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + "\\resources\\Patent.properties")) {
            this.prop = new Properties();
            this.prop.load(fin);
        } catch (IOException e) {
           LogMessages.errorLog.error(e);
        }

    }

    /**
     * @param key whose value to be fetched from Property File
     * @return value of the key
     */
    public String getPropertyValue(String key) {
        return this.prop.getProperty(key, "");
    }

    /**
     * @return set of all keys specified in Property File
     */
    public Set<Object> getAllKeys() {
        return this.prop.keySet();
    }

    /**
     * @param key key in Property File
     * @return true if key exists else false
     */
    public boolean isKeyExist(String key) {
        return this.prop.keySet().contains(key);
    }
}
