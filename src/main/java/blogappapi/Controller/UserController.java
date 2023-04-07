package blogappapi.Controller;
import blogappapi.Service.UserService;
import blogappapi.dto.UserDto;
import blogappapi.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addSingleUser")
    public User addSingleUser(@Valid @RequestBody UserDto userDto){
        return userService.addSingleUser(userDto);
    }

    @PutMapping("/upadteByid/{id}")
    public User updateUser(@RequestBody UserDto userDto , @PathVariable("id") int id){
        return userService.updateUser(userDto,id);
    }

    @GetMapping("/getuserByid/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser(){
        return userService.getAllUSer();
    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        return userService.deleteAll();
    }

//    @PostMapping("/add1")
//    public List<User> adda(@RequestBody List<UserDto> userDto , UserDto userDto1) {
//        return userService.add1(userDto , userDto1);
//    }

//     @PostMapping("/addMultipleUser")
//    public List<UserDto> add(@RequestBody UserDto userDto){
//        return userService.addListOfUser(userDto);
//    }
}
