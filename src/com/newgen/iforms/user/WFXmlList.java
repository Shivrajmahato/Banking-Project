package com.newgen.iforms.user;

import java.util.regex.Pattern;

public class WFXmlList {
    String xmlString;
    String listTag;
    int startPos;
    int endPos;
    int elementStart;
    int elementEnd;
    private String strUpperCaseXmlString;
    private String strUpperCaseListTag;
    boolean hasSameLen = true;

    public WFXmlList(String outXml, String tag, int startPos, int endPos) {
        this.xmlString = outXml;
        this.listTag = tag;

        this.strUpperCaseListTag = tag.toUpperCase();

        if (this.xmlString.length() != this.xmlString.toUpperCase().length()) {
            this.hasSameLen = false;
        }
        if (!this.hasSameLen) {
            this.strUpperCaseXmlString = Pattern.compile(tag, 2).matcher(this.xmlString).replaceAll(tag.toUpperCase());
        } else
            this.strUpperCaseXmlString = this.xmlString.toUpperCase();
        this.startPos = startPos;
        this.endPos = endPos;
        this.elementStart = this.startPos;
        this.elementEnd = this.strUpperCaseXmlString.indexOf("</" + this.strUpperCaseListTag + ">", this.elementStart);
        if (this.elementEnd == -1)
            return;
        this.elementEnd += this.listTag.length() + 3;
    }

    public void reInitialize(boolean isAscending) {
        if (isAscending) {
            this.elementStart = this.startPos;
            this.elementEnd = this.strUpperCaseXmlString.indexOf("</" + this.strUpperCaseListTag + ">", this.elementStart);

            if (this.elementEnd == -1)
                return;
            this.elementEnd += this.strUpperCaseListTag.length() + 3;
        } else {
            reInitializeDesc();
        }
    }

    boolean reInitializeDesc() {
        this.elementEnd = this.strUpperCaseXmlString.lastIndexOf("</" + this.strUpperCaseListTag + ">", this.endPos);

        if (this.elementEnd == -1) {
            return false;
        }
        this.elementEnd += this.strUpperCaseListTag.length() + 3;
        this.elementStart = this.strUpperCaseXmlString.lastIndexOf("<" + this.strUpperCaseListTag + ">", this.elementEnd);

        return true;
    }


    public boolean hasMoreElements(boolean isAscending) {
        if (this.elementEnd == -1) {
            return false;
        }
        if (isAscending) {
            int index = this.strUpperCaseXmlString.indexOf("<" + this.strUpperCaseListTag + ">", this.elementStart);
            return (index != -1) && (index < this.endPos);
        }

        return hasMoreElementsDesc();
    }

    boolean hasMoreElementsDesc() {
        int index = this.strUpperCaseXmlString.lastIndexOf("<" + this.strUpperCaseListTag + ">", this.elementStart);

        return (index != -1) && (index >= this.startPos);
    }

    public void skip(boolean isAscending) {
        if (isAscending) {
            this.elementStart = this.elementEnd;
            this.elementEnd = this.strUpperCaseXmlString.indexOf("</" + this.strUpperCaseListTag + ">", this.elementStart);

            if (this.elementEnd == -1)
                return;
            this.elementEnd += this.strUpperCaseListTag.length() + 3;
        } else {
            skipDesc();
        }
    }

    void skipDesc() {
        this.elementEnd = this.elementStart;
        this.elementStart = this.strUpperCaseXmlString.lastIndexOf("<" + this.strUpperCaseListTag + ">", this.elementEnd - 1);

        if (this.elementStart != -1)
            return;
        this.elementEnd = -1;
    }

    public String getVal(String tag) {
        if ((this.elementStart == -1) || (this.elementEnd == -1)) {
            return "";
        }

        int startIndex = this.strUpperCaseXmlString.indexOf("<" + tag.toUpperCase() + ">", this.elementStart);
        if ((startIndex == -1) || (startIndex >= this.elementEnd)) {
            return "";
        }
        return this.xmlString.substring(startIndex + tag.length() + 2, this.strUpperCaseXmlString.indexOf("</" + tag.toUpperCase() + ">", this.elementStart));
    }

    public String toString() {
        if (this.xmlString == null) {
            return "";
        }
        return this.xmlString;
    }

}