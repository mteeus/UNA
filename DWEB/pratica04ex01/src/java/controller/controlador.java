package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.dao.UsuarioDao;
public class Controlador extends HttpServlet {
 protected void processRequest (HttpServletRequest request, HttpServletResponse
response)
 throws ServletException, IOException {
 response.setContentType ("text/html;charset=UTF-8");
 Usuario usuario = new Usuario ();
 usuario.setNome (request.getParameter ("nome"));
 usuario.setEmail (request.getParameter ("email"));
 usuario.setSenha (request.getParameter ("senha"));
 usuario.setNascimento (LocalDate.parse (request.getParameter ("nascimento")));
 UsuarioDao usuarioDao = (UsuarioDao) request.getAttribute ("usuario_dao");
 usuario = usuarioDao.inserir (usuario);
 try (PrintWriter out = response.getWriter ()) {
 out.println ("<!DOCTYPE html>");
 out.println ("<html>");
 out.println ("<head>");
 out.println ("<title>Servlet Controlador</title>");
 out.println ("</head>");
 out.println ("<body>");
 out.println ("<h1>Usuario:</h1>");
 out.println ("ID: " + usuario.getId () + "<br>");
 out.println ("Nome: " + usuario.getNome () + "<br>");
 out.println ("E-mail: " + usuario.getEmail () + "<br>");
 out.println ("Senha: " + usuario.getSenha () + "<br>");
 DateTimeFormatter formato = DateTimeFormatter.ofPattern ("dd/MM/uuuu");
 out.println ("Data de nascimento: " + usuario.getNascimento ().format (formato) +
"<br>");
 out.println ("<br>");
 out.println ("</body>");
 out.println ("</html>");
 }
 }
 @Override
 protected void doGet (HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 processRequest (request, response);
 }
 @Override
 protected void doPost (HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 processRequest (request, response);
 }
}
