package org.springframework.web.servlet.action;

import org.springframework.web.servlet.exception.ServiceRuntimeException;
import org.springframework.web.servlet.pojos.ServiceRequest;
import org.springframework.web.servlet.pojos.ServiceResponse;

public abstract interface Action
{
  public abstract ServiceResponse perform(ServiceRequest paramServiceRequest)
    throws ServiceRuntimeException;
}
