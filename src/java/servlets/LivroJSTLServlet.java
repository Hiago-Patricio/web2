/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.AutorDAO;
import dao.AutorDAOImpl;
import dao.LivroDAO;
import dao.LivroDAOImpl;
import dao.MidiaDAO;
import dao.MidiaDAOImpl;
import entidades.Autor;
import entidades.Livro;
import entidades.Midia;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.MoneyUtils;

/**
 *
 * @author hiago
 */
@WebServlet(name = "LivroJSTLServlet", urlPatterns = {"/livrojstl"})
public class LivroJSTLServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Livro l = new Livro();
        LivroDAO daoLivro = new LivroDAOImpl();
        Autor a = new Autor();
        AutorDAO daoAutor = new AutorDAOImpl();
        Midia m = new Midia();
        MidiaDAO daoMidia = new MidiaDAOImpl();
        
        if (request.getParameter("titulo") != null
                && request.getParameter("sinopse") != null
                && request.getParameter("editora") != null
                && request.getParameter("edicao") != null
                && request.getParameter("preco") != null
                && request.getParameter("quantidade") != null
                && request.getParameter("autorId") != null) {
            if (!request.getParameter("id").equals("")) {
                l.setId(Integer.parseInt(
                        request.getParameter("id")));
            }else{
                l.setId(0);
            }
            l.setTitulo(request.getParameter("titulo"));
            l.setSinopse(request.getParameter("sinopse"));
            l.setEditora(request.getParameter("editora"));
            l.setEdicao(Integer.parseInt(request.getParameter("edicao")));
            m.setPreco(MoneyUtils.convertRealToFloat(request.getParameter("preco")));
            m.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            m.setTipo("livro");
            a = daoAutor.find(Integer.parseInt(request.getParameter("autorId")));
            if (!request.getParameter("idMidia").equals("")) {
                m.setId(Integer.parseInt(
                        request.getParameter("idMidia")));
            }else{
                m.setId(0);
            }
            l.setAutor(a);
            l.setMidia(m);
            daoLivro.save(l);
        } else if (request.getParameter("editar") != null) {
            int id = Integer.parseInt(request.getParameter("editar"));
            l = daoLivro.find(id);
            request.setAttribute("livro", l);
        } else if (request.getParameter("excluir") != null) {
            int id = Integer.parseInt(request.getParameter("excluir"));
            l = daoLivro.find(id);
            daoLivro.delete(l);
        }

        request.setAttribute("lista", daoLivro.all());
        request.setAttribute("autores", daoAutor.all());

        RequestDispatcher view = request.getRequestDispatcher("livrojstl.jsp");
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
