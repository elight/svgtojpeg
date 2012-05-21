package com.clearfit;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

public class SVGtoJPGServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws Exception {

    byte[] jpg_bytes = StringToJPEG.call(req.getParameter("svg"));

    if(jpg_bytes.length > 0) {
      res.setContentType("image/jpeg");
      res.setStatus(HttpServletResponse.SC_OK);
      ServletOutputStream out = res.getOutputStream();
      out.write(jpg_bytes);
      out.flush();
      out.close();
    }
  }

  public static void main(String[] args) throws Exception {
    Server server = new Server(Integer.valueOf(System.getenv("PORT")));
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    context.addServlet(new ServletHolder(new SVGtoJPGServlet()),"/*");
    server.start();
    server.join();
  }
}
