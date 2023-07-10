package com.apys.learning.ext.register_offece.view;

import java.io.Serializable;

public class MarriageResponse implements Serializable {
    private boolean existing;

    public boolean isExisting() {
        return existing;
    }

    public void setExisting(boolean existing) {
        this.existing = existing;
    }
}
