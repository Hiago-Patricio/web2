/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ClienteDAO;
import dao.ClienteDAOImpl;
import entidades.Cliente;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hiago
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/clientejstl"})
public class ClienteJSTLServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
            
        Cliente c = new Cliente();
        ClienteDAO dao = new ClienteDAOImpl();
        
        request.setCharacterEncoding("UTF-8");
        
        if(request.getParameter("endereco") != null
            && request.getParameter("sexo") != null
            && request.getParameter("nome") != null
            && request.getParameter("dataNascimento") != null
            && request.getParameter("vip") != null){

            if(!request.getParameter("id").equals("")){
                int id = Integer.parseInt(request.getParameter("id"));
                c.setId(id);
            }
            c.setEndereco(request.getParameter("endereco"));
            c.setSexo(request.getParameter("sexo"));
            c.setNome(request.getParameter("nome"));
            c.setVip(Boolean.parseBoolean(request.getParameter("vip")));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            c.setDataNascimento(sdf.parse(
                request.getParameter("dataNascimento")));
            dao.save(c);
        }else if(request.getParameter("excluir") != null){
            int id = Integer.parseInt(request.getParameter("excluir"));
            c = dao.find(id);
            dao.delete(c);
        }else if(request.getParameter("editar") != null){
            int id = Integer.parseInt(request.getParameter("editar"));
            c = dao.find(id);
            request.setAttribute("cliente", c);
        }
        
        request.setAttribute("lista", dao.all());
        
        RequestDispatcher view = request.getRequestDispatcher("clientejstl.jsp");
        view.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteJSTLServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteJSTLServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
