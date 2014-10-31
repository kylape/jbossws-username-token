package com.redhat.gss.wss;

import org.jboss.wsf.stack.cxf.security.authentication.SubjectCreatingPolicyInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;

public class MySubjectCreatingPolicyInterceptor extends AbstractPhaseInterceptor {
  SubjectCreatingPolicyInterceptor delegate = new SubjectCreatingPolicyInterceptor();

  public MySubjectCreatingPolicyInterceptor() {
     super(Phase.PRE_PROTOCOL_FRONTEND);
     addBefore("org.jboss.wsf.stack.cxf.interceptor.HandlerAuthInterceptor");
  }

  @Override
  public void handleMessage(Message message) throws Fault {
    delegate.handleMessage(message);
  }

}
