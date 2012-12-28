package org.unsure.linkedin.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class LinkedInConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private String consumerKeyValue;

    @NotEmpty
    @JsonProperty
    private String consumerSecretValue;

    @NotEmpty
    @JsonProperty
    private String callbackUrl;

    public String getConsumerKeyValue() {
        return consumerKeyValue;
    }

    public String getConsumerSecretValue() {
        return consumerSecretValue;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }
}
