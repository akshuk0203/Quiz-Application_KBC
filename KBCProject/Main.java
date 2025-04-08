public class Main {
    public static void main(String[] args) {
        KbcModel model = new KbcModel();
        KbcView view = new KbcView();
        new KbcController(model, view);
    }
}
