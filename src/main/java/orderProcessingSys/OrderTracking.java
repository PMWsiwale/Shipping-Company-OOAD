package orderProcessingSys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderTracking {
	@Autowired
	 private JdbcTemplate jdbcTemplate ;
	
	public List getMoreOrderStatus()
	{
		String url="SELECT * FROM status ";
		
		return this.jdbcTemplate.queryForList(url);
	}
	
	public Map<String,Object> getOrderStatus(int orderId)
	{
		String url="SELECT * FROM status where orderId=? orderBY date desc";
		
		 Map<String, Object> orderStatus = jdbcTemplate .queryForMap(url,orderId);
		
		return orderStatus;
	}

}
