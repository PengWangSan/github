package org.springframework.web.servlet.pojos;

import java.io.Serializable;
import java.util.Map;

public class ServiceResponse
  implements Serializable
{
  private static final long serialVersionUID = 1098804052158035225L;
  public static String SERVICE_RESPONSE_RESULT = "SERVICE_RESPONSE_RESULT";
  private Map<Object, Object> model;
  
  public Map<Object, Object> getModel()
  {
    return this.model;
  }
  
  public void setModel(Map<Object, Object> model)
  {
    this.model = model;
  }
}
