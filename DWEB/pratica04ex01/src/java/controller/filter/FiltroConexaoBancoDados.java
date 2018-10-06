package controller.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import model.dao.UsuarioDao;
public class FiltroConexaoBancoDados implements Filter {
 public FiltroConexaoBancoDados () {
 }
 @Override
 public void init (FilterConfig arg) throws ServletException {
 }
 @Override
 public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain)
 throws IOException, ServletException {
 System.out.println ("--Entrando no filtro: FiltroConexaoBancoDados");
 //Lógica do filtro: abrir conexão com o banco de dados.
 UsuarioDao usuarioDao = new UsuarioDao ("teste", "root", "root");
 usuarioDao.conectar ();
 request.setAttribute ("usuario_dao", usuarioDao);
 //Lógica do filtro: encaminhar requisição.
 chain.doFilter (request, response);
 //Lógica do filtro: fechar conexão com o banco de dados.
 usuarioDao.desconectar ();
 System.out.println ("--Saindo do filtro: FiltroConexaoBancoDados");
 }
 @Override
 public void destroy () {
 }
}