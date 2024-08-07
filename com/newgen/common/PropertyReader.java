package com.newgen.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertyReader
{
  private static PropertyReader reader = null;
  private Properties prop = null;

     /* Private constructor of Property Reader
     *
     * @throws FileNotFoundException
     * @throws IOException
     */ 
  public PropertyReader()
    throws FileNotFoundException, IOException
  {
    readPropertyFile();
  }
			  
   

    /**
     * This method is used to initialize PropertyReader object
     *
     * @param filePath
     * @return Reader object
     * @throws FileNotFoundException
     * @throws IOException
     */
  public static PropertyReader getPropertyInstance()
    throws FileNotFoundException, IOException
  {
    if (reader == null) {
      reader = new PropertyReader();
    }
    return reader;
  }

	/**
     * This method is used to read property file
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
  private void readPropertyFile() throws FileNotFoundException, IOException {
    FileInputStream fin = null;
    fin = new FileInputStream(System.getProperty("user.dir") + "\\resources\\Patent.properties");
    this.prop = new Properties();
    this.prop.load(fin);  
  }

	/**
     *
     * @param key whose value to be fetched from Property File
     * @return value of the key
     */
  public String getPropertyValue(String key)
  {
    return this.prop.getProperty(key, "");				
  }

	/**
     *
     * @return set of all keys specified in Property File
     */
  public Set<Object> getAllKeys(){		
	Set keys = this.prop.keySet();
    return keys;
  }

	/**
     *
     * @param key key in Property File
     * @return true if key exists else false
     */
  public boolean isKeyExist(String key) {
    Set keys = this.prop.keySet();
    if (keys.contains(key)) {
      return true;
    }								   
    return false;
  }
}
