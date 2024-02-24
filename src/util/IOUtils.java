package util;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class IOUtils {
    private static final String MM_DD_YYYY = "MM/dd/yyyy";

    private IOUtils() {
    }

    public static int getUserChoice(int... range) {
        Scanner sc = new Scanner(System.in);
        try {
            int choice = Integer.parseInt(sc.nextLine());
            if (Arrays.stream(range).noneMatch(value -> value == choice)) {
                throw new InputMismatchException();
            }
            return choice;
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("please select " + range[0] + " - " + range[range.length - 1]);
            return getUserChoice(range);
        }

    }

    public static String getUserChoice(String... range) {
        Scanner sc = new Scanner(System.in);
        try {
            String choice = sc.nextLine();
            if (Arrays.stream(range).noneMatch(value -> value.equalsIgnoreCase(choice))) {
                throw new InputMismatchException();
            }
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("please select " + range[0] + " - " + range[range.length - 1]);
            return getUserChoice(range);
        }
    }

    public static String enterRoomNumber() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String roomIdPattern = "R[0-9]{3}";
        if (Pattern.compile(roomIdPattern).matcher(input).find()) {
            return input;
        } else {
            System.out.println("Room number must be the same format Rxxx (ex: R123)");
            return enterRoomNumber();
        }
    }

    public static double enterPrice() {
        Scanner sc = new Scanner(System.in);
        try {
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number! price must be a double number");
            return enterPrice();
        }
    }

    public static String enterEmail() {
        Scanner sc = new Scanner(System.in);
        try {
            String email = sc.nextLine();
            Pattern emailPattern = Pattern.compile("^(.+)@(.+).com$");
            if (!emailPattern.matcher(email).matches()) {
                throw new IllegalArgumentException("Error, Invalid email");
            }
            return email;

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid email ! email must be same the format (name@domain.com)");
            return enterEmail();
        }
    }

    public static Date getInputDate() {
        Scanner sc = new Scanner(System.in);
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(MM_DD_YYYY);
            return Date.from(LocalDate.parse(sc.nextLine(), dtf).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeException e) {
            System.out.println("Error: Invalid date. Date must be the same format " + MM_DD_YYYY + " (e.g. 02/01/2000)");
            return getInputDate();
        }
    }

    public static Date plusDay(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, num);
        return calendar.getTime();
    }

    public static String getDateString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(MM_DD_YYYY);
        return formatter.format(date);
    }
}
