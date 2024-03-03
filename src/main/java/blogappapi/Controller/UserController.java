package blogappapi.Controller;
import blogappapi.Service.FileService;
import blogappapi.Service.UserService;
import blogappapi.dto.ImageResponse;
import blogappapi.dto.UserDto;
import blogappapi.model.User;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Value("${user.profile.image.path}")
    private  String imageUploadPath;

    @PostMapping("/addSingleUser")
    public User addSingleUser(@Valid @RequestBody UserDto userDto){
        return userService.addSingleUser(userDto);
    }

    @PutMapping("/upadteByid/{id}")
    public User updateUser(@RequestBody UserDto userDto , @PathVariable("id") int id){
        return userService.updateUser(userDto,id);
    }

    @GetMapping("/getuserByid/{id}")
    public UserDto getUserById(@PathVariable int id){
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

    // Add Images
    @PostMapping("/uploadImage/{userId}")
    public ResponseEntity<ImageResponse>uploadUserImage(@PathVariable("userId") int userId,@RequestParam("userImage")MultipartFile image) throws IOException {
        String imageName = fileService.uploadImage(imageUploadPath, image);
        UserDto user = userService.getUserById(userId);
        user.setImageName(imageName);
        User userDto = userService.updateUser(user, userId);

        ImageResponse imageResponse = ImageResponse
                .builder()
                .imageName(imageName)
                .message("image uploaded successfully!!")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();

        return  new ResponseEntity<>(imageResponse,HttpStatus.CREATED);

    }
}
