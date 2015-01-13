package org.neo4j.good_practices;

import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import org.neo4j.server.rest.security.SecurityRule;

public class SecurityRuleB implements SecurityRule
{
    @Override
    public boolean isAuthorized( HttpServletRequest httpServletRequest )
    {
        httpServletRequest.setAttribute( "X-Id", UUID.randomUUID() );
        return true;
    }

    @Override
    public String forUriPath()
    {
        return "/*";
    }

    @Override
    public String wwwAuthenticateHeader()
    {
        return null;
    }
}
