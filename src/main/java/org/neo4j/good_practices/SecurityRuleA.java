package org.neo4j.good_practices;

import javax.servlet.http.HttpServletRequest;

import org.neo4j.server.rest.security.SecurityRule;

public class SecurityRuleA implements SecurityRule
{
    @Override
    public boolean isAuthorized( HttpServletRequest httpServletRequest )
    {
        httpServletRequest.setAttribute( "X-Neo4j", "2.0.3" );
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
