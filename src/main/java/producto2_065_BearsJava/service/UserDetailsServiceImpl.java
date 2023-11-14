//package producto2_065_BearsJava.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import producto2_065_BearsJava.repository.UserRepo;
//import producto2_065_BearsJava.model.Role;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    UserRepo userRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//
//        //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
//
//        producto2_065_BearsJava.model.User appUser =
//                userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
//
//        //Mapear nuestra lista de Authority con la de spring security
//        List grantList = new ArrayList();
//        for (Role authority : appUser.getRoles()) {
//            // ROLE_USER, ROLE_ADMIN,..
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
//            grantList.add(grantedAuthority);
//        }
//
//        //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
//        UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
//        return user;
//    }
//}