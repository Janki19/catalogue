package pl.kubie.catalogue;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ViewAnswers {

    //Check Main Menu choice
    public int mainMenuChoice() {
        int retChoice;
        while (true) {
            try {
                int tempNumber = intAnswer();
                if (tempNumber >= 0 && tempNumber < 9) {
                    retChoice = tempNumber;
                    break;
                } else {
                    System.out.println("Try again!");
                }
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return retChoice;
    }

    //Check search  choice
    public int searchChoice() {
        int retChoice;
        while (true) {
            try {
                int tempNumber = intAnswer();
                if (tempNumber == 1 || tempNumber == 2 || tempNumber == 3) {
                    retChoice = tempNumber;
                    break;
                } else {
                    System.out.println("Try again!");
                }
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return retChoice;
    }

    //Check movies list input
    public int checkIfBack() {
        int retAnswer;
        while (true) {
            try {
                if (intAnswer() == 9) {
                    retAnswer = 9;
                    break;
                } else System.out.println("Try again!");
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return retAnswer;
    }

    //Checking Add New Movies input
    public String yearInput() {
        int tempYear;
        while (true) {
            try {
                int inputYear = intAnswer();
                if (inputYear < 2020) {
                    tempYear = inputYear;
                    break;
                } else {
                    System.out.println("Try again!");
                }
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return String.valueOf(tempYear);
    }

    // Checking the answer and return true if record need to be saved.

    public boolean selectYesNo() {
        boolean temReturn = false;
        while (true) {
            String yesNo = stringAnswer();
            if (yesNo.equalsIgnoreCase("y")) {
                temReturn = true;
                break;
            } else if (yesNo.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Wrong answer!");
            }
        }
        return temReturn;
    }

    //Check if type is int
    public int ifInt() {
        int number;
        while (true) {
            try {
                number = intAnswer();
                break;
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return number;
    }

    //Check ratings between 1 to 7
    public int checkRatings() {
        int ratingAnswer;
        while (true) {
            try {
                int ratingsNumber = intAnswer();
                if (ratingsNumber > 0 && ratingsNumber <= 7) {
                    ratingAnswer = ratingsNumber;
                    break;
                } else System.out.println("Try again!");
            } catch (Exception e) {
                System.out.println("Try again!");
            }
        }
        return ratingAnswer;
    }

    //Checks if the date has been entered correctly
    public LocalDate checkIfDateIsCorrect() {
        LocalDate date;
        while (true) {
            String dateInput = stringAnswer();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                date = LocalDate.parse(dateInput, formatter);
                break;
            } catch (DateTimeException e) {
                System.out.println("Entered date is incorrect. Try again.");
            }
        }
        return date;
    }

    public String stringAnswer() {
        Scanner answer = new Scanner(System.in);
        return answer.nextLine();
    }

    public int intAnswer() {
        Scanner answer = new Scanner(System.in);
        return answer.nextInt();
    }
}
