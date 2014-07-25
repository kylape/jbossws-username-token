package com.redhat.gss.wss;
 
import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.interceptor.security.SimpleAuthorizingInterceptor;
import org.jboss.logging.Logger;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
 
public class MyAuthorizationInterceptor extends SimpleAuthorizingInterceptor
{
  private static final Logger log = Logger.getLogger(MyAuthorizationInterceptor.class.getName());

  @Override
  public void handleMessage(Message message) throws Fault 
  {
    super.handleMessage(message);
  }

  public MyAuthorizationInterceptor()
  {
    super();
    readRoles();
  }

  private void readRoles()
  {
    Map<String, String> roles = new HashMap<String, String>();
    roles.put("sayHello"  , "hello");
    roles.put("sayGoodbye", "snoopies");
    setMethodRolesMap(roles);
  }
}
