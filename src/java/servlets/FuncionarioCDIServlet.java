package servlets;

import dao.FuncionarioDAO;
import entidades.Funcionario;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.MoneyUtils;

@WebServlet(name = "FuncionarioCDIServlet", urlPatterns = {"/funcionariocdi"})
public class FuncionarioCDIServlet extends HttpServlet {

    @Inject
    private Funcionario f;

    @Inject
    private FuncionarioDAO dao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("nome") != null
                && request.getParameter("cargo") != null
                && request.getParameter("salario") != null
                && request.getParameter("dataAdmissao") != null) {
            if (!request.getParameter("id").equals("")) {
                f.setId(Integer.parseInt(
                        request.getParameter("id")));
            }else{
                f.setId(0);
            }
            f.setNome(request.getParameter("nome"));
            f.setCargo(request.getParameter("cargo"));
            f.setSalario(MoneyUtils.convertRealToFloat(
                    request.getParameter("salario")));

            Date dataAdmissao = null;
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dataAdmissao = sdf.parse(request.getParameter("dataAdmissao"));
                f.setDataAdmissao(dataAdmissao);
            }catch(Exception erro){
            }
            dao.save(f);
        } else if (request.getParameter("editar") != null) {
            Integer id = Integer.parseInt(request.getParameter("editar"));
            f = dao.find(id);
            request.setAttribute("funcionario", f);
        } else if (request.getParameter("excluir") != null) {
            Integer id = Integer.parseInt(request.getParameter("excluir"));
            f = dao.find(id);
            dao.delete(f);
        }

        request.setAttribute("lista", dao.all());

        RequestDispatcher view = request.getRequestDispatcher("funcionariocdi.jsp");
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
            Logger.getLogger(FuncionarioCDIServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FuncionarioCDIServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
