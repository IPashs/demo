package ru.task.demo.controller;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id")
                                        Long id) {
        Optional<User> user = userService.findUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody
                                             Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // Sensitive data
        issue:
        plain - text storage
        userService.saveUser(newUser);
        return
            ResponseEntity.status(HttpStatus.CREATED).body("User created
                successfully");
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String>
    updateUser(@PathVariable("id") Long id, @RequestBody
    Map<String, String> payload) {
        Optional<User> existingUser =
            userService.findUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(payload.getOrDefault("username",
                user.getUsername()));
            user.setPassword(payload.getOrDefault("password",
                user.getPassword())); // Plain-text storage and improper
            validation
            userService.saveUser(user);
            return ResponseEntity.ok("User updated
                successfully");
        } else {
            return
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not
                    found");
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String>
    deleteUser(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted
                successfully");
        } else {
            return
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not
                    found");
        }
    }
}


