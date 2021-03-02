package com.project.hourbank.services;

import com.project.hourbank.models.User;
import com.project.hourbank.repositories.UserRepository;
import com.project.hourbank.util.Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public final String apikey = "lucasjarrierdeaquinocavalcantimariameduardasouzacunhadasilvacienciasdacomputacao";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Util util;


    // OK
    public User save(User user) {
        return userRepository.save((user));
    }

    // OK
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users :: add);
        return users;
    }

    // OK
    public User findById(Long id) {
        User idUser = null;
        for (User user : (List<User>) userRepository.findAll()) {
            if(user.getId() == id){
                idUser = user;
            }
        }
        return idUser;
    }

    // OK
    public User findByEmail(String email) {
        User mailUser = null;
        for (User user : (List<User>) userRepository.findAll()){
            if(user.getMail().equals(email)){
                return user;
            }
        } return mailUser;
    }

    // OK
    public User deleteUSerId(long id) {
        User user = findById(id);
        if(user == null) return null;
        userRepository.delete(user);
        return user;
    }
    // OK
    public User update(Long id, User userUpdates) {
        User user = findById(id);
        if(user != null) {
            user.setMail(userUpdates.getMail());
            user.setName(userUpdates.getName());
            user.setPassword(userUpdates.getPassword());
            final User userUpdated = userRepository.save(user);
        }
        return user;
    }


    // Validation

    private boolean validateUser(User user, String password) throws Exception {

        // Funcional
        if((user.getPassword().equals(password))) {
            return true;
        }

        // Falta terminar
        if (!user.isEnabled()) {
            return false;
        }
        return true;
    }

    // Metedodo muito encapsulado
    public String login(String mail,String password) throws Exception {
        User user = this.findByEmail(mail);
        if (user != null) {
            Boolean validation = validateUser(user, password);
            if(validation) {
                String token = createJWT(user);
                return token;
            }
            return ("Olá, " + user.getName() + "! \nSua senha está incorreta.");
        }
        return "Olá Visitante, este e-mail não está cadastrado!";
    }


    // Funcional (Minha solução temporaria e possívelmten substitivel por algo melhor) -> Deprecation!
    private String createJWT(User user) {
        @SuppressWarnings("deprecation")
        String token = Jwts.builder().setSubject(user.getMail()).setId((user.getIdString())).signWith(SignatureAlgorithm.HS256, apikey)
                .setExpiration(new Date(System.currentTimeMillis() + (60000 * 360))).compact();
        return token;
    }

    /**
     * TERMINAR O PARSEJWT
     * @param bearer
     * @return
     * @throws Exception
     */

    public String checkJWT(String bearer) throws Exception {
        if (bearer == null || !bearer.substring(0, 6).equals("Bearer"))
            throw new Exception("Token não presente ou inválido");

        String token = bearer.substring(7);
        Claims data = parseJWT(token);
        Date instant = new Date(System.currentTimeMillis());

        if (data.getExpiration().before(instant))
            throw new Exception("Token expirado");

        String email = data.getSubject();

        return email;
    }

    private Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(apikey)).parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

}
