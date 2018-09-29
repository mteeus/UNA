package model.dao;

import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import model.Usuario;
import model.repositorio.BancoDeDados;

public class UsuarioDao {

    BancoDeDados bd = null;

    public UsuarioDao(String banco, String usuario, String senha) {
        this.bd = new BancoDeDados(banco, usuario, senha);
    }

    public void conectar() {
        this.bd.abrirConexao();
    }

    public void desconectar() {
        this.bd.fecharConexao();
    }

    public Usuario inserir(Usuario usuario) {
        long id;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String insert = "insert into teste.usuario (nome, email, senha, nascimento) values (?, ?, ?,?)";
    
 ps = this.bd.getConexao().prepareStatement(insert,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setDate(4, Date.valueOf(usuario.getNascimento()));
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                usuario = this.encontrar(id);
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir o usuário no banco de dados.");
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar manipulador do banco de dados.");
                e.printStackTrace();
            }
        }
        return usuario;
    }

    public List<Usuario> listar() {
        List<Usuario> resp = new LinkedList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.bd.getConexao().createStatement();
            String select = "select * from teste.usuario";
            rs = stmt.executeQuery(select);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setNascimento(LocalDate.parse(rs.getString("nascimento")));
                resp.add(usuario);
            }
        } catch (Exception e) {
            resp = null;
            System.out.println("Erro ao ler os dados dos usuário a partir do banco de dados.");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar os manipuladores do banco de dados.");
                e.printStackTrace();
            }
        }
        return resp;
    }

    public Usuario encontrar(long id) {
        Usuario usuario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String select = "select * from teste.usuario where id = ?";
            ps = this.bd.getConexao().prepareStatement(select);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setNascimento(LocalDate.parse(rs.getString("nascimento")));
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler os dados do usuário a partir do banco de dados.");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar os manipuladores do banco de dados.");
                e.printStackTrace();
            }
        }
        return usuario;
    }

    public void alterar(Usuario usuario) {
        PreparedStatement ps = null;
        try {
            String update = "update teste.usuario set nome = ?, email = ?, senha = ?, nascimento = ?where id = ?";
     
            
  ps = this.bd.getConexao().prepareStatement(update);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setDate(4, Date.valueOf(usuario.getNascimento()));
            ps.setLong(5, usuario.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao atualizar os dados do usuário no banco de dados.");
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar o manipulador do banco de dados.");
                e.printStackTrace();
            }
        }
    }

    public void remover(Usuario usuario) {
        PreparedStatement ps = null;
        try {
            String delete = "delete from teste.usuario where id = ?";
            ps = this.bd.getConexao().prepareStatement(delete);
            ps.setLong(1, usuario.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao excluir usuário no banco de dados.");
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar o manipulador de banco de dados.");
                e.printStackTrace();
            }
        }
    }

    public void remover() {
        PreparedStatement ps = null;
        try {
            String truncate = "truncate teste.usuario";
            ps = this.bd.getConexao().prepareStatement(truncate);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao excluir usuários no banco de dados.");
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar o manipulador de banco de dados.");
                e.printStackTrace();
            }
        }
    }
}
