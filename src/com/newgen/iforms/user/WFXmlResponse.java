package com.newgen.iforms.user;

public class WFXmlResponse {

    private String xmlString;
    private String strUpperCaseXmlString;

    public WFXmlResponse(String responseXml) {
        this.xmlString = responseXml;
        if (this.xmlString == null)
            return;
        this.strUpperCaseXmlString = this.xmlString;
    }

    public WFXmlList createList(String listStartTag, String elementTag) {
        int startPos = this.strUpperCaseXmlString.indexOf("<" + listStartTag + ">");
        if (startPos == -1) {
            return new WFXmlList("", elementTag, 0, 0);
        }
        startPos += 2 + listStartTag.length();
        int endPos = this.strUpperCaseXmlString.indexOf("</" + listStartTag + ">", startPos);
        return new WFXmlList(this.xmlString, elementTag, startPos, endPos);
    }

    public String toString() {
        return this.xmlString;
    }
}