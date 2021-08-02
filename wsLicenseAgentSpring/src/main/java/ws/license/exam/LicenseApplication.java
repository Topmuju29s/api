
package ws.license.exam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

@SpringBootApplication

public class LicenseApplication extends SpringBootServletInitializer/*implements CommandLineRunner*/{
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(LicenseApplication.class);
	    }

	public static void main(String[] args) {
		SpringApplication.run(LicenseApplication.class, args);
	}
	
//	@Override
//	public void run(String... arg) throws Exception
//	{
//
//		try
//		{
////			int bInsert = insert("09","11.00-11.20","9005316","9009999");
////			if(bInsert > 0)
////				System.out.println("insert success");
////			else
////				System.out.println("insert fail");
//		
////			int bUpdate = update("08","11.22-11.22","9000000","9008888");
////			System.out.println("bUpdate = "+bUpdate);
////			if(bUpdate > 0)
////				System.out.println("update success");
////			else
////				System.out.println("update fail");
//			
////			int bDelete = delete("06","11.22-11.22","9000000","9008888");
////			System.out.println("delete = "+bDelete);
////			if(bDelete > 0)
////				System.out.println("delete success");	
////			else
////				System.out.println("delete fail");
//			
////			ArrayList<String[]> arr = search("A");
////			System.out.println("arr searchAll = "+arr.size());
////			for(int i=0;i<arr.size();i++)
////			{
////				String[] s = (String[])arr.get(i);
////				System.out.println("result searchAll:"+s[0]+"|"+s[1]+"|"+s[2]+"|"+s[3]+"|"+s[4]+"|"+s[5]);
////			}
//			
////			ArrayList<String[]> arr = search("01");
////			System.out.println("arr searchBykey = "+arr.size());
////			for(int i=0;i<arr.size();i++)
////			{
////				String[] s = (String[])arr.get(i);
////				System.out.println("result searchBykey :"+s[0]+"|"+s[1]+"|"+s[2]+"|"+s[3]+"|"+s[4]+"|"+s[5]);
////			}
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			System.out.println("error:"+e.getMessage());
//		}
//	}
//	
//	public ArrayList<String[]> search(String type) throws Exception
//	{
//		if(type.equals("A"))
//			return searchAll();
//		else 
//			return searchBykey(type);
//	}
//	public ArrayList<String[]> searchBykey(String id) throws Exception
//	{
//		System.out.println("-----searchByKey--------"+id);
//		String query = setStatement("searchBykey");
//		System.out.println("query search ="+query);
//
//	    return jdbcTemplate.execute(query,new PreparedStatementCallback<ArrayList<String[]>>()
//	    {  
//		    @Override  
//		    public ArrayList<String[]> doInPreparedStatement(PreparedStatement ps)  throws SQLException, DataAccessException 
//		    {  
//		       ps.setString(1,id);  
//		       ResultSet rs = ps.executeQuery();
//		       
//		       ArrayList<String[]> arr = new ArrayList<String[]>();
//		       while(rs.next())
//		       {
//		    	   
//		    	   arr.add(new String[]{rs.getString("round_id"), rs.getString("time_str"), rs.getString("create_user_code"), 
//		    			   				rs.getString("create_time"), rs.getString("update_user_code"), rs.getString("last_update")});
//		    	   
//		       }
//		       return arr;
//		              
//		    }  
//	    });
//	}
//	public ArrayList<String[]> searchAll() throws Exception
//	{
//		System.out.println("-----searchAll--------");
//		String query = setStatement("searchAll");
//		System.out.println("query search ="+query);
//
//	    return jdbcTemplate.execute(query,new PreparedStatementCallback<ArrayList<String[]>>()
//	    {  
//		    @Override  
//		    public ArrayList<String[]> doInPreparedStatement(PreparedStatement ps)  throws SQLException, DataAccessException 
//		    {  
//		       ArrayList<String[]> arr = new ArrayList<String[]>();
//		       ResultSet rs = ps.executeQuery();  
//		       while(rs.next())
//		       {
//		    	   
//		    	   arr.add(new String[]{rs.getString("round_id"), rs.getString("time_str"), rs.getString("create_user_code"), 
//		    			   				rs.getString("create_time"), rs.getString("update_user_code"), rs.getString("last_update")});
//		    	   
//		       }
//		       return arr;
//		              
//		    }  
//	    });
//	}
//	public Integer insert(String id, String time, String userCreate, String userUpdate) throws Exception
//	{  
//		System.out.println("-----insertExamround--------"+id+"|"+time+"|"+userCreate+"|"+userUpdate);
//		String query = setStatement("insert");
//		System.out.println("query insert ="+query);
//
//	    return jdbcTemplate.execute(query,new PreparedStatementCallback<Integer>()
//	    {  
//		    @Override  
//		    public Integer doInPreparedStatement(PreparedStatement ps)  throws SQLException, DataAccessException 
//		    {  
//		        ps.setString(1,id);  
//		        ps.setString(2,time); 
//		        ps.setString(3,userCreate); 
//		        ps.setString(4,userUpdate); 
//		        
//		        return ps.executeUpdate();  
//		              
//		    }  
//	    });
//
//	}  
//	
//	public Integer update(String id, String time, String userCreate, String userUpdate) throws Exception
//	{  
//		System.out.println("-----updateExamround--------"+id+"|"+time+"|"+userCreate+"|"+userUpdate);
//		String query = setStatement("update");
//		System.out.println("query update ="+query);
//	    return jdbcTemplate.execute(query,new PreparedStatementCallback<Integer>()
//	    {  
//		    @Override  
//		    public Integer doInPreparedStatement(PreparedStatement ps)  throws SQLException, DataAccessException 
//		    {  
//		        ps.setString(1,time);  
//		        ps.setString(2,userUpdate); 
//		        ps.setString(3,id); 
//		        return ps.executeUpdate();  
//		              
//		    }  
//	    });  
//	}  
//
//	public Integer delete(String id, String time, String userCreate, String userUpdate) throws Exception
//	{  
//		System.out.println("-----deleteExamround--------"+id+"|"+time+"|"+userCreate+"|"+userUpdate);
//		String query = setStatement("delete");
//		System.out.println("query delete ="+query);
//	    return jdbcTemplate.execute(query,new PreparedStatementCallback<Integer>()
//	    {  
//		    @Override  
//		    public Integer doInPreparedStatement(PreparedStatement ps)  throws SQLException, DataAccessException 
//		    {  
//		        ps.setString(1,id); 
//		        return ps.executeUpdate();  
//		              
//		    }  
//	    });  
//	}  
//	
//  public String setStatement(String key) throws Exception
//  {
//	  System.out.println("--------setStatementSave---------"+key);
//      String statement;
//
//    switch (key) {
//    case "insert" :
//        statement = "INSERT INTO license_exam.exam_round(round_id, time_str, create_user_code, create_time, update_user_code, last_update) VALUES (?, ?, ?, now(), ?, now())";
//        break;
//    case "update":
//        statement = "UPDATE license_exam.exam_round SET time_str=?, update_user_code=?, last_update=now() WHERE round_id = ?";
//        break;
//    case "delete":
//        statement = "DELETE From license_exam.exam_round WHERE round_id = ?";
//        break;
//    case "searchAll":
//        statement = "SELECT round_id, time_str, create_user_code, create_time, update_user_code, last_update FROM license_exam.exam_round order by round_id";
//        break;
//    case "searchBykey":
//    	statement = "SELECT round_id, time_str, create_user_code, create_time, update_user_code, last_update FROM license_exam.exam_round WHERE round_id = ? order by round_id";
//    	break;
//    default : statement = "";
//}
//      return statement;
//  }

//  private void preparePK(String round_id) throws Exception
//  {
//      preparedStatement.setString(1, round_id);
//  }
}
