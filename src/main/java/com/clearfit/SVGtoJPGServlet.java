package com.clearfit;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

public class SVGtoJPGServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {

    //System.err.println("SVG follows:");
    //System.err.println(req.getParameter("svg"));

    try {
      String svg = req.getParameter("svg").replaceAll("\\n", "");
      System.err.println("SVG follows:");
      System.err.println(svg);

      byte[] jpg_bytes = StringToJPEG.call(svg);

      if(jpg_bytes.length > 0) {
        res.setContentType("image/jpeg");
        res.setStatus(HttpServletResponse.SC_OK);
        ServletOutputStream out = res.getOutputStream();
        out.write(jpg_bytes);
        out.flush();
        out.close();
      }
    } catch(Exception e) {
      e.printStackTrace();
      res.setStatus(500);
    }
  }

  public static void main(String[] args) throws Exception{
    Server server = new Server(Integer.valueOf(System.getenv("PORT")));
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    context.addServlet(new ServletHolder(new SVGtoJPGServlet()),"/*");
    server.start();
    server.join();
  }
}
