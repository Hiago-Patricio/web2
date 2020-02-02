package servlets;

import dao.RevistaDAO;
import entidades.Midia;
import entidades.Revista;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.MoneyUtils;

@WebServlet(name = "RevistaCDIServlet", urlPatterns = {"/revistacdi"})
public class RevistaCDIServlet extends HttpServlet {

    @Inject
    private Midia m;
    @Inject
    private Revista r;
    @Inject
    private RevistaDAO daoRevista;
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
        
        if(request.getParameter("titulo") != null
            && request.getParameter("editora") != null
            && request.getParameter("preco") != null
            && request.getParameter("quantidade") != null) 
        {
            if(!request.getParameter("id").equals("")){
                r.setId(Integer.parseInt(
                        request.getParameter("id")));
            }else{
                r.setId(0);
            }
            r.setTitulo(request.getParameter("titulo"));
            r.setEditora(request.getParameter("editora"));
            m.setPreco(MoneyUtils.convertRealToFloat(
                    request.getParameter("preco")));
            m.setQuantidade(Integer.parseInt(
                    request.getParameter("quantidade")));
            if(!request.getParameter("idMidia").equals("")){
                m.setId(Integer.parseInt(
                        request.getParameter("idMidia")));
            }else{
                m.setId(0);
            }
            r.setMidia(m);
            System.out.println(r.getId());
            System.out.println(r.getTitulo());
            System.out.println(r.getEditora());
            System.out.println(r.getMidia().getId());
            System.out.println(r.getMidia().getPreco());
            System.out.println(r.getMidia().getQuantidade());
            daoRevista.save(r);
        } else if (request.getParameter("editar") != null) {
            int id = Integer.parseInt(request.getParameter("editar"));
            r = daoRevista.find(id);
            request.setAttribute("revista", r);
        } else if (request.getParameter("excluir") != null) {
            int id = Integer.parseInt(request.getParameter("excluir"));
            r = daoRevista.find(id);
            daoRevista.delete(r);
        }
        
        request.setAttribute("lista", daoRevista.all());

        RequestDispatcher view = request.getRequestDispatcher("revistacdi.jsp");
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
