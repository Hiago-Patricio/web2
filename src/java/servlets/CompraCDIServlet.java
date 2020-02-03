/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ClienteDAO;
import dao.CompraDAO;
import dao.FuncionarioDAO;
import dao.LivroDAO;
import dao.MidiaDAO;
import dao.RevistaDAO;
import entidades.Compra;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
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
@WebServlet(name = "CompraCDIServlet", urlPatterns = {"/compracdi"})
public class CompraCDIServlet extends HttpServlet {

    @Inject
    private CompraDAO daoCompra;
    @Inject
    private Compra compra;
    @Inject
    private ClienteDAO daoCliente;
    @Inject
    private FuncionarioDAO daoFuncionario;
    @Inject
    private MidiaDAO daoMidia;
    @Inject
    private RevistaDAO daoRevista;
    @Inject
    private LivroDAO daoLivro;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        if(request.getParameter("clienteId") != null
            && request.getParameter("funcionarioId") != null
            && request.getParameter("quantidade") != null
            && request.getParameter("midiaId") != null
            && request.getParameter("data") != null)
        {
            if(!request.getParameter("id").equals("")){
                compra.setId(Integer.parseInt(
                    request.getParameter("id")));
            }else{
                compra.setId(0);
            }
            
            int clienteId = Integer.parseInt(
                request.getParameter("clienteId"));
            compra.setCliente(daoCliente.find(clienteId));
            
            int funcionarioId = Integer.parseInt(
                request.getParameter("funcionarioId"));
            compra.setFuncionario(daoFuncionario.find(funcionarioId));
            
            compra.setQuantidade(Integer.parseInt(
                request.getParameter("quantidade")));
            
            int midiaId = Integer.parseInt(
                request.getParameter("midiaId"));
            compra.setMidia(daoMidia.find(midiaId));
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            compra.setData(sdf.parse(request.getParameter("data")));
            daoCompra.save(compra);
        }else if(request.getParameter("editar") != null){
            int id = Integer.parseInt(request.getParameter("editar"));
            compra = daoCompra.find(id);
            request.setAttribute("compra", compra);
        } else if (request.getParameter("excluir") != null) {
            int id = Integer.parseInt(request.getParameter("excluir"));
            compra = daoCompra.find(id);
            daoCompra.delete(compra);
        }
        
        request.setAttribute("compras", daoCompra.all());
        request.setAttribute("clientes", daoCliente.all());
        request.setAttribute("funcionarios", daoFuncionario.all());
        request.setAttribute("revistas", daoRevista.all());
        request.setAttribute("livros", daoLivro.all());
        
        RequestDispatcher view = request.getRequestDispatcher("compracdi.jsp");
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
            Logger.getLogger(CompraCDIServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CompraCDIServlet.class.getName()).log(Level.SEVERE, null, ex);
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
