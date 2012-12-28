package org.unsure.linkedin;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.eclipse.jetty.server.session.SessionHandler;
import org.unsure.linkedin.configuration.LinkedInConfiguration;
import org.unsure.linkedin.resources.LinkedInResource;

public class LinkedInService extends Service<LinkedInConfiguration> {

    public static void main( String[] args) throws Exception {
        new LinkedInService().run(args);
    }

    @Override
    public void initialize(Bootstrap<LinkedInConfiguration> bootstrap) {
        bootstrap.setName("dropwizard-linkedin");

    }

    @Override
    public void run(LinkedInConfiguration configuration, Environment environment) throws Exception {
        final String consumerKeyValue = configuration.getConsumerKeyValue();
        final String consumerSecretValue = configuration.getConsumerSecretValue();
        final String callbackUrl = configuration.getCallbackUrl();

        // We need to store the requestToken in the current session. Adding this handler is required to do that.
        environment.setSessionHandler(new SessionHandler());

        environment.addResource(new LinkedInResource(consumerKeyValue, consumerSecretValue, callbackUrl));
    }
}
