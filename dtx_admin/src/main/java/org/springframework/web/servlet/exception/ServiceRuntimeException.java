package org.springframework.web.servlet.exception;

public class ServiceRuntimeException
  extends RuntimeException
{
  private static final long serialVersionUID = 8152397859325980928L;
  
  public ServiceRuntimeException() {}
  
  public ServiceRuntimeException(String message)
  {
    super(message);
  }
}
