package org.unsure.linkedin.resources;


import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import org.unsure.linkedin.core.LinkedinPerson;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

import static com.google.common.base.Preconditions.checkNotNull;


@Path("/linkedin")
@Produces(MediaType.APPLICATION_JSON)
public class LinkedInResource {
    private final String consumerKeyValue;
    private final String consumerSecretValue;
    private final String callbackUrl;

    private LinkedInOAuthService oauthService;


    public LinkedInResource(String consumerKeyValue, String consumerSecretValue, String callbackUrl) {
        this.consumerKeyValue = consumerKeyValue;
        this.consumerSecretValue = consumerSecretValue;
        this.callbackUrl = callbackUrl;
    }

    @GET
    public Response authenticationRequest(@Context HttpServletRequest request) {
        Response response;

        // To create the OAuth Service, you need the api consumer key and secret.
        oauthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(consumerKeyValue, consumerSecretValue);

        // Specifying a callback
        LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken(callbackUrl);

        // Get the authUrl from the request token
        String authUrl = requestToken.getAuthorizationUrl();

        // Store the request token for use after the callback
        request.getSession(true).setAttribute("requestToken", requestToken);
        try {
            response = Response.seeOther(new URI(authUrl)).build();
        } catch (URISyntaxException e) {
            response = Response.serverError().build();
        }
        return response;

    }

    @GET
    @Path("/verify")
    public LinkedinPerson verifyLinkedInResource( @Context HttpServletRequest request) {
        // Once the control returns to the callback url, you can get the access token like
        String oauthVerifier = request.getParameter("oauth_verifier");

        LinkedInRequestToken requestToken = checkNotNull(
                (LinkedInRequestToken) request.getSession(true).getAttribute("requestToken"), "Resquest Token was not retrieved from session");
        LinkedInAccessToken accessToken = oauthService.getOAuthAccessToken(requestToken, oauthVerifier);

        // Normally we'd persist the token and use it in the future for requests.
        // In this case we'll just use it to get basic data for the current user.
        final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
        final LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);

        Person profile = client.getProfileForCurrentUser();
        return new LinkedinPerson(profile);
    }
}
