package com.dianping.btmovie.servlet;

import com.dianping.btmovie.task.VideoLiveTask;
import com.dianping.btmovie.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by oceanzhang on 15/10/23.
 */
@WebServlet(name = "VideoLiveInfoServlet",urlPatterns = "/VideoLiveInfo")
public class VideoLiveInfoServlet extends BaseServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=UTF-8");
        String nodeId = request.getParameter("nodeId");
        PrintWriter out = response.getWriter() ;
        if(StringUtils.isEmpty(nodeId)){
            jsonRerutn(out,-1,"nodeid cannot be null");
            return;
        }
        try {
            String liveData = VideoLiveTask.getLiveInfo(nodeId);
            jsonRerutn(out,0,"return live list data",liveData);
            return;
        }catch (IOException e){
            e.printStackTrace();
        }
        jsonRerutn(out,-1,"cannot get live list data");
    }
}
