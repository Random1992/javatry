package org.docksidestage.javatry.basic.st6.os;

public enum osType {
    OS_TYPE_MAC("Mac", "/","/Users/"),
    OS_TYPE_WINDOWS("Windows", "\\","/Users/"),
    OS_TYPE_OLD_WINDOWS("OldWindows", "\\","/Documents and Settigs/");

    private String system;
    private String symbol;
    private String directory;

    private osType(String system, String symbol, String directory) {
        this.system = system;
        this.symbol = symbol;
        this.directory = directory;
    }

    public String getSystem() {
        return system;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getDirectory() {
        return directory;
    }
}
