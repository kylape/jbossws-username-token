package com.redhat.gss.wss;

import java.security.Principal;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.security.DefaultSecurityContext;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.security.SecurityContext;
import org.apache.ws.security.WSUsernameTokenPrincipal;

import org.jboss.wsf.spi.security.SecurityDomainContext;
import org.jboss.wsf.spi.deployment.Endpoint;

import javax.management.*;
import java.lang.management.*;
import org.jboss.logging.Logger;

public class SubjectRemovingPolicyInterceptor extends AbstractPhaseInterceptor<Message>
{
  private static Logger log = Logger.getLogger(SubjectRemovingPolicyInterceptor.class);

  public SubjectRemovingPolicyInterceptor()
  {
    super(Phase.POST_INVOKE);
  }

  @Override
  public void handleMessage(Message message) throws Fault
  {
    Endpoint ep = message.getExchange().get(Endpoint.class);
    SecurityDomainContext sdc = ep.getSecurityDomainContext();
    SecurityContext context = message.get(SecurityContext.class);
    Principal p = context.getUserPrincipal();
    if (p != null)
    {
      try
      {
        flushCache(sdc.getSecurityDomain(), p.getName());
      }
      catch (Exception e)
      {
        log.warn("Cache not flushed", e);
      }
    }
  }

  private void flushCache(String securityDomain, String user) throws Exception
  {
    MBeanServerConnection mbeanServerConnection = ManagementFactory.getPlatformMBeanServer();
    ObjectName mbeanName = new ObjectName("jboss.as:subsystem=security,security-domain="+securityDomain);
    Object[] params = {user};
    String[] signature = {String.class.getName()};
    mbeanServerConnection.invoke(mbeanName, "flushCache", params, signature);
  }
}
