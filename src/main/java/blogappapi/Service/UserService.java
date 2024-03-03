package blogappapi.Service;


import blogappapi.dao.UserRepository;
import blogappapi.dto.UserDto;
import blogappapi.exception.ResourceNotFountException;
import blogappapi.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
     ModelMapper modelMapper;

    // add single user
    public User addSingleUser(UserDto userDto){
        User u1= new User();
        u1.setName(userDto.getName());
        u1.setEmail(userDto.getEmail());
        u1.setPassword(userDto.getPassword());
        u1.setAbout(userDto.getAbout());
        return userRepository.save(u1);
    }

    // Add User With image
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        // Upload image
        if (userDto.getImageFile() != null) {
            try {
                user.setImage(userDto.getImageFile().getBytes());
                user.setImageName(userDto.getImageFile().getOriginalFilename());
            } catch (IOException e) {
                // Handle exception
            }
        }

        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }








// update
    public User updateUser(UserDto userDto , Integer userId){
        User user1= this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFountException("User","Id",userId));
        user1.setName(userDto.getName());
        user1.setEmail(userDto.getEmail());
        user1.setPassword(user1.getPassword());
        user1.setAbout(userDto.getAbout());
        user1.setImageName(userDto.getImageName());
        return userRepository.save(user1);
    }

    // get user by id
    public UserDto getUserById(int id){

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Id is not found !!"));
        return modelMapper.map(user,UserDto.class);

    }

    // get All user
    public List<User> getAllUSer(){
        List<User> listOfUser = userRepository.findAll();
        System.out.println("_________======abc..    ====="+listOfUser);
        return listOfUser;
    }

    public String deleteUser(int id){
         userRepository.deleteById(id);
         return "user Delete Successfully";
    }

    public String deleteAll(){
        userRepository.deleteAll();
        return "delete All record successfully";
    }

    public User dtoToUser(UserDto userDto){
        User u=this.modelMapper.map(userDto,User.class);

//        User u=new User();
//        u.setId(userDto.getId());
//        u.setName(userDto.getName());
//        u.setEmail(userDto.getEmail());
//        u.setPassword(userDto.getPassword());
//        u.setAbout(userDto.getAbout());
        return u;
    }

    public UserDto userToDto(User user){
        UserDto ud=this.modelMapper.map(user,UserDto.class);
//        UserDto ud=new UserDto();
//        ud.setName(user.getName());
//        ud.setId(user.getId());
//        ud.setPassword(user.getPassword());
//        ud.setEmail(user.getEmail());
//        ud.setAbout(user.getAbout());
        return ud;
    }

//    public List<User> add1(List<UserDto> userDtos1,UserDto userDto){
//        User m= new User();
//        m.setName(userDto.getName());
//        m.setEmail(userDto.getEmail());
//        m.setPassword(userDto.getPassword());
//        m.setAbout(userDto.getAbout());
//        return Collections.singletonList(m);
//    }


//          public List<UserDto> addListOfUser(UserDto userDto){
//        List<User> users= this.userRepository.findAll();
//        List <UserDto> userDtos=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
//        return this.userRepository.saveAll(userDtos);
//
//    }
}
