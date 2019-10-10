package org.docksidestage.javatry.basic.st6.dbms;

public abstract class St6Aql {
    public St6Aql(){};
    protected abstract String buildPagingQuery(int pageSize, int pageNumber);
}
