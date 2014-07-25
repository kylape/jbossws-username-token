package com.redhat.gss.wss;

import org.apache.cxf.interceptor.InInterceptors;
import org.apache.cxf.annotations.Logging;
import org.jboss.ws.api.annotation.EndpointConfig;
import org.jboss.logging.Logger;

@javax.jws.WebService(wsdlLocation="WEB-INF/wsdl/secureService.wsdl")
@EndpointConfig(configFile = "WEB-INF/jaxws-endpoint-config.xml", configName = "Custom WS-Security Endpoint")
@InInterceptors(interceptors = {
  "org.jboss.wsf.stack.cxf.security.authentication.SubjectCreatingPolicyInterceptor",
  "com.redhat.gss.wss.MyAuthorizationInterceptor"
})
@Logging(pretty=true)
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
