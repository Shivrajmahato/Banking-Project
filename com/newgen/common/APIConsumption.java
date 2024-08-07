package com.newgen.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Properties;

public class APIConsumption {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmm");
    //private static final String finalPayLoad="";
    public String consumeAPI(String request, String process) throws IOException {
        Properties properties = new Properties();
        InputStream input = null;
        StringBuilder response = new StringBuilder();
        String output = "";
//        String finalPayLoad="";
        try {
            try {
                input = new FileInputStream(System.getProperty("user.dir") + "/NabilConfig/Integration.properties");
                properties.load(input);
            } catch (FileNotFoundException e1) {
                LogMessages.errorLog.info("Exception inside Apiconsumption=" + e1);
                e1.printStackTrace();
            } catch (IOException e) {
                LogMessages.errorLog.info("Exception inside Apiconsumption=" + e);
                e.printStackTrace();
            }
            //      String xapikey = properties.getProperty(process).trim();
            String nabilurl = "https://apims.nabilbank.com/api/v1/connect";
            String login = "Basic YnBtOlN5JHRlbUAyMDIy";
            //String nabilurl = properties.getProperty("nabilURL").trim();
            //String login = properties.getProperty("login");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String finaltimestamp = sdf.format(timestamp);

            String data = Base64.getEncoder().encodeToString(request.getBytes());
            String signmodel = "{\"Model\":" + request + ",\"TimeStamp\":\"" + finaltimestamp + "\"}";
            
            //System.out.println(signmodel);
//            try {
                String signature="";
				try {
					signature = sign(signmodel);
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SignatureException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                LogMessages.statusLog.info("SIgnature::" + signature);
                String finalPayload = "{\r\n" +
                    " \"FunctionName\": \""+process+"\",\r\n" +
                    " \"Data\": \"" + data + "\",\r\n" +
                    " \"Signature\": \"" + signature + "\",\r\n" +
                    " \"TimeStamp\": \"" + finaltimestamp + "\",\r\n" +
                    "}";
//            } catch (InvalidKeyException e) {
//                LogMessages.errorLog.info("Exception inside Apiconsumption=" + e);
//                e.printStackTrace();
//            } catch (NoSuchAlgorithmException e) {
//                LogMessages.errorLog.info("Exception inside Apiconsumption=" + e);
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                LogMessages.errorLog.info("Exception inside Apiconsumption=" + e);
//                e.printStackTrace();
//            } catch (SignatureException e) {
//                LogMessages.errorLog.info("Exception inside Apiconsumption=" + e);
//                e.printStackTrace();
//            }

            LogMessages.errorLog.info(nabilurl + "###" + login + "##\n\r" + finalPayload);
            URL url = new URL(nabilurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            try {
                conn.setRequestProperty("Authorization", login);
            } catch (Exception e) {
                LogMessages.errorLog.info("Exception inside Apiconsumption fromm=" + e);
            }
            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(finalPayload);
            out.flush();
            out.close();
            LogMessages.statusLog.info("Inside Apiconsumption=" + conn.getResponseCode());

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                while ((output = br.readLine()) != null) {
                    response.append(output + "\n");
                }
                output = "SUCCESS##" + response;
                br.close();
            }else {
                LogMessages.statusLog.info("inside !200");
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
                LogMessages.statusLog.info("BR" + br);
                while ((output = br.readLine()) != null) {
                    LogMessages.statusLog.info("inside while" + output + "#######" + response);
                    response.append(output + "\n");
                    LogMessages.statusLog.info("inside while" + output + "#######" + response);
                }
                output = conn.getResponseCode()+"##" + response;
                br.close();
            }
            LogMessages.errorLog.info("Response=" + response);
        } catch (IOException e) {
            LogMessages.errorLog.info("Exception inside Apiconsumption=" + e);
            e.printStackTrace();
        }
        return output.toString();
    }

    public static RSAPrivateKey readPrivateKey() throws Exception {
        String key2 = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCpMYLbzaZVj36g\n" +
            "jybRUB5l0CzbiTQHjjHFu44eHIg/KNt1J+s6GWHEWpQ7A4uOVnVEXyytWbUgRJwI\n" +
            "AGRVUNpUs6S6f0b8JgNuoC4tgiM+Kluojhox0rO1zFIXgTNfamVb1C06al8cgqv/\n" +
            "D08lTDxru0pS53eDj/dG/AkYLUZQAbWF1hD+AVSRH9Lh8gY8Xwh431Galgm79bos\n" +
            "nJ5GFY6Vn5uHY2kY+vOpsZZRrgMtSUO9riZzo9PwLWZgK1xCSM7PhcL2HwKkqM5b\n" +
            "6/6b4a0UfcIO795gbLIHr7euSz+TQt/PZFR5jj8jHJi8SmOs7NMQf11OiaZb+XX3\n" +
            "rqFspJFtAgMBAAECggEAfTsEXKTuxM2dMEJEvRDMLxfS2/vF4wHAnVahbnA3+GfJ\n" +
            "aAHP/gTjdG6G4NdS6FveGGlJrFWF1whxZQrFONDLiX4eI1wpsmCAkdVF2LFGXLEF\n" +
            "ftEPWmlWUu2UpYkf+TPLKhj2ERLCq1uc6wDt1/GBl0vdS1fAAkUBBi1undKFBJnK\n" +
            "MrcWAep4Mk5lfleri+k0lfqC7nEXvntV7rgXzoc22i0WPS+MauU/egDWglWVmEvr\n" +
            "TKnQyTJ2+1mfAZAv3nWUzZK9se+/J4JV0xfCvfFOGUKiYJrzwjL7Yorz1Ti+b/7h\n" +
            "hr+qfjfpUZIAXSQ3iidrpbAQzRLaqERNwfaHZRChEQKBgQDAXB7v2mg2zgrG4NIf\n" +
            "dFM0vgyJCarJSE+SX5wntQTu2pgLgqL82/bwvcO0c4VzbjbVwFl0fzMLBwTYPK6K\n" +
            "3lDpesB2Ff/QhXFTPceJg+pJ2MOaDE5/xRmX5D/15Dm4O+ckM60HWrMd+QPqowoK\n" +
            "3dkejkoO616q/UimMFawmId9mwKBgQDhK1Ab9jr52ZfCtPQxqRhXtpss1Us6Hvtl\n" +
            "KA8vPlcfsQt8oK5i7+Rwhzu2POueF1h+39sCiWAAYMj/0eGBM3igxUSTa9c/Vzam\n" +
            "je6jdo9F9h+30Kf5nF2bQn9+EVqHUPR/T0q0jJgAoFDHsDn31qkyMPUpP79NN7os\n" +
            "e0MI5WGhlwKBgQCfDg0OqvutBWXn2JImLBt46wSWc4XidYZGNAR82LNBhUJfkh+P\n" +
            "EZr4qRCfrz3TmniA+lF9gIgGQEGDQ3nXncgtprBpc/tYwl5YRCcv0AH0GeZzBGYM\n" +
            "RD/LVpKEkbggXHfdeVVDVX3x9KuGYlW1codLOsCF4Z0fqdtef23PC/BMrQKBgQCl\n" +
            "7gwnvukxC5dZZY36Npj4sllhzYCVQRWTMtUVxIPhl89WNPatdAjMn+tx3SzooF2X\n" +
            "iZFUKjXzsV8NnfXnBy4ik5x4YuUJCVT9oeQ00367WKeBBqNyPzd/3Z4kIderlg3G\n" +
            "74Cm0mCFPZ6puQTOzYJ7uTZBAngQCDwK4X7BxH+Q2wKBgD8Loil8w/5+F0po/Htm\n" +
            "+tLdFisVyPkgnNpyImEFmMio3DXvjgk5d+CdkeuDhGldA1Ah6ORJwSlEYsDPSIw7\n" +
            "hB9XSiFJa/CZ46qkdd1hfHMAY1LX+uYBrlAqLfmlsIh1eNq5EFz1EC5IZ/nHtl7W\n" +
            "fs/f3gTDWO3UwWqK9wj4oHED\n" +
            "-----END PRIVATE KEY-----";

        String privateKeyPEM = key2
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replaceAll("\n", "")
            .replace("-----END PRIVATE KEY-----", "");
        
        byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
    public static String sign(String signString)
    throws NoSuchAlgorithmException, UnsupportedEncodingException,
    InvalidKeyException, SignatureException {
        PrivateKey pkey = null;
        try {
            pkey = readPrivateKey();
        } catch (Exception e) {
            LogMessages.errorLog.info("Exception inside Apiconsumption=" + e);
            e.printStackTrace();
        }
        String signedString = null;
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(pkey);
        signature.update(signString.getBytes());
        byte[] signatureBytes = signature.sign();
        byte[] encryptedByteValue = Base64.getEncoder().encode(signatureBytes);
        signedString = new String(encryptedByteValue, "UTF-8");
        return signedString;
    }

}