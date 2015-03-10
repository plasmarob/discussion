/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.discussion.robertdiscussion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "LoginChecker", urlPatterns = {"/LoginChecker"})
public class LoginChecker extends HttpServlet {

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
        String username = request.getParameter("username");
        String passwd = request.getParameter("pass");
        /*
        File file = new File("users.txt");
        // if file doesnt exists, then create it
        
        if (!file.exists()) {
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("user1");
            bw.write("pass1");
            bw.close();
        }
*/
        //System.out.println(System.getProperty("user.dir"));
        System.err.println("Hello!");
        BufferedReader br = null;
        
        List<String> users = new ArrayList<String>();
        List<String> passs = new ArrayList<String>();
        
        //final String resourcesPath = “resources/images/bumpMap.png”;
        //InputStream stream = MySketch.class.getResourceAsStream(resourcesPath);
        System.err.println(getClass().getClassLoader().getResource("/resources/users.txt"));//.getClassLoader().getResource("/resources/users.txt"));
        //ServletContext.getResourceAsStream("WEB-INF/resources/yourfilename.cnf");
        InputStream stream = (getClass().getClassLoader().getResourceAsStream("/resources/users.txt"));
        try {
            String line;
            
            br = new BufferedReader(new InputStreamReader(stream));
            while ((line = br.readLine()) != null) {
                    System.err.println(line);
                    users.add(line);
                    line = br.readLine();
                    System.err.println(line);                    
                    passs.add(line);
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
          
        boolean found = false;
        String name = "";
        for (String s : users)
        {
            if (username.equals(s))
            {
                if (passwd.equals(passs.get(users.indexOf(s))))
                {
                    found = true;
                    name = s;
                    break;
                }
            }
        }
                
        if (found)
        {
            request.getSession().setAttribute("username", name);           
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        }
        else
        {
            request.getRequestDispatcher("Fail.jsp").forward(request, response);
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
