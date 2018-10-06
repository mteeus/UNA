package model.repositorio;
import java.sql.Connection;
import java.sql.DriverManager;
public class BancoDeDados {
     protected String url = "jdbc:mysql://localhost:3306/";
 protected String banco = null;
 protected String usuario = null;
 protected String senha = null;
 protected String driver = "com.mysql.cj.jdbc.Driver";
 protected Connection conexao = null;
 public BancoDeDados (String banco, String usuario, String senha) {
 this.banco = banco;
 this.usuario = usuario;
 this.senha = senha;
 this.url += this.banco + "?user=" + this.usuario + "&password=" + this.senha +
"&useSSL=false&useTimezone=true&serverTimezone=UTC";
 System.out.println (this.url);
 }
 public String getUrl () {
 return url;
 }
 public void setUrl (String url) {
 this.url = url;
 }
 public String getBanco () {
 return banco;
 }
 public void setBanco (String banco) {
 this.banco = banco;
 }
 public String getUsuario () {
 return usuario;
 }
 public void setUsuario (String usuario) {
 this.usuario = usuario;
 }
 public String getSenha () {
 return senha;
  }
 public void setSenha (String senha) {
 this.senha = senha;
 }
 public String getDriver () {
 return driver;
 }
 public void setDriver (String driver) {
 this.driver = driver;
 }
 public Connection getConexao () {
 return conexao;
 }
 public void setConexao (Connection conexao) {
 this.conexao = conexao;
 }
 public void abrirConexao () {
 if (this.conexao == null) {
 try {
 Class.forName (this.driver).newInstance ();
 this.conexao = DriverManager.getConnection (this.url);
 } catch (Exception e) {
 System.out.println ("Erro ao abrir conexão com o banco de dados" + this.banco + ".");
 e.printStackTrace ();
 }
 }
 }
 public void fecharConexao () {
 if (this.conexao != null) {
 try {
 this.conexao.close ();
 this.conexao = null;
 } catch (Exception e) {
 System.out.println ("Erro ao fechar conexão com o banco de dados " + this.getBanco ()
+ ".");
 e.printStackTrace ();
 
}
 }
 }
}