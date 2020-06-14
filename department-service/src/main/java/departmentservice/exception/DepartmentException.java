package departmentservice.exception;

public class DepartmentException extends Exception {

    public DepartmentException(RuntimeException exception){
        super(exception);
    }

    public DepartmentException(String exceptionClause, RuntimeException exception){
        super(exceptionClause,exception);
    }

}
