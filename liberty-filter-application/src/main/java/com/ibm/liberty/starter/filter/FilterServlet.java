/*******************************************************************************
 * Copyright (c) 2016 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.ibm.liberty.starter.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/"})
public class FilterServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(FilterServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if ("/".equals(req.getRequestURI())) {
            log.info("Forwarding request");
            resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            resp.setHeader("Location", "/start/");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            PrintWriter writer = resp.getWriter();
            StringBuffer urlBuffer = req.getRequestURL();
            String appLocation = urlBuffer.toString();
            String appUrl = appLocation.substring(0, appLocation.indexOf(req.getRequestURI())) + "/start/";
            writer.println("<html><body>Resource not found, the Liberty app accelerator can be found <a href=\"" + appUrl  + "\">here</a>.</body></head></html>");
        }
    }


}
