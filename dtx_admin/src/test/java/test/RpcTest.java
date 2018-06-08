package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.pojos.ServiceRequest;
import org.springframework.web.servlet.pojos.ServiceResponse;

import com.ecoo.dtx.admin.DtxAdminApplication;
import com.ecoo.dtx.admin.rpc.AppModel;
import com.ecoo.dtx.admin.rpc.HessianClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DtxAdminApplication.class)
@WebAppConfiguration
public class RpcTest {

	@Autowired
	HessianClient hessianClient;
	
	
	@Test
	public void test() {
		
		
		ServiceRequest sq=new ServiceRequest();
		
		sq.setRequestServiceId("kzB2cOrderAction");
		
		Map<Object, Object> condition = new HashMap<Object, Object>();
		Map<Object, Object> queryFilter = new HashMap<Object, Object>();
		
		condition.put("op", "getKzB2cOrderList");
		condition.put("queryFilter", queryFilter);
		
		sq.setRequestObject(condition);
		
		 ServiceResponse response=hessianClient.invoke(sq, AppModel.order);
		 
		 System.out.println(response);
		
	}
	
	
}
