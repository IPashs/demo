
# Тестовое задание от 19.12.2024

### Особенности реализации:
- В связи с отсутствием спецификации по ролевой и статусной модели, они были реализованы на усмотрение разработчика.
- Для сокращения сроков разработки тесты покрывают только пример создания проекта (другие тесты не реализованы).
- Реализована простая безопасность на основе JWT и простой ролевой модели (без refresh токена).
- Swagger для документирования API не был добавлен.
- Mapstruct для маппинга объектов также не использовался.

---

## Развертывание проекта

### Требования:
- Docker установлен и настроен.

### Шаги:
1. Выполните команду:
   ```bash
   docker compose up -d
   ```
   В результате будут подняты два контейнера:
   - Контейнер с базой данных (порт: **5432**).
   - Контейнер с приложением (порт: **8080**).

   ⚠️ Убедитесь, что указанные порты свободны на вашей системе.

---

## Работа с API

### Регистрация пользователя
- **URL:** `POST http://localhost:8080/reg`
- **Тело запроса:**
  ```json
  {
    "email": "mail100@mail.ru",
    "name": "Юрий Никулин",
    "password": "123"
  }
  ```
- В ответе будет предоставлен `bearer токен`.

---

### Создание проекта
- **URL:** `POST http://localhost:8080/projects`
- **Тело запроса:**
  ```json
  {
    "name": "название проекта",
    "code": "шифр007",
    "startDate": "2024-12-21T14:30:00",
    "endDate": "2025-12-21T14:30:00",
    "authorEmail": "mail100@mail.ru",
    "sections": [
      "27613fb3-00cb-4c77-84d2-07d5cf19816c",
      "87613fb3-00cb-4c77-84d2-07d5cf19816c"
    ]
  }
  ```

---

### Получение проекта
- **URL:** `GET http://localhost:8080/projects/{PROJECT_ID}`
- `{PROJECT_ID}` – идентификатор проекта из ответа предыдущего запроса.

---

### Обновление проекта
- **URL:** `PUT http://localhost:8080/projects/{PROJECT_ID}`
- **Тело запроса:**
  ```json
  {
    "name": "название проекта",
    "code": "шифр007",
    "startDate": "2024-12-21T14:30:00",
    "endDate": "2025-12-21T14:30:00",
    "authorEmail": "mail100@mail.ru",
    "sections": [
      "17613fb3-00cb-4c77-84d2-07d5cf19816c",
      "87613fb3-00cb-4c77-84d2-07d5cf19816c"
    ],
    "status": "AT_WORK"
  }
  ```

---

### Удаление проекта
- **URL:** `DELETE http://localhost:8080/projects/{PROJECT_ID}`
- `{PROJECT_ID}` – идентификатор проекта из ответа на запрос создания.

---

## Дополнительная информация
Для добавления, удаления и получения разделов, а также списка проектов реализованы соответствующие методы. Ознакомьтесь с их реализацией в коде проекта.

---
