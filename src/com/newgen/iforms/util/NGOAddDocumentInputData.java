package com.newgen.iforms.util;

public class NGOAddDocumentInputData {
    private String cabinetName;
    private String sessionID;
    private  String parentFolderIndex;
    private int noOfPages;
    private String sDocumentFullName;
    private String docType;
    private String docSize;
    private String createdByAppName;
    private String isIndex;

    public String getCabinetName() {
        return cabinetName;
    }

    public void setCabinetName(String cabinetName) {
        this.cabinetName = cabinetName;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getParentFolderIndex() {
        return parentFolderIndex;
    }

    public void setParentFolderIndex(String parentFolderIndex) {
        this.parentFolderIndex = parentFolderIndex;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getsDocumentFullName() {
        return sDocumentFullName;
    }

    public void setsDocumentFullName(String sDocumentFullName) {
        this.sDocumentFullName = sDocumentFullName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocSize() {
        return docSize;
    }

    public void setDocSize(String docSize) {
        this.docSize = docSize;
    }

    public String getCreatedByAppName() {
        return createdByAppName;
    }

    public void setCreatedByAppName(String createdByAppName) {
        this.createdByAppName = createdByAppName;
    }

    public String getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(String isIndex) {
        this.isIndex = isIndex;
    }

}
