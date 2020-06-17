package maj;

public class Result<T> {
    private T tee;

    private Result(T tee) {
        this.tee = tee;
    }

    public static <T> Result<T> of(T tee) {
        return new Result<>(tee);
    }

    public boolean isPresent() {
        return tee != null;
    }

}
