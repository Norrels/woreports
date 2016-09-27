package com.woreports.api;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public enum Format {
    PDF("application/pdf"), XLSX("application/vnd.ms-excel");

    private final String mimeType;

    private Format(String mimeType) {
        this.mimeType = mimeType;
    }

    public String mimeType() {
        return mimeType;
    }
}
