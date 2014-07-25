package com.redhat.gss.wss;

import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.BindingProvider;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.jboss.logging.Logger;

public class SecureClient
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public static void main(String[] args) throws Exception
  {
    SecureClient client = new SecureClient();
    client.invoke();
  }

  public void invoke() throws Exception
  {
    //Create JAX-WS client
    URL wsdl = new URL("http://localhost:8080/jbossws-username-token/SecureService?wsdl");
    QName serviceNS = new QName("http://wss.gss.redhat.com/", "SecureServiceService");
    QName portNS = new QName("http://wss.gss.redhat.com/", "SecureServicePort");
    Service service = Service.create(wsdl, serviceNS);
    WsIntfc port = service.getPort(portNS, WsIntfc.class);

    Map<String, Object> ctx = ((BindingProvider)port).getRequestContext();
    ctx.put("ws-security.username", "klape");
    ctx.put("ws-security.password", "RedHat13#");

    log.info("Invoking sayHello with user klape...");
    try
    {
      port.sayHello("Kyle");
      log.debug("Success: Invocation succeeded");
    }
    catch(Exception e)
    {
      log.error("***FAIL: Invocation did not succed when it should have");
    }

    log.info("Invoking sayGoodbye with user klape...");
    try
    {
      port.sayGoodbye("Kyle");
      log.error("***FAIL: Invocation succeeded when it should not have.");
    }
    catch(Exception e)
    {
      log.debug("Success: Invocation failed due to insufficient access");
    }

    log.info("Invoking sayGoodbye with user snoopy...");
    try
    {
      ctx.put("ws-security.username", "snoopy");
      port.sayGoodbye("Snoopy");
      log.debug("Success: Invocation succeeded");
    }
    catch(Exception e)
    {
      log.error("***FAIL: Invocation failed when it should not have.");
    }

  }
}
