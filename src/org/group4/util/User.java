/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.group4.util;
public class User {
	public String username;
	public int rank;
	public int submitted;
	public int solved;
	public int submissions;
	public int accepted;
	public void setBaseInfo(String username,int rank,int submitted,int solved,int submissions,int accepted){
		this.username=username;
		this.rank=rank;
		this.submitted=submitted;
		this.solved=solved;
		this.submissions=submissions;
		this.accepted=accepted;	
	}
}

