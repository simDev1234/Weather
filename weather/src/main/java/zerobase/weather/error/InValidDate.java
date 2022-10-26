package zerobase.weather.error;

public class InValidDate extends RuntimeException{

    private static final String MESSAGE = "너무 과거 혹은 미래의 메세지입니다.";

    public InValidDate(){
        super(MESSAGE);
    }

}
