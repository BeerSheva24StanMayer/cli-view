package telran.view;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
    String readString(String prompt);

    void writeString(String str);

    default void writeLine(Object obj) {
        writeString(obj.toString() + "\n");
    }

    default <T> T readObject(String prompt, String errorPrompt, Function<String, T> mapper) {
        boolean running = false;
        T res = null;
        do {
            running = false;
            try {
                String strRes = readString(prompt);
                res = mapper.apply(strRes);
            } catch (Exception e) {
                writeLine(errorPrompt + ": " + e.getMessage());
                running = true;
            }

        } while (running);
        return res;
    }

    /**
     * 
     * @param prompt
     * @param errorPrompt
     * @return Integer number
     */
    default Integer readInt(String prompt, String errorPrompt) {
        return readObject(prompt, errorPrompt, str -> Integer.parseInt(str));     
    }

    default Long readLong(String prompt, String errorPrompt) {
        return readObject(prompt, errorPrompt, str -> Long.parseLong(str));
    }

    default Double readDouble(String prompt, String errorPrompt) {
        return readObject(prompt, errorPrompt, str -> Double.parseDouble(str));
    }

    default Double readNumberRange(String prompt, String errorPrompt, double min, double max) throws Exception {
        Double doubleRes = readDouble(prompt, errorPrompt);
        if (!(doubleRes >= min && doubleRes <= max)) {
            throw new IllegalArgumentException(errorPrompt);
        }
        return doubleRes;
    }

    default String readStringPredicate(String prompt, String errorPrompt,
            Predicate<String> predicate) {
            String res = readString(prompt);
        if(!predicate.test(res)) {
            throw new IllegalArgumentException(errorPrompt);
        }
        return res;

    }

    default String readStringOptions(String prompt, String errorPrompt,
            HashSet<String> options) {
                String res = readObject(prompt, errorPrompt, str -> str.toString());
        if(!options.contains(res)) {
            throw new IllegalArgumentException(errorPrompt);
        }
        return res;
    }

    default LocalDate readIsoDate(String prompt, String errorPrompt) {
        return readObject(prompt, errorPrompt, str -> LocalDate.parse(str));
    }

    default LocalDate readIsoDateRange(String prompt, String errorPrompt, LocalDate from,
            LocalDate to) {
                LocalDate localDate = readIsoDate(prompt, errorPrompt);
                if(!(localDate.isAfter(from) && localDate.isBefore(to))) {
                    throw new IllegalArgumentException(errorPrompt);
                }
        return localDate;
    }


}