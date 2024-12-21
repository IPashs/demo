INSERT INTO users (id, name, password, email, role, registered_at)
VALUES ('d657d102-cfb2-44ca-bde3-f978351ddcb7', 'Игорь', '$2a$10$Ntls/233Ge9Fs6orFPxOOO2MaqvkXpx6TgddaMwE98JWHsVav800W', 'mail3@mail.ru', 'USER', '2024-12-20 18:17:03')
ON CONFLICT DO NOTHING;