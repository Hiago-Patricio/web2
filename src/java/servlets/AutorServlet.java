/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.AutorDAO;
import dao.AutorDAOImpl;
import entidades.Autor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "AutorServlet", urlPatterns = {"/autor"})
public class AutorServlet extends HttpServlet {

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

        
        Autor autor = new Autor();
        AutorDAO dao = new AutorDAOImpl();
        
        if(request.getParameter("nome")!=null){
            int id = Integer.parseInt(request.getParameter("id"));
            SimpleDateFormat dataFormato = new SimpleDateFormat("yyyy-MM-dd");

            String dataNascimentoStr = request.getParameter("data_nascimento");
            String dataFalecimentoStr = request.getParameter("data_falecimento");
            
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                autor.setDataNascimento(sdf.parse(dataNascimentoStr));
                autor.setDataFalecimento(sdf.parse(dataFalecimentoStr));
            }catch(Exception erro){
            }
            
            autor.setId(id);
            autor.setNome(request.getParameter("nome"));
            autor.setNacionalidade(request.getParameter("nacionalidade"));
            dao.save(autor);
        }else if(request.getParameter("excluir") != null){
            int id = Integer.parseInt(request.getParameter("excluir"));
            dao.delete(dao.find(id));
        }else if(request.getParameter("editar") != null){
            int id = Integer.parseInt(request.getParameter("editar"));
            autor = dao.find(id);
            request.setAttribute("autor", autor);
        }
                
        RequestDispatcher view =  request.getRequestDispatcher("autor.jsp");
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
