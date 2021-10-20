package com.aie.thrillio.constants;

public enum KidFriendlyStatus {
    
    APPROVED("approved"),
    REJECTED("rejected"),
    UNKNOWN("unknown");
    
    private KidFriendlyStatus(String status){
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }
}
