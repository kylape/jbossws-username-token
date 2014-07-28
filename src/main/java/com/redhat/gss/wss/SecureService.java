package com.redhat.gss.wss;

import javax.ejb.Stateless;
import javax.jws.WebService;

import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.annotations.Logging;
import org.jboss.ws.api.annotation.EndpointConfig;
import org.jboss.logging.Logger;
import org.jboss.ejb3.annotation.SecurityDomain;

@WebService(wsdlLocation="META-INF/wsdl/secureService.wsdl")
@EndpointConfig(configFile = "META-INF/jaxws-endpoint-config.xml", configName = "Custom WS-Security Endpoint")
@InInterceptors(interceptors = {
  "org.jboss.wsf.stack.cxf.security.authentication.SubjectCreatingPolicyInterceptor",
  "com.redhat.gss.wss.MyAuthorizationInterceptor"
})
@Logging(pretty=true)
@Stateless
@SecurityDomain("other")
public class SecureService
{
  private static Logger log = Logger.getLogger(SecureService.class);

  public String sayHello(String name)
  {
    log.info("Hello, " + name);
    return "Hello, " + name;
  }

  public String sayGoodbye(String name)
  {
    log.info("Goodbye, " + name);
    return "Goodbye, " + name;
  }
}
