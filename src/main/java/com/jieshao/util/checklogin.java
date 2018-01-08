package com.jieshao.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class checklogin {

	public boolean checklogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
        if(session.getAttribute("user") == null)
        {
        	response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;
      }else {
          return true;
      }
	}
	
}
