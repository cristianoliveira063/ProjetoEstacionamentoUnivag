package br.com.univag.service;

import br.com.univag.dao.UsuarioDao;
import br.com.univag.dominio.PerfilUsuario;
import br.com.univag.exception.DaoException;
import br.com.univag.model.UsuarioVo;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author CRISTIANO
 */
public class UsuarioService {

    private Connection con;

    private List<PerfilUsuario> perfil;

    public UsuarioService() {

    }

    public void salvar(UsuarioVo usuario) throws DaoException {
        String senhaGerada = usuario.getSenha();
        String senhaMd5 = convertMD5(usuario.getSenha());
        usuario.setSenha(senhaMd5);
        new UsuarioDao(con).salvarUsuarioVo(usuario);
        usuario.setSenha(senhaGerada);
        enviarSenhaPorEmail(usuario);

    }

    public String convertMD5(String nome) {

        MessageDigest mdigest;
        try {
            mdigest = MessageDigest.getInstance("MD5");
            byte[] valorMd5 = mdigest.digest(nome.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (byte b : valorMd5) {

                sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,
                        3));

            }
            System.out.println("A senha criptografada é:" + sb.toString());
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Erro de criptografia");
            e.getMessage();
            return null;
        } catch (UnsupportedEncodingException e) {
            System.out.println("Erro de criptografia02");
            e.getMessage();
            return null;
        }

    }

    public String geraSenha() {
        String[] novaSenha = {"0", "1", "2", "3", "4", "5", "6", "7", "8",
            "9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};
        String senha = "";
        for (int x = 0; x < 5; x++) {

            int j = (int) (Math.random() * novaSenha.length);
            senha += novaSenha[j];

        }
        System.out.println("A senha gerada é:" + senha);
        return senha;

    }

    @SuppressWarnings("deprecation")
    public void enviarSenhaPorEmail(UsuarioVo usuario) {
        System.out.println("Iniciando envio de Emails");
        SimpleEmail emailSimples = new SimpleEmail();
        System.out.println("Executou SimpleEmail");
        emailSimples.setHostName("smtp.gmail.com");
        System.out.println("passou pelo setHostName");
        emailSimples.setSmtpPort(465);
        System.out.println("setSmtpPort");
        try {
            emailSimples.setFrom("tad2013estacionamento@gmail.com", "Univag");
            System.out.println("setFrom");
            emailSimples.addTo(usuario.getEmail(), "Usuario");
            System.out.println("addTo");
            emailSimples.setSubject("Dados de Acesso ao Sistema.");// assunto
            System.out.println("setSubject");
            emailSimples.setMsg("SENHA DE ACESSO AO SISTEMA : "
                    + usuario.getSenha() + "\n EMAIL DE LOGIN : "
                    + usuario.getEmail());// mensagem
            System.out.println("setMsg");
            emailSimples.setSSL(true);
            System.out.println("setSSL");
            emailSimples.setAuthentication("tad2013estacionamento@gmail.com",
                    "jamaissabera");// dados
            // do
            // email
            System.out.println("setAuthentication");
            System.out.println("Iniciar o envio");
            emailSimples.send();
            System.out.println("Email foi enviado com sucesso");

        } catch (EmailException e) {
            System.out.println("erro de envio de email");
        }
    }

    public int verificarUsuarioVo(UsuarioVo vo) throws DaoException {
        UsuarioDao dao = new UsuarioDao(con);
        UsuarioVo usuarioCpf = dao.consutarUsuarioPorCpf(vo.getCpf());
        UsuarioVo usuarioemail = dao.consultarUsuarioEmail(vo.getEmail());
        if (usuarioCpf != null || usuarioemail != null) {
            if (usuarioCpf != null && usuarioemail != null) {

                return 3;

            } else if (usuarioCpf != null) {

                return 1;
            } else if (usuarioemail != null) {

                return 2;

            }

        }

        return 0;

    }

    /*
     SimpleEmail email = new SimpleEmail();
     email.setSSLOnConnect(true);
     email.setHostName( "smtp.seudominio.com.br" );
     email.setSslSmtpPort( "465" );
     email.setAuthenticator( new DefaultAuthenticator( "rodrigo@seudominio.com.br" ,  "1234" ) );
     try {
     email.setFrom( "rodrigo@seudominio.com.br");
     
     email.setDebug(true);
     
     email.setSubject( "Assunto do E-mail" );
     email.setMsg( "Texto sem formatação" );
     email.addTo( "rodrigoaramburu@gmail.com" );//por favor trocar antes de testar!!!!
     
     email.send();
     
     } catch (EmailException e) {
     e.printStackTrace();
     }
          
     */
    /**
     * @return the perfil
     */
    public List<PerfilUsuario> getPerfil() {
        return Arrays.asList(PerfilUsuario.values());
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(List<PerfilUsuario> perfil) {
        this.perfil = perfil;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    public List<UsuarioVo> listUsuario(int valor) throws DaoException {
        List<UsuarioVo> list = new UsuarioDao(con).listaUsuarioVo(valor);

        return list;

    }

    public int quantidadePaginas() throws DaoException {
        double total = new UsuarioDao(con).totalUsuarioVo();
        if (total <= 10) {

            return 1;

        }

        int resultado = (int) Math.ceil(total / 10);
        return resultado;

    }

    public int quantidadeRegistros() throws DaoException {
        int total = new UsuarioDao(con).totalUsuarioVo();

        return total;

    }

    public UsuarioVo selecionaUserById(int codigo) throws DaoException {

        UsuarioVo vo = new UsuarioVo();
        vo = new UsuarioDao(con).selectUsuarioById(codigo);

        return vo;

    }

    public void AlterarPerfilUser(UsuarioVo vo) throws DaoException {

        new UsuarioDao(con).updateUsuarioVoPerfil(vo);

    }

    public void deleteUserById(int codigo) throws DaoException {

        new UsuarioDao(con).deleteUser(codigo);

    }

    public UsuarioVo autenticarUser(String usuario, String senha) throws DaoException {
        UsuarioDao dao = new UsuarioDao(con);
        String senhaMd5 = convertMD5(senha);
        UsuarioVo vo = dao.autenticarUsuarioVo(usuario, senhaMd5);
        return vo;

    }

    public void recuperUserByEmail(UsuarioVo vo) throws DaoException {

        String senhaNova = geraSenha();
        vo.setSenha(senhaNova);
        enviarSenhaPorEmail(vo);
        String cript = convertMD5(vo.getSenha());
        vo.setSenha(cript);
        new UsuarioDao(con).updateUsuarioVoSenha(vo);

    }

    public UsuarioVo selecionarUserByEmail(String email) throws DaoException {

        UsuarioVo usuario = new UsuarioDao(con).consultarUsuarioEmail(email);

        return usuario;

    }

    public List<UsuarioVo> pesquisaUsuarioService(UsuarioVo vo) throws DaoException {
        List<UsuarioVo> list = new ArrayList<>();
        UsuarioVo usuario = null;
        if (vo.getEmail() != null) {
            usuario = new UsuarioDao(con).consultarUsuarioEmail(vo.getEmail());
            if (usuario != null) {

                list.add(usuario);
            }

        } else if (vo.getCpf() != null) {
            usuario = new UsuarioDao(con).consutarUsuarioPorCpf(vo.getCpf());
            if (usuario != null) {
                list.add(usuario);

            }

        }

        return list;

    }
    
    public void updateSenhaUsuarioVoPerfil(UsuarioVo vo) throws DaoException{
        UsuarioDao dao = new UsuarioDao(con);
        if(!vo.getSenha().equals("")){
        String senhaCript = convertMD5(vo.getSenha());
        String nome = vo.getNome()+" "+vo.getSobrenome();
        vo.setNome(nome.trim());
        vo.setSenha(senhaCript);
        dao.updateUsuarioVoPerfil(vo);
       dao.updateUsuarioVoSenha(vo);  
            
            
        }
        else{
            
        String nome = vo.getNome()+" "+vo.getSobrenome();
        vo.setNome(nome.trim());
        dao.updateUsuarioVoPerfil(vo); 
            
            
        }
       
      
       
    }

}
