package org.group4.util; 
import java.io.Serializable;
public class Problem implements Serializable{
	  String id;
	  String time;
	  public Problem(){  
	  }
	  public void setProblem(String id, String time){
		 this.id=id;
	     this.time=time;
	  }
}