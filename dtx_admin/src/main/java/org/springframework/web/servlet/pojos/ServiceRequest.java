package org.springframework.web.servlet.pojos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ServiceRequest
  implements Serializable
{
  private static final long serialVersionUID = -4367273658947888781L;
  private String requestServiceId;
  private Object requestObject;
  private Map<String, Object> parameters;
  
  public ServiceRequest()
  {
    this.parameters = new HashMap();
  }
  
  public Map<String, Object> getParameters()
  {
    return this.parameters;
  }
  
  public Object getParameter(String key)
  {
    return this.parameters.get(key);
  }
  
  public Object getRequestObject()
  {
    return this.requestObject;
  }
  
  public void setRequestObject(Object requestObject)
  {
    this.requestObject = requestObject;
  }
  
  public String getRequestServiceId()
  {
    return this.requestServiceId;
  }
  
  public void setRequestServiceId(String requestServiceId)
  {
    this.requestServiceId = requestServiceId;
  }
  
  public void setParameter(String key, Object value)
  {
    this.parameters.put(key, value);
  }
  
  public Object getPrameter(String key)
  {
    return this.parameters.get(key);
  }
}
