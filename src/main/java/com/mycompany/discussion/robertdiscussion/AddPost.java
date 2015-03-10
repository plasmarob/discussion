/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.discussion.robertdiscussion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Robert
 */
@WebServlet(name = "AddPost", urlPatterns = {"/AddPost"})
public class AddPost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String post = request.getParameter("postform");
        String username = (String)request.getSession().getAttribute("username");
        
        
        //System.out.println(System.getProperty("user.dir"));
        //System.err.println("Hello!");
        BufferedReader br = null;
        List<String> postStrs = new ArrayList<String>();
        System.err.println(getClass().getClassLoader().getResource("/resources/posts.txt"));
        InputStream stream = (getClass().getClassLoader().getResourceAsStream("/resources/posts.txt"));
        try {
            String line;
            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                    postStrs.add(line);
            }
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
                try {
                        if (br != null) br.close();
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
        }
        
        
        
        URL resourceUrl = getClass().getResource("/resources/posts.txt");
        try {
            File file = new File(resourceUrl.toURI());
            OutputStream output = new FileOutputStream(file);
            PrintStream printStream = new PrintStream(output);
            
            
            printStream.println(post);
            printStream.println("- " + username);
            printStream.println((new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())));
            printStream.println("");
            
            for (String s : postStrs)
            {
                printStream.println(s);
            }
            
            printStream.close();
            //output.write(str.getBytes(Charset.forName("UTF-8")));
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                try {
                        if (br != null) br.close();
                } catch (Exception ex) {
                        ex.printStackTrace();
                }
        }
        
        /*
        BufferedWriter bw;
        getClass().getClassLoader().getResource(post)
        bw = new BufferedWriter((getClass().getClassLoader().getResourceAsStream("/resources/posts.txt")));  
        bw.write("Hello World!");  
        bw.write("\n");  
        bw.write("Hello World 2 !\n");  
        bw.write("Hello World 3 !" + "\n");  
        bw.close(); 
        
        InputStream ostream = (getClass().getClassLoader().getResourceAsStream("/resources/posts.txt"));
        try {
            String line;
            
            bw = new BufferedWriter(new OuputStreamWriter(ostream));
            while ((line = br.readLine()) != null) {
                    postStrs.add(line);
            }
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
                try {
                        if (br != null) br.close();
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
        }
        */
        
        
        System.err.println(post);
        for (String s : postStrs)
        {
            System.err.println(s);
        }
        
        
        String postdata = post.replace("\n", "<br/>");
        
        
        postdata = postdata.concat("<br/>- ");
        postdata = postdata.concat(username);
        postdata = postdata.concat("<br/>");
        postdata = postdata.concat((new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())));
        postdata = postdata.concat("<br/><br/>");
        
        for (String s : postStrs)
        {
            postdata = postdata.concat(s);
            postdata = postdata.concat("<br/>");
        }
        
        if (true)
        {
            request.getSession().setAttribute("postdata", postdata);           
            request.getRequestDispatcher("PostPage.jsp").forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
