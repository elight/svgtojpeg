package com.clearfit;

import java.io.*;
import java.util.zip.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SVGtoJPGServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {

    //System.err.println("SVG follows:");
    //System.err.println(req.getParameter("svg"));

    try {
      String svg = req.getParameter("svg").replaceAll("\\n", "");
      System.err.println("SVG follows: \n" + svg);

      /*
      InputStream is = new ByteArrayInputStream(svg.getBytes());
      GZIPInputStream gzis = new GZIPInputStream(is);
      BufferedReader br = new BufferedReader(new InputStreamReader(gzis));

      String line;
      StringBuffer sb = new StringBuffer();
      while((line = br.readLine()) != null) {
        sb.append(line);
      }

      svg = sb.toString();
      */

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
}
