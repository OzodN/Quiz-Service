# 📚 Quiz Service

A simple **Java console-based Quiz Management System**.  
This project allows **teachers** to create and manage quizzes, while **students** can register, log in, and take quizzes with automatic scoring.  

It is designed with **Object-Oriented Programming (OOP)** principles and structured into layers for easy maintenance.

---

## ✨ Features

### 👩‍🏫 Teacher
- Add new quiz questions with multiple answers
- Update existing questions and answers
- Delete questions
- View all available questions

### 👨‍🎓 Student
- Register or log in
- Take quizzes with multiple-choice questions
- Get quiz results (score & total questions)

### 🔐 Authentication
- Separate registration for **Teacher** and **Student**
- Login with username & password
- Session-based role access (Teacher Menu / Student Menu)

---

## 🗂 Project Structure
```plaintext
src/
└── uz/pdp/quizService
├── model/ # Domain models (User, Question, Answer, QuizResult, Role)
├── repository/ # Repositories (QuestionRepository, UserRepository)
├── service/ # Business logic (AuthService, UserService, QuizService, TeacherService)
├── ui/ # Console menus (MainMenu, TeacherMenu, StudentMenu)
├── util/ # Utility classes (InputUtil)
├── uml/ # Interfaces (Menu, Quiz, BaseClass)
└── Main.java # Application entry point

```

## 🛠 Technologies
- **Java 17+**
- **Console-based UI**
- **OOP Design**
- **JavaDoc Documentation**

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/OzodN/quiz-service.git
2. Navigate into the project folder:
``` 
 cd quiz-service
```
3. Compile the project: 
```
 javac .\quizService\Main.java
```
4. Run the program:
```
java quizService.Main
```
