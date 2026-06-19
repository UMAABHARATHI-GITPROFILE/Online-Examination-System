package com.onlineexam.main;
import java.util.List;
import java.util.Scanner;

import com.onlineexam.model.Student;
import com.onlineexam.model.Exam;
import com.onlineexam.service.AdminService;
import com.onlineexam.service.StudentService;
import com.onlineexam.service.ExamService;
import com.onlineexam.model.Question;
import com.onlineexam.service.QuestionService;


public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AdminService adminService = new AdminService();
        StudentService studentService = new StudentService();
        ExamService examService = new ExamService();
        QuestionService questionService = new QuestionService();


        while (true) {

            System.out.println("\n=== ONLINE EXAMINATION SYSTEM ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Registration");
            System.out.println("3. Student Login");
            System.out.println("4. Exit");
            System.out.println("5. Add Question");

            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Admin Username: ");
                    String adminUser = sc.nextLine();

                    System.out.print("Admin Password: ");
                    String adminPass = sc.nextLine();

                    if (adminService.login(adminUser, adminPass)) {
                        System.out.println("Admin login successful");

                        System.out.println("\n--- ADMIN MENU ---");
                        System.out.println("1. Add Exam");
                        System.out.print("Enter choice: ");

                        int adminChoice = sc.nextInt();
                        sc.nextLine();

                        if (adminChoice == 1) {
                            Exam exam = new Exam();

                            System.out.print("Exam Name: ");
                            exam.setExamName(sc.nextLine());

                            System.out.print("Duration (minutes): ");
                            exam.setDuration(sc.nextInt());
                            sc.nextLine();

                            examService.addExam(exam);
                            System.out.println("Exam added successfully");
                        }

                    } else {
                        System.out.println("Invalid admin credentials");
                    }
                    break;

                case 2:
                    Student student = new Student();

                    System.out.print("Name: ");
                    student.setName(sc.nextLine());

                    System.out.print("Email: ");
                    student.setEmail(sc.nextLine());

                    System.out.print("Password: ");
                    student.setPassword(sc.nextLine());

                    if (studentService.register(student)) {
                        System.out.println("Student registered successfully");
                    } else {
                        System.out.println("Registration failed");
                    }
                    break;

                case 3:
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    Student loggedStudent = studentService.login(email, password);

                    if (loggedStudent != null) {
                        System.out.println("Welcome " + loggedStudent.getName());
                        System.out.println("\nAvailable Exams:");
                        List<Exam> exams = examService.getAllExams();

                        for (Exam e : exams) {
                            System.out.println(e.getExamId() + " - " + e.getExamName());
                        }

                        System.out.print("Enter Exam ID to start exam: ");
                        int examId = sc.nextInt();
                        sc.nextLine();

                        List<Question> questions = questionService.getQuestionsByExamId(examId);

                        int score = 0;

                        for (Question q : questions) {

                            System.out.println("\n" + q.getQuestionText());
                            System.out.println("A. " + q.getOptionA());
                            System.out.println("B. " + q.getOptionB());
                            System.out.println("C. " + q.getOptionC());
                            System.out.println("D. " + q.getOptionD());

                            System.out.print("Your answer (A/B/C/D): ");
                            char answer = sc.next().toUpperCase().charAt(0);
                            sc.nextLine();

                            if (answer == q.getCorrectOption()) {
                                score++;
                            }
                        }

                        System.out.println("\nExam Finished!");
                        System.out.println("Your Score: " + score + " out of " + questions.size());

                    } else {
                        System.out.println("Invalid student credentials");
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using the system");
                    sc.close();
                    System.exit(0);
                
                case 5:
                    Question question = new Question();

                    System.out.print("Enter Exam ID: ");
                    question.setExamId(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Question: ");
                    question.setQuestionText(sc.nextLine());

                    System.out.print("Option A: ");
                    question.setOptionA(sc.nextLine());

                    System.out.print("Option B: ");
                    question.setOptionB(sc.nextLine());

                    System.out.print("Option C: ");
                    question.setOptionC(sc.nextLine());

                    System.out.print("Option D: ");
                    question.setOptionD(sc.nextLine());

                    System.out.print("Correct Option (A/B/C/D): ");
                    question.setCorrectOption(sc.next().charAt(0));
                    sc.nextLine();

                    questionService.addQuestion(question);
                    System.out.println("Question added successfully");


                    break;


                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
