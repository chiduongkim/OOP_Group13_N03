class StringIs {
    private String myString;
    public String getMyString() {
        return myString;
    }
}

public class Initialization {
    public static void main(String[] args) {
        StringIs a = new StringIs();
        
        System.out.println( a.getMyString());
    }
}
