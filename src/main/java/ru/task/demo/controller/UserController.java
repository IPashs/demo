package ru.task.demo.controller;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User
                                                 user) {
        if (user.getName() == null ||
            user.getName().isEmpty()) {
            return new ResponseEntity<>("User name is
                required", HttpStatus.BAD_REQUEST);
        }
        if (user.getEmail() == null ||
            user.getEmail().isEmpty()) {
            return new ResponseEntity<>("User email is
                required", HttpStatus.BAD_REQUEST);
        }
        if (!user.getEmail().contains("@")) {
            return new ResponseEntity<>("Invalid email
                format", HttpStatus.BAD_REQUEST);
        }
        if (userService.findUserByEmail(user.getEmail()) !=
            null) {
            return new ResponseEntity<>("User with this email
                already exists", HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        return new ResponseEntity<>("User created
            successfully", HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable
                                             Long id, @RequestBody User user) {
        User existingUser = userService.findUserById(id);
        if (existingUser == null) {
            return new ResponseEntity<>("User not found",
                HttpStatus.NOT_FOUND);
        }
        if (user.getName() != null &&
            !user.getName().isEmpty()) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null &&
            !user.getEmail().isEmpty() && user.getEmail().contains("@")) {
            existingUser.setEmail(user.getEmail());
        }
        userService.saveUser(existingUser);
        return new ResponseEntity<>("User updated
            successfully", HttpStatus.OK);
    }
}
