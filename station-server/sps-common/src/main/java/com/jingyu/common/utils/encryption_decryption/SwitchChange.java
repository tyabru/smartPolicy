package com.jingyu.common.utils.encryption_decryption;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "server")
public class SwitchChange {
    private Boolean switchChange;

    public Boolean getSwitchChange() {
        return switchChange;
    }

    public void setSwitchChange(Boolean switchChange) {
        this.switchChange = switchChange;
    }
}
