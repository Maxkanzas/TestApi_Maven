package models;

import java.util.List;

public class SingleResourceResponse {
    private SingleResourceData data;
    private Support support;

    public SingleResourceData getData() {
        return data;
    }

    public void setData(SingleResourceData data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}
