import java.time.LocalDateTime;

public class TryThings {

	private static LocalDateTime time;

	public static LocalDateTime getTime() {
		return time.now();
	}

	@Override
	public String toString() {
		return "TryThings [time=" + time + "]";
	}

	public static void main(String[] args) {
		System.out.println(TryThings.getTime());
	}
}
