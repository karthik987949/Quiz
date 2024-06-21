import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizSystem {
    // Inner class to represent a question
    static class Question {
        private String text;
        private List<String> options;
        private int correctAnswer;

        public Question(String text, List<String> options, int correctAnswer) {
            this.text = text;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getText() {
            return text;
        }

        public List<String> getOptions() {
            return options;
        }

        public boolean isCorrect(int answer) {
            return answer == correctAnswer;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(text).append("\n");
            for (int i = 0; i < options.size(); i++) {
                sb.append(i + 1).append(". ").append(options.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    private List<Question> questions;
    private int score;

    public QuizSystem() {
        this.questions = new ArrayList<>();
        this.score = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question);
            int answer = -1;
            boolean validInput = false;

            while (!validInput) {
                try {
                    System.out.print("Your answer (1-" + question.getOptions().size() + "): ");
                    answer = Integer.parseInt(scanner.nextLine());
                    if (answer < 1 || answer > question.getOptions().size()) {
                        throw new NumberFormatException();
                    }
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and " + question.getOptions().size() + ".");
                }
            }

            if (question.isCorrect(answer - 1)) {
                score++;
            }
        }

        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
        scanner.close();
    }

    public static void main(String[] args) {
        QuizSystem quiz = new QuizSystem();

        List<String> options1 = List.of("Berlin", "Madrid", "Paris", "Lisbon");
        quiz.addQuestion(new Question("What is the capital of France?", options1, 2));

        List<String> options2 = List.of("3", "4", "5", "6");
        quiz.addQuestion(new Question("What is 2 + 2?", options2, 1));

        quiz.start();
    }
}
