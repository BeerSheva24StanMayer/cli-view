package telran.view;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	String readString(String prompt);

	void writeString(String str);

	default void writeLine(Object obj){
		writeString(obj.toString() + "\n");
	}

	 default <T> T readObject(String prompt, String errorPrompt, Function<String, T> mapper){
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

		}while(running);
		return res;
	}
	/**
	 * 
	 * @param prompt
	 * @param errorPrompt
	 * @return Integer number
	 */
	default Integer readInt(String prompt, String errorPrompt){
		//TODO
		throw new UnsupportedOperationException();
	}

	 default Long readLong(String prompt, String errorPrompt) {
		//TODO
		throw new UnsupportedOperationException();
	}

	default Double readDouble(String prompt, String errorPrompt){
		//TODO
		throw new UnsupportedOperationException();
	}
	default Double readNumberRange(String prompt, String errorPrompt, double min, double max) {
		//TODO
		throw new UnsupportedOperationException();
	}
	default String readStringPredicate(String prompt, String errorPrompt,
			Predicate<String> predicate) {
				//TODO
				throw new UnsupportedOperationException();
			}
	default String readStringOptions(String prompt, String errorPrompt,
			HashSet<String> options){
				//TODO
				throw new UnsupportedOperationException();
			}
	default LocalDate readIsoDate(String prompt, String errorPrompt){
		//TODO
		throw new UnsupportedOperationException();
	}
	default LocalDate readIsoDateRange(String prompt, String errorPrompt, LocalDate from,
			LocalDate to) {
				//TODO
				throw new UnsupportedOperationException();
			}
	

}