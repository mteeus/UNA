package controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
@WebFilter (filterName = "Filtro", urlPatterns = {"/controlador"})
public class Filtro implements Filter {
 public Filtro () {
 }
 public void init (FilterConfig filterConfig) {
 }
 public void doFilter (ServletRequest request, ServletResponse response,
 FilterChain chain)
 throws IOException, ServletException {
      System.out.println (">>> Entrando no Filtro.");
 try {
 chain.doFilter (request, response);
 } catch (Throwable t) {
 t.printStackTrace ();
 }
 System.out.println (">>> Saindo no Filtro.");
 }
 public void destroy () {
 }
}

 